package nl.rabobank.mongo.dao;

import nl.rabobank.mongo.entity.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {

    Optional<Account> findById(String id);
    Optional<Account> findByAccountNumber(String accountNumber);
}
