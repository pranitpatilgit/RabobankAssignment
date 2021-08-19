package nl.rabobank.mongo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document
@Data
@NoArgsConstructor
public class User extends AuditableEntity{

    @Indexed(unique=true)
    String name;

    @DBRef
    Set<Account> readAccounts = new HashSet<>();
    @DBRef
    Set<Account> writeAccounts = new HashSet<>();
}
