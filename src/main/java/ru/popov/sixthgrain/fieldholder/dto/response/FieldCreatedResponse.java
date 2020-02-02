package ru.popov.sixthgrain.fieldholder.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FieldCreatedResponse {

    @JsonProperty("FieldId")
    private Long fieldId;
    @JsonProperty("AccountId")
    private Long accountId;

    public FieldCreatedResponse() {
    }

    public FieldCreatedResponse(Long fieldId, Long accountId) {
        this.fieldId = fieldId;
        this.accountId = accountId;
    }

    public Long getFieldId() {
        return fieldId;
    }

    public void setFieldId(Long fieldId) {
        this.fieldId = fieldId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

}
