package nl.rabobank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.rabobank.mongo.entity.Access;
import nl.rabobank.mongo.repository.AccessRepository;

@Service
public class AccessService {

    @Autowired
    private AccessRepository accessRepository;

    @Autowired
    private NextSequenceService nextSequenceService;

    public void giveAccess (Access access){
        access.setId(nextSequenceService.generateSequence(Access.ACCESS_SEQUENCE));
        accessRepository.save(access);
    }





    
}
