package nl.rabobank.service.impl;

import nl.rabobank.authorizations.Authorization;
import nl.rabobank.dto.PowerOfAttorney;
import nl.rabobank.exception.NotFoundException;
import nl.rabobank.mapper.PowerOfAttorneyMapper;
import nl.rabobank.mongo.dao.AccountRepository;
import nl.rabobank.mongo.dao.UserRepository;
import nl.rabobank.mongo.entity.Account;
import nl.rabobank.mongo.entity.User;
import nl.rabobank.service.PowerOfAttorneyService;
import nl.rabobank.validator.PowerOfAttorneyValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PowerOfAttorneyServiceImpl implements PowerOfAttorneyService {

    private AccountRepository accountRepository;
    private PowerOfAttorneyValidator powerOfAttorneyValidator;
    private PowerOfAttorneyMapper powerOfAttorneyMapper;
    private UserRepository userRepository;

    @Autowired
    public PowerOfAttorneyServiceImpl(AccountRepository accountRepository, PowerOfAttorneyValidator powerOfAttorneyValidator,
                       PowerOfAttorneyMapper powerOfAttorneyMapper, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.powerOfAttorneyValidator = powerOfAttorneyValidator;
        this.powerOfAttorneyMapper = powerOfAttorneyMapper;
        this.userRepository = userRepository;
    }

    @Override
    public void authorizeAccessOnAccount(PowerOfAttorney powerOfAttorney) {
        nl.rabobank.authorizations.PowerOfAttorney powerOfAttorneyDomain = powerOfAttorneyMapper.mapDtoToDomain(powerOfAttorney);

        powerOfAttorneyValidator.validateRequest(powerOfAttorneyDomain);

        User grantee = userRepository.findByName(powerOfAttorneyDomain.getGranteeName())
                .orElseThrow(() -> new NotFoundException("User with name - " + powerOfAttorneyDomain.getGranteeName() + " is not found."));

        Account account = accountRepository.findByAccountNumber(powerOfAttorneyDomain.getAccount().getAccountNumber())
                .orElseThrow(() -> new NotFoundException("Account with AccountNumber - " + powerOfAttorneyDomain.getAccount().getAccountNumber() + " is not found."));

        if(Authorization.READ == powerOfAttorney.getAuthorization()){
            grantee.getReadAccounts().add(account);
        }
        else if(Authorization.WRITE == powerOfAttorney.getAuthorization()){
            grantee.getWriteAccounts().add(account);
        }

        userRepository.save(grantee);
    }
}
