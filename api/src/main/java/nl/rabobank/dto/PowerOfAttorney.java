package nl.rabobank.dto;

import lombok.Setter;
import lombok.Value;
import nl.rabobank.account.Account;
import nl.rabobank.authorizations.Authorization;

@Value
@Setter
public class PowerOfAttorney {
    String granteeName;
    String grantorName;
    Account account;
    Authorization authorization;
}
