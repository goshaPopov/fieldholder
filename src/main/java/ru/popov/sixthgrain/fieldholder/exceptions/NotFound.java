package ru.popov.sixthgrain.fieldholder.exceptions;

public class NotFound extends Exception {

    public NotFound(String entName) {
        super(entName + " not found.");
    }
}
