package nl.rabobank.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.rabobank.mongo.entity.Account;
import nl.rabobank.mongo.repository.AccountRepository;
import nl.rabobank.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl (AccountRepository accountRepository){
        this.accountRepository = accountRepository;

    }


    public List<Account> findByAccountType(String accountType){
        return accountRepository.findByAccountType(accountType);
    }

    public Account findByAccountNumber(String accountNumber){
        return accountRepository.findByAccountNumber(accountNumber);
    }

}