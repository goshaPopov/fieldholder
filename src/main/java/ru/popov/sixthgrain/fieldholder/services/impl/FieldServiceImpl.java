package ru.popov.sixthgrain.fieldholder.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.popov.sixthgrain.fieldholder.dto.requests.FieldRequest;
import ru.popov.sixthgrain.fieldholder.exceptions.NotFound;
import ru.popov.sixthgrain.fieldholder.exceptions.UniqueException;
import ru.popov.sixthgrain.fieldholder.model.Account;
import ru.popov.sixthgrain.fieldholder.model.Field;
import ru.popov.sixthgrain.fieldholder.repositories.AccountRepository;
import ru.popov.sixthgrain.fieldholder.repositories.FieldRepository;
import ru.popov.sixthgrain.fieldholder.services.AccountService;
import ru.popov.sixthgrain.fieldholder.services.FieldService;

@Service
public class FieldServiceImpl implements FieldService {

    private final FieldRepository fieldRepository;
    private final AccountService accountRepository;

    @Autowired
    public FieldServiceImpl(FieldRepository fieldRepository, AccountService accountRepository) {
        this.fieldRepository = fieldRepository;
        this.accountRepository = accountRepository;
    }


    @Override
    public Page<Field> findAll(Pageable pageable) {
        return fieldRepository.findAll(pageable);
    }

    @Override
    public Field findOneById(Long aLong) throws NotFound {
        return fieldRepository.findById(aLong).orElseThrow(() -> new NotFound("Field"));
    }

    @Override
    public Field create(FieldRequest request, Account account) throws UniqueException {

        Field existedField = fieldRepository.findFieldByAccountIdAndName(account.getId(), request.getFieldName());
        if (existedField == null) {
            Field field = new Field(request.getFieldName(), request.getLat(), request.getLon(), account);
            return fieldRepository.save(field);
        }
        throw new UniqueException("Field");
    }

    @Override
    public Field update(Field existedField, FieldRequest request, Account account) throws UniqueException, NotFound {

        Field withSameName = fieldRepository.findFieldByAccountIdAndName(account.getId(), request.getAccountName());
        if (existedField.equals(withSameName)) {
            existedField.setName(request.getFieldName());
            existedField.setLat(request.getLat());
            existedField.setLon(request.getLon());
            existedField.setAccount(account);
            return fieldRepository.save(existedField);
        }
        throw new UniqueException("Field");
    }

    @Override
    public Field delete(Long aLong) throws NotFound {
        Field toDelete = fieldRepository.findById(aLong).orElseThrow(() -> new NotFound("Field"));
        fieldRepository.delete(toDelete);
        return toDelete;
    }
}
