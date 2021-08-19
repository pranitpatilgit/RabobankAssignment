package nl.rabobank.dto;

import lombok.Data;

@Data
public class Account {

    String accountNumber;
    String accountHolderName;
    Double balance;
    AccountType accountType;
}
