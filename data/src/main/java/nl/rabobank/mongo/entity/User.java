package nl.rabobank.mongo.entity;

import lombok.Builder;
import lombok.Setter;
import lombok.Value;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Value
@Setter
@Builder
public class User extends AuditableEntity{
    String name;

    @DBRef
    List<Account> readAccounts;
    @DBRef
    List<Account> writeAccounts;
}
