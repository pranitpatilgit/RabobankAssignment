package nl.rabobank.controller.IT;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.rabobank.dto.Account;
import nl.rabobank.dto.AccountType;
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
public class UserIT {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void givenAccount_whenAccountIsCreated_thenStatus200AndUserIsCreated() throws Exception {
        //Given account data
        Account account1 = new Account("NL01BANK12345647", "Pranit" , 100.00, AccountType.SAVINGS);

        //When
        mvc.perform(MockMvcRequestBuilders.post("/rest/account")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(account1)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        //Then
        mvc.perform(MockMvcRequestBuilders.get("/rest/user/Pranit")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Pranit"));
    }
}
