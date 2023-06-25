package ru.patrakhin.RollOfTheDiceOnJson.dto;

public class PersonDTO {
    private int id;
    private String name;
    private int currentCell;

    public PersonDTO() {
    }

    public PersonDTO(int id, String name, int currentCell) {
        this.id = id;
        this.name = name;
        this.currentCell = currentCell;
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
}
