package ru.patrakhin.RollOfTheDiceOnJson.controller;

import ru.patrakhin.RollOfTheDiceOnJson.dto.PersonDTO;
import ru.patrakhin.RollOfTheDiceOnJson.model.People;
import ru.patrakhin.RollOfTheDiceOnJson.service.PeopleService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.patrakhin.RollOfTheDiceOnJson.util.PersonErrorResponse;
import ru.patrakhin.RollOfTheDiceOnJson.util.PersonNotCreatedException;
import ru.patrakhin.RollOfTheDiceOnJson.util.PersonNotFoundException;


import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/people")
public class PeopleController {
    private final PeopleService peopleService;
    private final ModelMapper modelMapper;

    @Autowired
    public PeopleController(PeopleService peopleService, ModelMapper modelMapper) {
        this.peopleService = peopleService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public List<PersonDTO> getPeople() {
        // Получение данных всех персон
        List<People> people = peopleService.findAll();
        return people.stream()
                .map(this::convertToPersonDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PersonDTO getPerson(@PathVariable("id") int id) {
        // Получение данных персоны по id
        People person = peopleService.findOne(id);
        return convertToPersonDTO(person);
    }


    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid PersonDTO personDTO, BindingResult bindingResult) {
        // Создание новой персоны
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";");
            }
            throw new PersonNotCreatedException(errorMsg.toString());
        }
        People person = convertToPerson(personDTO);
        peopleService.save(person);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable("id") int id, @RequestBody @Valid PersonDTO personDTO, BindingResult bindingResult) {
        // Обновление данных персоны по id
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";");
            }
            throw new PersonNotCreatedException(errorMsg.toString());
        }
        People existingPerson = peopleService.findOne(id);
        if (existingPerson == null) {
            throw new PersonNotFoundException("Person with this id wasn't found!");
        }
        People updatedPerson = convertToPerson(personDTO);
        updatedPerson.setId(id);
        peopleService.save(updatedPerson);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/{id}/roll-dice")
    public ResponseEntity<PersonDTO> rollDice(@PathVariable("id") int id) {
        // Бросок кубика и обновление данных персоны
        People person = peopleService.findOne(id);
        if (person == null) {
            throw new PersonNotFoundException("Person with this id wasn't found!");
        }
        person.rollDice();

        // Проверка достижения 48 ячейки или суммы больше 48
        if (person.getCurrentCell() >= 48 || (person.getCurrentCell() + person.getResult()) > 48) {
            person.setCurrentCell(48);
            person.setGameStatus("End of the game");
        } else {
            person.setGameStatus("Roll the dice");
        }

        peopleService.save(person);
        PersonDTO updatedPersonDTO = convertToPersonDTO(person);
        return ResponseEntity.ok(updatedPersonDTO);
    }

    @GetMapping("/{id}/result")
    public ResponseEntity<PersonDTO> getResult(@PathVariable("id") int id) {
        // Получение результата броска персоны
        People person = peopleService.findOne(id);
        if (person == null) {
            throw new PersonNotFoundException("Person with this id wasn't found!");
        }
        PersonDTO personDTO = new PersonDTO(
                person.getId(),
                person.getName(),
                person.getCurrentCell(),
                person.getFirstDice(),
                person.getSecondDice(),
                person.getResult()
        );
        if (person.getCurrentCell() >= 48 || (person.getCurrentCell() + person.getResult()) > 48) {
            personDTO.setGameStatus("End of the game");
        } else {
            personDTO.setGameStatus("Roll the dice");
        }
        return ResponseEntity.ok(personDTO);
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotFoundException e){
        PersonErrorResponse personErrorResponse = new PersonErrorResponse(
                "Person with this id wasn't found!",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(personErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotCreatedException e){
        PersonErrorResponse personErrorResponse = new PersonErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(personErrorResponse, HttpStatus.BAD_REQUEST);
    }

    private People convertToPerson(PersonDTO personDTO) {
        return modelMapper.map(personDTO, People.class);
    }

    private PersonDTO convertToPersonDTO(People person) {
        return modelMapper.map(person, PersonDTO.class);
    }
}
