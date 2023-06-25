package ru.patrakhin.RollOfTheDiceOnJson.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


public class PersonDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;

    @Column(name = "current_cell")
    private int currentCell;

    @Column(name = "first_dice")
    private int firstDice;

    @Column(name = "second_dice")
    private int secondDice;

    @Column(name = "result")
    private int result;

    @Column(name = "gameStatus")
    private String gameStatus;

    public PersonDTO() {
        // Пустой конструктор без аргументов
    }

    public PersonDTO(int id, String name, int currentCell, int firstDice, int secondDice, int result) {
        this.id = id;
        this.name = name;
        this.currentCell = currentCell;
        this.firstDice = firstDice;
        this.secondDice = secondDice;
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrentCell() {
        return currentCell;
    }

    public void setCurrentCell(int currentCell) {
        this.currentCell = currentCell;
    }

    public int getFirstDice() {
        return firstDice;
    }

    public void setFirstDice(int firstDice) {
        this.firstDice = firstDice;
    }

    public int getSecondDice() {
        return secondDice;
    }

    public void setSecondDice(int secondDice) {
        this.secondDice = secondDice;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(String gameStatus) {
        this.gameStatus = gameStatus;
    }
}
