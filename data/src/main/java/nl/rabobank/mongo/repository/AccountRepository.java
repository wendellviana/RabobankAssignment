package nl.rabobank.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import nl.rabobank.mongo.entity.Account;

public interface AccountRepository extends MongoRepository<Account, String> {


    List<Account> findByAccountType(String accountType);
    Account findByAccountNumber(String accountNumber);
}

