package ru.patrakhin.RollOfTheDiceOnJson.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.patrakhin.RollOfTheDiceOnJson.model.People;
import ru.patrakhin.RollOfTheDiceOnJson.repository.PersonRepository;
import ru.patrakhin.RollOfTheDiceOnJson.util.PersonNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PersonRepository personRepository;

    @Autowired
    public PeopleService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<People> findAll() {
        return personRepository.findAll();
    }

    public People findOne(int id) {
        Optional<People> foundPerson = personRepository.findById(id);
        return foundPerson.orElseThrow();
    }

    @Transactional
    public void save(People people){
        personRepository.save(people);
    }

    @Transactional
    public void delete(People people) {
        personRepository.delete(people);
    }

}
