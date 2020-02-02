package ru.popov.sixthgrain.fieldholder.dto.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class FieldRequest {


    @NotBlank(message = "Lat is mandatory")
    @JsonProperty("Lat")
    private Double lat;
    @NotBlank(message = "Lon is mandatory")
    @JsonProperty("Lon")
    private Double lon;
    @NotBlank(message = "FieldName is mandatory")
    @JsonProperty("FieldName")
    private String fieldName;
    @NotBlank(message = "AccountName is mandatory")
    @JsonProperty("AccountName")
    private String accountName;
    @NotBlank(message = "AccountEmail is mandatory")
    @JsonProperty("AccountEmail")
    private String accountEmail;

    public FieldRequest() {
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

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountEmail() {
        return accountEmail;
    }

    public void setAccountEmail(String accountEmail) {
        this.accountEmail = accountEmail;
    }
}
