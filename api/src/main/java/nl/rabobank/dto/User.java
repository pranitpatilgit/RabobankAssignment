package nl.rabobank.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class User {

    @NonNull
    String name;
    List<Account> readAccounts;
    List<Account> writeAccounts;
}
