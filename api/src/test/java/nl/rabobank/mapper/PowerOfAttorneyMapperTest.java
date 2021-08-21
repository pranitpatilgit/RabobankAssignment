package nl.rabobank.mapper;

import nl.rabobank.account.SavingsAccount;
import nl.rabobank.authorizations.Authorization;
import nl.rabobank.dto.PowerOfAttorney;
import nl.rabobank.mapper.impl.PowerOfAttorneyMapperImpl;
import nl.rabobank.service.AccountService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PowerOfAttorneyMapperTest {

    @Mock
    private AccountService accountService;
    private PowerOfAttorneyMapper powerOfAttorneyMapper;

    @Before
    public void setUp(){
        powerOfAttorneyMapper = new PowerOfAttorneyMapperImpl(accountService);
    }

    @Test
    public void givenDto_whenMapDtoToDomain_thenReturnDomain(){
        PowerOfAttorney powerOfAttorney = new PowerOfAttorney();
        powerOfAttorney.setAccountNumber("1");
        powerOfAttorney.setGranteeName("Grantee");
        powerOfAttorney.setGrantorName("Grantor");
        powerOfAttorney.setAuthorization(Authorization.WRITE);

        SavingsAccount account = new SavingsAccount();
        account.setAccountNumber("1");

        when(accountService.getAccountByAccountNumber("1")).thenReturn(account);

        nl.rabobank.authorizations.PowerOfAttorney powerOfAttorneyDomain =
                powerOfAttorneyMapper.mapDtoToDomain(powerOfAttorney);

        Assert.assertEquals(powerOfAttorney.getAccountNumber(), powerOfAttorneyDomain.getAccount().getAccountNumber());
        Assert.assertEquals(powerOfAttorney.getGranteeName(), powerOfAttorneyDomain.getGranteeName());
        Assert.assertEquals(powerOfAttorney.getGrantorName(), powerOfAttorneyDomain.getGrantorName());
        Assert.assertEquals(powerOfAttorney.getAuthorization(), powerOfAttorneyDomain.getAuthorization());
    }
}
