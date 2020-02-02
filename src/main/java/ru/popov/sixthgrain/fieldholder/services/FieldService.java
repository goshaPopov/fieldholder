package ru.popov.sixthgrain.fieldholder.services;

import ru.popov.sixthgrain.fieldholder.dto.requests.FieldRequest;
import ru.popov.sixthgrain.fieldholder.exceptions.NotFound;
import ru.popov.sixthgrain.fieldholder.exceptions.UniqueException;
import ru.popov.sixthgrain.fieldholder.model.Account;
import ru.popov.sixthgrain.fieldholder.model.Field;

public interface FieldService extends AbstractService<Field, Long> {

    Field create(FieldRequest request, Account account) throws UniqueException;

    Field update(Field field, FieldRequest request, Account account) throws UniqueException, NotFound;

}
