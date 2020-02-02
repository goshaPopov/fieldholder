package ru.popov.sixthgrain.fieldholder.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.popov.sixthgrain.fieldholder.dto.response.AccountResponse;
import ru.popov.sixthgrain.fieldholder.dto.response.ErrorResponse;
import ru.popov.sixthgrain.fieldholder.exceptions.NotFound;
import ru.popov.sixthgrain.fieldholder.services.AccountService;

@Controller
@RequestMapping("/api/account")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping( method = RequestMethod.GET)
    @ResponseBody
    public HttpEntity<Page<AccountResponse>> findAll(Pageable pageable) {
        return new ResponseEntity<>(accountService.findAll(pageable).map(AccountResponse::new), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public HttpEntity findOne(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(new AccountResponse(accountService.findOneById(id)), HttpStatus.OK);
        } catch (NotFound notFound) {
            return new ResponseEntity<>(ErrorResponse.ACCOUNT_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
    }

}
