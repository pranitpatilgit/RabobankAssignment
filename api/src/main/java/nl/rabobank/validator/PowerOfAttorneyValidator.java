package nl.rabobank.validator;

import nl.rabobank.authorizations.PowerOfAttorney;
import nl.rabobank.exception.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class PowerOfAttorneyValidator {

    public void validateRequest(PowerOfAttorney powerOfAttorney){
        if(!powerOfAttorney.getGrantorName().equals(powerOfAttorney.getAccount().getAccountHolderName())){
            throw new ValidationException("Account does not belong to grantor");
        }
    }
}
