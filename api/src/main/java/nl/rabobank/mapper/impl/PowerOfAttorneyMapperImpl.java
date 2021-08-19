package nl.rabobank.mapper.impl;

import nl.rabobank.authorizations.PowerOfAttorney;
import nl.rabobank.mapper.PowerOfAttorneyMapper;
import nl.rabobank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PowerOfAttorneyMapperImpl implements PowerOfAttorneyMapper {

    private AccountService accountService;

    @Autowired
    public PowerOfAttorneyMapperImpl(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public PowerOfAttorney mapDtoToDomain(nl.rabobank.dto.PowerOfAttorney powerOfAttorney) {
        return PowerOfAttorney.builder()
                .granteeName(powerOfAttorney.getGranteeName())
                .grantorName(powerOfAttorney.getGrantorName())
                .authorization(powerOfAttorney.getAuthorization())
                .account(accountService.getAccountByAccountNumber(powerOfAttorney.getAccountNumber()))
                .build();
    }
}
