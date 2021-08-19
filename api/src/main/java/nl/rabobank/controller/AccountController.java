package nl.rabobank.controller;

import io.swagger.v3.oas.annotations.Operation;
import nl.rabobank.dto.Account;
import nl.rabobank.mapper.AccountMapper;
import nl.rabobank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "rest/account", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {

    private AccountService accountService;
    private AccountMapper accountMapper;

    @Autowired
    public AccountController(AccountService accountService, AccountMapper accountMapper) {
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }

    @GetMapping("{accountNumber}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Gets the details of the account")
    public Account getAccountByAccountNumber(@PathVariable String accountNumber){
        return accountMapper.mapDomainToDto(accountService.getAccountByAccountNumber(accountNumber));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Creates a new account and returns it.")
    public Account addAccount(@RequestBody Account account){
        nl.rabobank.account.Account accountDomain = accountService.saveAccount(accountMapper.mapDtoToDomain(account));
        return accountMapper.mapDomainToDto(accountDomain);
    }
}
