package ru.patrakhin.RollOfTheDiceOnJson.model;

import java.util.Random;

public class Person {
    private final Random random = new Random();

    private int id;
    private String name;
    private int currentCell;
    private int firstDice;
    private int secondDice;
    private int result;

    public Person() {

    }

    public Person(int id, String name, int currentCell, int firstDice, int secondDice, int result) {
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
