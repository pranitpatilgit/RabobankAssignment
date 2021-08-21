package nl.rabobank.service;

import nl.rabobank.account.SavingsAccount;
import nl.rabobank.dto.User;
import nl.rabobank.exception.NotFoundException;
import nl.rabobank.mapper.AccountMapper;
import nl.rabobank.mongo.dao.AccountRepository;
import nl.rabobank.mongo.entity.Account;
import nl.rabobank.service.impl.AccountServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;
    @Mock
    private AccountMapper accountMapper;
    @Mock
    private UserService userService;

    private AccountServiceImpl accountService;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Before
    public void setUp(){
        accountService = new AccountServiceImpl(accountRepository, accountMapper, userService);
    }

    @Test
    public void givenAccountNumber_whenGetAccountByAccountNumber_thenReturnAccount(){
        SavingsAccount account = new SavingsAccount();
        account.setAccountNumber("1234");
        account.setAccountHolderName("AA");
        account.setBalance(1.12);

        Account accountEntity = new Account();

        when(accountRepository.findByAccountNumber("1234")).thenReturn(Optional.of(accountEntity));
        when(accountMapper.mapEntityToDomain(accountEntity)).thenReturn(account);

        nl.rabobank.account.Account result = accountService.getAccountByAccountNumber("1234");

        Assert.assertEquals(account, result);
    }

    @Test
    public void givenAccount_whenSaveAccount_thenReturnAccount(){
        SavingsAccount account = new SavingsAccount();
        account.setAccountNumber("1234");
        account.setAccountHolderName("AA");
        account.setBalance(1.12);

        Account accountEntity = new Account();
        User user = new User();

        when(accountMapper.mapDomainToEntity(account)).thenReturn(accountEntity);
        when(accountRepository.save(accountEntity)).thenReturn(accountEntity);
        when(userService.getUserByName("AA")).thenReturn(user);
        when(accountMapper.mapEntityToDomain(accountEntity)).thenReturn(account);

        nl.rabobank.account.Account result = accountService.saveAccount(account);

        Assert.assertEquals(account, result);
    }

    @Test(expected = NotFoundException.class)
    public void givenAccountNumber_whenGetAccountByAccountNumber_thenThrowNotFoundException(){

        when(accountRepository.findByAccountNumber("1234")).thenThrow(new NotFoundException("Account with id 1234 not found."));

        accountService.getAccountByAccountNumber("1234");

        expectedEx.expect(NotFoundException.class);
        expectedEx.expectMessage("Account with id 1234 not found.");
    }
}
