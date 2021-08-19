package nl.rabobank.service.impl;

import nl.rabobank.account.Account;
import nl.rabobank.exception.NotFoundException;
import nl.rabobank.mapper.AccountMapper;
import nl.rabobank.mongo.dao.AccountRepository;
import nl.rabobank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;
    private AccountMapper accountMapper;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public Account getAccountByAccountNumber(String accountNumber) {
        nl.rabobank.mongo.entity.Account accountEntity = accountRepository.findByAccountNumber(accountNumber).orElseThrow(() ->
                new NotFoundException("Account with AccountNumber - " + accountNumber + " is not found."));
        return accountMapper.mapEntityToDomain(accountEntity);
    }

    @Override
    public Account saveAccount(Account account) {
        nl.rabobank.mongo.entity.Account accountEntity = accountRepository.save(accountMapper.mapDomainToEntity(account));
        return accountMapper.mapEntityToDomain(accountEntity);
    }
}
