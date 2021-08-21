package nl.rabobank.service;

import nl.rabobank.exception.NotFoundException;
import nl.rabobank.mongo.dao.UserRepository;
import nl.rabobank.mongo.entity.Account;
import nl.rabobank.mongo.entity.User;
import nl.rabobank.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    private UserService userService;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Before
    public void setUp(){
        modelMapper = new ModelMapper();
        userService = new UserServiceImpl(userRepository, modelMapper);
    }

    @Test
    public void givenUserName_whenGetUserByName_thenReturnUser(){
        User user = new User();
        user.setName("A");
        Set<Account> readAccounts = new HashSet<>();
        Account account = new Account();
        account.setAccountNumber("1");
        readAccounts.add(account);
        user.setReadAccounts(readAccounts);
        user.setWriteAccounts(readAccounts);

        when(userRepository.findByName("A")).thenReturn(Optional.of(user));

        nl.rabobank.dto.User result = userService.getUserByName("A");

        Assert.assertEquals(user.getName(), result.getName());
        Assert.assertEquals(user.getReadAccounts().size(), 1);
        Assert.assertEquals(user.getWriteAccounts().size(), 1);
    }

    @Test
    public void givenAccount_whenSaveAccount_thenReturnAccount(){
        User user = new User();
        user.setName("A");
        Set<Account> readAccounts = new HashSet<>();
        Account account = new Account();
        account.setAccountNumber("1");
        readAccounts.add(account);
        user.setReadAccounts(readAccounts);
        user.setWriteAccounts(readAccounts);

        nl.rabobank.dto.User userDto = new nl.rabobank.dto.User();
        userDto.setName("A");
        Set<nl.rabobank.dto.Account> readAccounts1 = new HashSet<>();
        nl.rabobank.dto.Account account1 = new nl.rabobank.dto.Account();
        account1.setAccountNumber("1");
        readAccounts1.add(account1);
        userDto.setReadAccounts(readAccounts1);
        userDto.setWriteAccounts(readAccounts1);

        when(userRepository.save(any(User.class))).thenReturn(user);

        nl.rabobank.dto.User result = userService.createUser(userDto);

        Assert.assertEquals(user.getName(), result.getName());
        Assert.assertEquals(user.getReadAccounts().size(), 1);
        Assert.assertEquals(user.getWriteAccounts().size(), 1);
    }

    @Test(expected = NotFoundException.class)
    public void givenAccountNumber_whenGgetUserByName_thenThrowNotFoundException(){

        when(userRepository.findByName("A")).thenThrow(new NotFoundException("User with name A is not found."));

        userService.getUserByName("A");

        expectedEx.expect(NotFoundException.class);
        expectedEx.expectMessage("User with name A is not found.");
    }
}
