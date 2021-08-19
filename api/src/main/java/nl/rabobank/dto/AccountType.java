package nl.rabobank.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum AccountType {
    SAVINGS,
    PAYMENT
}
