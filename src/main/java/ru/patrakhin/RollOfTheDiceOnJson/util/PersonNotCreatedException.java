package ru.patrakhin.RollOfTheDiceOnJson.util;

public class PersonNotCreatedException extends RuntimeException{
    public PersonNotCreatedException(String message){
        super(message);
    }
}
