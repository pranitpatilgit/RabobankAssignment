package nl.rabobank.mapper;

import nl.rabobank.account.Account;
import nl.rabobank.account.PaymentAccount;
import nl.rabobank.account.SavingsAccount;
import nl.rabobank.dto.AccountType;
import nl.rabobank.mapper.impl.AccountMapperImpl;
import nl.rabobank.mongo.entity.AccountEntityType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

@RunWith(MockitoJUnitRunner.class)
public class AccountMapperTest {

    private ModelMapper modelMapper;
    private AccountMapper accountMapper;

    @Before
    public void setUp(){
        modelMapper = new ModelMapper();
        accountMapper = new AccountMapperImpl(modelMapper);
    }

    @Test
    public void givenAccountDto_whenMapDtoToDomain_thenReturnAccountDomain(){
        nl.rabobank.dto.Account account = new nl.rabobank.dto.Account();
        account.setAccountNumber("1234");
        account.setAccountType(AccountType.SAVINGS);
        account.setAccountHolderName("AA");
        account.setBalance(1.23);

        Account result = accountMapper.mapDtoToDomain(account);

        Assert.assertEquals(account.getAccountNumber(), result.getAccountNumber());
        Assert.assertEquals(account.getAccountHolderName(), result.getAccountHolderName());
        Assert.assertEquals(account.getBalance(), result.getBalance());
        Assert.assertTrue(result instanceof SavingsAccount);
    }

    @Test
    public void givenAccountDomain_whenMapDomainToEntity_thenReturnAccountEntity(){
        PaymentAccount account = new PaymentAccount();
        account.setAccountNumber("1");
        account.setAccountHolderName("A");
        account.setBalance(1.23);

        nl.rabobank.mongo.entity.Account accountentity = accountMapper.mapDomainToEntity(account);

        Assert.assertEquals(account.getAccountNumber(), accountentity.getAccountNumber());
        Assert.assertEquals(account.getAccountHolderName(), accountentity.getAccountHolderName());
        Assert.assertEquals(account.getBalance(), accountentity.getBalance());
        Assert.assertEquals(AccountEntityType.PAYMENT, accountentity.getAccountEntityType());
    }

    @Test
    public void givenAccountEntity_whenMapEntityToDomain_thenReturnAccountDomain(){
        nl.rabobank.mongo.entity.Account accountEntity = new nl.rabobank.mongo.entity.Account();
        accountEntity.setAccountEntityType(AccountEntityType.SAVINGS);
        accountEntity.setAccountNumber("1");
        accountEntity.setAccountHolderName("A");
        accountEntity.setBalance(1.23);

        Account account = accountMapper.mapEntityToDomain(accountEntity);

        Assert.assertEquals(accountEntity.getAccountNumber(), account.getAccountNumber());
        Assert.assertEquals(accountEntity.getAccountHolderName(), account.getAccountHolderName());
        Assert.assertEquals(accountEntity.getBalance(), account.getBalance());
        Assert.assertTrue(account instanceof SavingsAccount);
    }
    @Test
    public void givenAccountDomain_whenMapDomainToDto_thenReturnAccountDto(){
        PaymentAccount account = new PaymentAccount();
        account.setAccountNumber("1");
        account.setAccountHolderName("A");
        account.setBalance(1.23);

        nl.rabobank.dto.Account accountDto = accountMapper.mapDomainToDto(account);

        Assert.assertEquals(account.getAccountNumber(), accountDto.getAccountNumber());
        Assert.assertEquals(account.getAccountHolderName(), accountDto.getAccountHolderName());
        Assert.assertEquals(account.getBalance(), accountDto.getBalance());
        Assert.assertEquals(AccountType.PAYMENT, accountDto.getAccountType());
    }
}
