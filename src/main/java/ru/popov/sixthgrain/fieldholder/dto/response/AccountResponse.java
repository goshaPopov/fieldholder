package ru.popov.sixthgrain.fieldholder.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.popov.sixthgrain.fieldholder.model.Account;
import ru.popov.sixthgrain.fieldholder.model.Field;

import java.util.List;
import java.util.stream.Collectors;

public class AccountResponse {

    @JsonProperty("AccountId")
    private Long id;
    @JsonProperty("AccountName")
    private String name;
    @JsonProperty("AccountEmail")
    private String email;
    @JsonProperty("Fields")
    private List<Long> fields;

    public AccountResponse() {
    }

    public AccountResponse(Account account) {
        this.id = account.getId();
        this.name = account.getName();
        this.email = account.getEmail();
        this.fields = account.getFields().stream().map(Field::getId).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Long> getFields() {
        return fields;
    }

    public void setFields(List<Long> fields) {
        this.fields = fields;
    }
}
