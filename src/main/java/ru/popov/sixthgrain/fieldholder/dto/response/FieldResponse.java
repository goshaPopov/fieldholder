package ru.popov.sixthgrain.fieldholder.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.popov.sixthgrain.fieldholder.model.Account;
import ru.popov.sixthgrain.fieldholder.model.Field;

public class FieldResponse {

    @JsonProperty("FieldId")
    private Long fieldId;
    @JsonProperty("Lat")
    private Double lat;
    @JsonProperty("Lon")
    private Double lon;
    @JsonProperty("FieldName")
    private String fieldName;
    @JsonProperty("AccountName")
    private String accountName;
    @JsonProperty("AccountEmail")
    private String accountEmail;

    public FieldResponse() {
    }

    public FieldResponse(Long fieldId, Double lat, Double lon, String fieldName, String accountName, String accountEmail) {
        this.fieldId = fieldId;
        this.lat = lat;
        this.lon = lon;
        this.fieldName = fieldName;
        this.accountName = accountName;
        this.accountEmail = accountEmail;
    }

    public FieldResponse(Field field) {
        this(field.getId(), field.getLat(), field.getLon(), field.getName(), field.getAccount().getName(), field.getAccount().getEmail());
    }

    public Long getFieldId() {
        return fieldId;
    }

    public void setFieldId(Long fieldId) {
        this.fieldId = fieldId;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getAccountEmail() {
        return accountEmail;
    }

    public void setAccountEmail(String accountEmail) {
        this.accountEmail = accountEmail;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
}
