package nl.rabobank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.rabobank.mongo.entity.Account;
import nl.rabobank.mongo.repository.AccountRepository;

@Service
public class AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountService (AccountRepository accountRepository){
        this.accountRepository = accountRepository;

    }


    public List<Account> findByAccountType(String accountType){
        return accountRepository.findByAccountType(accountType);
    }

    public Account findByAccountNumber(String accountNumber){
        return accountRepository.findByAccountNumber(accountNumber);
    }

}