package nl.rabobank.controller.IT;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.rabobank.authorizations.Authorization;
import nl.rabobank.dto.Account;
import nl.rabobank.dto.AccountType;
import nl.rabobank.dto.PowerOfAttorney;
import nl.rabobank.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationTest.properties")
@Import(TestConfiguration.class)
public class PowerOfAttorneyIT {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserService userService;

    @Test
    public void givenAccountAndUsers_whenProvidedPowerOfAttorney_thenStatus200AndAccountAddedToUser() throws Exception {
        Account account1 = new Account("NL01BANK12345647", "Pranit" , 100.00, AccountType.SAVINGS);
        Account account2 = new Account("NL01BANK12345648", "Ash" , 100.00, AccountType.PAYMENT);

        //Given accounts and users
        mvc.perform(MockMvcRequestBuilders.post("/rest/account")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(account1)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        mvc.perform(MockMvcRequestBuilders.post("/rest/account")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(account2)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        //When
        PowerOfAttorney powerOfAttorney =
                new PowerOfAttorney("Ash", "Pranit", "NL01BANK12345647", Authorization.READ);
        mvc.perform(MockMvcRequestBuilders.post("/rest/poa")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(powerOfAttorney)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        Assert.assertFalse(userService.getUserByName("Ash").getReadAccounts().isEmpty());
        Assert.assertTrue(userService.getUserByName("Pranit").getReadAccounts().isEmpty());
    }
}
