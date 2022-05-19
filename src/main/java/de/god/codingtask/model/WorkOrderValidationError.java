package de.god.codingtask.model;

import lombok.Getter;

@Getter
public enum Error {
    INVALID_DEPARTMENT(0, "Department field is required"),
    INVALID_START_DATE(1, "Start date field is required and must be before current date"),
    INVALID_END_DATE(2, "End date field is required and must be after end date"),
    INVALID_CURRENCY_CODE(3, "Currency must be a valid a ISO 4217 code"),
    INVALID_COST(4, "Cost must be a positive number"),
    INVALID_ANALYSIS_DATE(5, "Analysis date must be after start date and before end date"),
    INVALID_RESPONSIBLE_PERSON(6, "Responsible person field is required"),
    INVALID_TEST_DATE(7, "Test date must not be empty and must be after analysis date and before end date"),
    INVALID_PARTS_LIST(8, "Parts list must not be empty"),
    INVALID_FACTORY_NAME(10, "Factory name must not be empty"),
    INVALID_FACTORY_ORDER_NUMBER(11, "Factory order number rules: the length is 10, first 2 characters - letters, others – numbers."),
    INVALID_PARTS_NUMBERS(12, "Inventory numbers must not be empty");

    private final int code;
    private final String message;

    private Error(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return code + ": " + message;
    }
}
