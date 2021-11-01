package nl.rabobank.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import nl.rabobank.mongo.entity.Account;

public interface AccountRepository extends MongoRepository<Account, String> {
}
