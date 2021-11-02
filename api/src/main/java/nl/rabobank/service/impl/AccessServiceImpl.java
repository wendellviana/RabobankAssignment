package nl.rabobank.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.rabobank.exception.AccountNotFoundException;
import nl.rabobank.mongo.entity.Access;
import nl.rabobank.mongo.entity.Account;
import nl.rabobank.mongo.repository.AccessRepository;
import nl.rabobank.service.AccessService;

@Service
public class AccessServiceImpl implements AccessService {

    private AccessRepository accessRepository;
    private HelpServiceImpl helpService;

    @Autowired
    public AccessServiceImpl(AccessRepository accessRepository,HelpServiceImpl helpService){
        this.accessRepository = accessRepository;
        this.helpService = helpService;
    }

    public void giveAccess(Access access, String accountType){

        Account account = helpService.getAccount(access.getAccountNumber(), accountType);
        if(account != null){
            access.setId(helpService.generateSequence(Access.ACCESS_SEQUENCE));
            accessRepository.save(access);
        }else{
            throw new AccountNotFoundException(access.getAccountNumber());
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
