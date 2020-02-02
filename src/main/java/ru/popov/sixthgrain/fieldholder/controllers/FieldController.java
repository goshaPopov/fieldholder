package ru.popov.sixthgrain.fieldholder.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.popov.sixthgrain.fieldholder.dto.requests.FieldRequest;
import ru.popov.sixthgrain.fieldholder.dto.response.ErrorResponse;
import ru.popov.sixthgrain.fieldholder.dto.response.FieldCreatedResponse;
import ru.popov.sixthgrain.fieldholder.dto.response.FieldResponse;
import ru.popov.sixthgrain.fieldholder.exceptions.NotFound;
import ru.popov.sixthgrain.fieldholder.exceptions.UniqueException;
import ru.popov.sixthgrain.fieldholder.model.Account;
import ru.popov.sixthgrain.fieldholder.model.Field;
import ru.popov.sixthgrain.fieldholder.services.AccountService;
import ru.popov.sixthgrain.fieldholder.services.FieldService;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api/field")
public class FieldController {

    private final FieldService fieldService;
    private final AccountService accountService;

    @Autowired
    public FieldController(FieldService fieldService, AccountService accountService) {
        this.fieldService = fieldService;
        this.accountService = accountService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public HttpEntity findAll(Pageable pageable) {
        return new ResponseEntity<>(fieldService.findAll(pageable).map(FieldResponse::new), HttpStatus.OK);
    }

    @RequestMapping(value = "/{fieldId}", method = RequestMethod.GET)
    @ResponseBody
    public HttpEntity findOne(@PathVariable("fieldId") Long fieldId) {
        try {
            return new ResponseEntity<>(new FieldResponse(fieldService.findOneById(fieldId)), HttpStatus.OK);
        } catch (NotFound notFound) {
            return new ResponseEntity<>(ErrorResponse.FIELD_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public HttpEntity createField(@RequestBody FieldRequest fieldRequest) {
        try {
            Account account = accountService.findOrCreateAccount(fieldRequest.getAccountName(), fieldRequest.getAccountEmail());
            Field field = fieldService.create(fieldRequest, account);
            return new ResponseEntity<>(new FieldCreatedResponse(field.getId(), field.getAccount().getId()), HttpStatus.OK);
        } catch (UniqueException e) {
            return new ResponseEntity<>(ErrorResponse.UNIQUE_FIELD_ERROR, HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(value = "/{fieldId}", method = RequestMethod.POST)
    @ResponseBody
    public HttpEntity updateField(@PathVariable("fieldId") Long fieldId, @RequestBody FieldRequest fieldRequest) {

        try {
            Field toUpdate = fieldService.findOneById(fieldId);
            Account account = accountService.findOrCreateAccount(fieldRequest.getAccountName(), fieldRequest.getAccountEmail());
            Field field = fieldService.update(toUpdate, fieldRequest, account);
            return new ResponseEntity<>(new FieldCreatedResponse(field.getId(), field.getAccount().getId()), HttpStatus.OK);
        } catch (UniqueException e) {
            return new ResponseEntity<>(ErrorResponse.UNIQUE_FIELD_ERROR, HttpStatus.BAD_REQUEST);
        } catch (NotFound notFound) {
            return new ResponseEntity<>(ErrorResponse.FIELD_NOT_FOUND, HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(value = "/{fieldId}", method = RequestMethod.DELETE)
    @ResponseBody
    public HttpEntity deleteField(@PathVariable("fieldId") Long fieldId) {

        try {
            fieldService.delete(fieldId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFound notFound) {
            return new ResponseEntity<>(ErrorResponse.FIELD_NOT_FOUND, HttpStatus.NOT_FOUND);
        }

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }


}
