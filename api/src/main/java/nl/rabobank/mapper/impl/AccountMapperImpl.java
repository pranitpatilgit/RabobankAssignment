package nl.rabobank.mapper.impl;

import nl.rabobank.account.Account;
import nl.rabobank.account.PaymentAccount;
import nl.rabobank.account.SavingsAccount;
import nl.rabobank.dto.AccountType;
import nl.rabobank.mapper.AccountMapper;
import nl.rabobank.mongo.entity.AccountEntityType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountMapperImpl implements AccountMapper {

    private ModelMapper modelMapper;

    @Autowired
    public AccountMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Account mapDtoToDomain(nl.rabobank.dto.Account account) {
        if(AccountType.SAVINGS == account.getAccountType()){
            return modelMapper.map(account, SavingsAccount.class);
        }
        else if(AccountType.PAYMENT == account.getAccountType()){
            return modelMapper.map(account, SavingsAccount.class);
        }
        return null;
    }

    @Override
    public nl.rabobank.mongo.entity.Account mapDomainToEntity(Account account) {
        nl.rabobank.mongo.entity.Account accountEntity = modelMapper.map(account, nl.rabobank.mongo.entity.Account.class);
        accountEntity.setAccountEntityType(account instanceof PaymentAccount ? AccountEntityType.PAYMENT : AccountEntityType.SAVINGS);
        return accountEntity;
    }

    @Override
    public Account mapEntityToDomain(nl.rabobank.mongo.entity.Account account) {
        if(AccountEntityType.SAVINGS == account.getAccountEntityType()){
            return modelMapper.map(account, SavingsAccount.class);
        }
        else if(AccountEntityType.PAYMENT == account.getAccountEntityType()){
            return modelMapper.map(account, SavingsAccount.class);
        }
        return null;
    }

    @Override
    public nl.rabobank.dto.Account mapDomainToDto(Account account) {
        nl.rabobank.dto.Account accountDto = modelMapper.map(account, nl.rabobank.dto.Account.class);
        accountDto.setAccountType(account instanceof PaymentAccount ? AccountType.PAYMENT : AccountType.SAVINGS);
        return accountDto;
    }
}
