package nl.rabobank.service;

import nl.rabobank.account.Account;

public interface AccountService {

    Account getAccountByAccountNumber(String id);
    Account saveAccount(Account account);
}
