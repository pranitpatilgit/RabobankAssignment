package nl.rabobank.service;

import nl.rabobank.dto.PowerOfAttorney;

public interface PowerOfAttorneyService {

    void authorizeAccessOnAccount(PowerOfAttorney powerOfAttorney);
}
