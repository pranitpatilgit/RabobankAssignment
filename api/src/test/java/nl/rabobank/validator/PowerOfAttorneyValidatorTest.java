package nl.rabobank.validator;

import nl.rabobank.account.SavingsAccount;
import nl.rabobank.authorizations.PowerOfAttorney;
import nl.rabobank.dto.Account;
import nl.rabobank.exception.NotFoundException;
import nl.rabobank.exception.ValidationException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PowerOfAttorneyValidatorTest {

    private PowerOfAttorneyValidator powerOfAttorneyValidator;

    @Before
    public void setUp(){
        powerOfAttorneyValidator = new PowerOfAttorneyValidator();
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void givenGrantorNameSameAsAccountHolderName_whenValidate_thenNoError(){
        SavingsAccount account = new SavingsAccount();
        account.setAccountHolderName("Grantor");
        PowerOfAttorney powerOfAttorney = PowerOfAttorney.builder()
                .granteeName("A")
                .grantorName("Grantor")
                .account(account)
                .build();

        powerOfAttorneyValidator.validateRequest(powerOfAttorney);
    }

    @Test(expected = ValidationException.class)
    public void givenGrantorNameIsNOTSameAsAccountHolderName_whenValidate_thenValidationException(){
        SavingsAccount account = new SavingsAccount();
        account.setAccountHolderName("I am not correct");
        PowerOfAttorney powerOfAttorney = PowerOfAttorney.builder()
                .granteeName("A")
                .grantorName("Grantor")
                .account(account)
                .build();

        powerOfAttorneyValidator.validateRequest(powerOfAttorney);

        expectedEx.expect(ValidationException.class);
        expectedEx.expectMessage("Account does not belong to grantor");
    }

}
