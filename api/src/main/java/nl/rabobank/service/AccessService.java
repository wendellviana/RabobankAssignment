package nl.rabobank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.rabobank.mongo.entity.Access;
import nl.rabobank.mongo.entity.Account;
import nl.rabobank.mongo.repository.AccessRepository;
import nl.rabobank.mongo.repository.AccountRepository;

@Service
public class AccessService {

    @Autowired
    private AccessRepository accessRepository;

    @Autowired
    private NextSequenceService nextSequenceService;

    @Autowired
    private AccountRepository accountRepository;

    public void giveAccess (Access access){

        Optional<Account> optAccount = accountRepository.findById(access.getAccountNumber());
        if(optAccount.isPresent()){
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
