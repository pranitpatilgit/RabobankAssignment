package nl.rabobank.dto;

import java.util.List;

public class User {
    String id;
    String name;
    List<Account> readAccounts;
    List<Account> writeAccounts;
}
