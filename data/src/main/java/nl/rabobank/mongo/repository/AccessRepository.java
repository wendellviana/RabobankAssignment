package nl.rabobank.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import nl.rabobank.mongo.entity.Access;


public interface AccessRepository extends MongoRepository<Access, Integer>{    

    List<Access> findByGrantorName(String grantorName);
    List<Access> findByGranteeName(String granteeName);
    
}
