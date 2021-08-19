package nl.rabobank.mongo.entity;

import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Account extends AuditableEntity{

    @Indexed(unique=true)
    String accountNumber;
    String accountHolderName;
    Double balance;
    AccountEntityType accountEntityType;
}
