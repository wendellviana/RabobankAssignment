package nl.rabobank.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import nl.rabobank.mongo.entity.Access;


public interface AccessRepository extends MongoRepository<Access, Integer>{    
}
