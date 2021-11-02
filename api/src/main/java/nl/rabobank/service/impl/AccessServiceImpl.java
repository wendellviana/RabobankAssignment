package nl.rabobank.service.impl;

import java.util.List;

import nl.rabobank.dto.AccessDTO;
import nl.rabobank.service.HelpService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.rabobank.exception.AccountNotFoundException;
import nl.rabobank.mongo.entity.Access;
import nl.rabobank.mongo.entity.Account;
import nl.rabobank.mongo.repository.AccessRepository;
import nl.rabobank.service.AccessService;
import org.springframework.ui.ModelMap;

@Service
public class AccessServiceImpl implements AccessService {

    private AccessRepository accessRepository;
    private HelpService helpService;

    @Autowired
    public AccessServiceImpl(AccessRepository accessRepository, HelpService helpService){
        this.accessRepository = accessRepository;
        this.helpService = helpService;
    }

    public AccessDTO giveAccess(AccessDTO accessDTO, String accountType){
        ModelMapper model = new ModelMapper();
        Account account = helpService.getAccount(accessDTO.getAccountNumber(), accountType);
        if(account != null){
            accessDTO.setId(helpService.generateSequence(Access.ACCESS_SEQUENCE));
            Access access = model.map(accessDTO, Access.class);
            accessRepository.save(access);
        }else{
            throw new AccountNotFoundException(accessDTO.getAccountNumber());
        }
        return accessDTO;

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
