package nl.rabobank.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class User {

    @NonNull
    String name;
    Set<Account> readAccounts = new HashSet<>();
    Set<Account> writeAccounts = new HashSet<>();
}
