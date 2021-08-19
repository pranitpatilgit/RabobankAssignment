package nl.rabobank.mongo.entity;

import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@NoArgsConstructor
public class User extends AuditableEntity{

    @Indexed(unique=true)
    String name;

    @DBRef
    List<Account> readAccounts;
    @DBRef
    List<Account> writeAccounts;
}
