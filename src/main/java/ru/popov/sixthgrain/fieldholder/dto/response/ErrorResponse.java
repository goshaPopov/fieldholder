package ru.popov.sixthgrain.fieldholder.dto.response;

public class ErrorResponse {

    private int code;
    private String error;
    private String description;
    protected ErrorResponse(int code, String error, String description) {
        this.code = code;
        this.error = error;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static final ErrorResponse FIELD_NOT_FOUND = new ErrorResponse(5, "field.does.not.exist", "Field does not exist");
    public static final ErrorResponse ACCOUNT_NOT_FOUND = new ErrorResponse(5, "account.does.not.exist", "Account does not exist");
    public static final ErrorResponse UNIQUE_FIELD_ERROR = new ErrorResponse(6, "field.already.exists", "Field already exists");
}
