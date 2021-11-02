package nl.rabobank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.rabobank.exception.AccountNotFoundBusinessException;
import nl.rabobank.mongo.entity.Access;
import nl.rabobank.mongo.entity.Account;
import nl.rabobank.mongo.repository.AccessRepository;
import nl.rabobank.mongo.repository.AccountRepository;

@Service
public class AccessService {

    private AccessRepository accessRepository;
    private NextSequenceService nextSequenceService;
    private AccountRepository accountRepository;

    @Autowired
    public AccessService(AccessRepository accessRepository,NextSequenceService nextSequenceService, AccountRepository accountRepository){
        this.accessRepository = accessRepository;
        this.accountRepository = accountRepository;
        this.nextSequenceService = nextSequenceService;
    }

    public void giveAccess (Access access){

        Account account = accountRepository.findByAccountNumber(access.getAccountNumber());
        if(account != null){
            access.setId(nextSequenceService.generateSequence(Access.ACCESS_SEQUENCE));
            accessRepository.save(access);
        }
        
    }

    public List<Access> findByGrantorName(String grantorName){
        return accessRepository.findByGrantorName(grantorName);
    }

    public List<Access> findByGranteeName(String granteeName){
        return accessRepository.findByGranteeName(granteeName);
    }

    public List<Access> findAll(){
        return accessRepository.findAll();
    }





    
}
