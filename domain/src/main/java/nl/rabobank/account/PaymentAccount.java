package nl.rabobank.account;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentAccount implements Account
{
    String accountNumber;
    String accountHolderName;
    Double balance;
}
