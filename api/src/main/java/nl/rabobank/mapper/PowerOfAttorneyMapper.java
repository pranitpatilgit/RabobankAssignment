package nl.rabobank.mapper;

import nl.rabobank.authorizations.PowerOfAttorney;

public interface PowerOfAttorneyMapper {

    PowerOfAttorney mapDtoToDomain(nl.rabobank.dto.PowerOfAttorney powerOfAttorney);
}
