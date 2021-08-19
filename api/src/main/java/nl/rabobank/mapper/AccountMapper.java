package nl.rabobank.mapper;

import nl.rabobank.account.Account;

public interface AccountMapper {

    Account mapDtoToDomain(nl.rabobank.dto.Account account);
    nl.rabobank.mongo.entity.Account mapDomainToEntity(Account account);
    Account mapEntityToDomain(nl.rabobank.mongo.entity.Account account);
    nl.rabobank.dto.Account mapDomainToDto(Account account);
}
