package ru.patrakhin.RollOfTheDiceOnJson.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Random;

@Entity
@Table(name = "People")
public class People {

    private final Random random = new Random();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;

    @Column(name = "currentCell")
    private int currentCell;

    @Column(name = "firstDice")
    private int firstDice;

    @Column(name = "secondDice")
    private int secondDice;

    @Column(name = "result")
    private int result;

    public People() {

    }

    public People(int id, String name, int currentCell, int firstDice, int secondDice, int result) {
        this.id = id;
        this.name = name;
        this.currentCell = currentCell;
        this.firstDice = firstDice;
        this.secondDice = secondDice;
        this.result = result;
    }

    public void rollDice() {
        int firstNumberSided = random.nextInt(6) + 1;
        int secondNumberSided = random.nextInt(6) + 1;
        setFirstDice(firstNumberSided);
        setSecondDice(secondNumberSided);
        setCurrentCell(getCurrentCell() + (firstNumberSided + secondNumberSided));
        setResult(firstNumberSided + secondNumberSided);
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
}
