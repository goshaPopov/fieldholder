package ru.popov.sixthgrain.fieldholder.exceptions;

public class UniqueException extends Exception {

    public UniqueException(String ent) {
        super(ent + " already exists");
    }

}
