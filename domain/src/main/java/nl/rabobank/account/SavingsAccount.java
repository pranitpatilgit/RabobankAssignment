package nl.rabobank.account;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SavingsAccount implements Account
{
    String accountNumber;
    String accountHolderName;
    Double balance;
}
