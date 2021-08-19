package nl.rabobank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    String accountNumber;
    String accountHolderName;
    Double balance;
    AccountType accountType;
}
