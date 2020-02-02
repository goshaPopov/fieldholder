package ru.popov.sixthgrain.fieldholder.model;

public abstract class AbstractEntity<ID> {

    public abstract ID getId();

    public abstract void setId(ID id);
}
