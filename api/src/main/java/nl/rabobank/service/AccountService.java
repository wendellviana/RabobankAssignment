package nl.rabobank.service;

import java.util.List;

import nl.rabobank.mongo.entity.Account;

public interface AccountService {
    public List<Account> findByAccountType(String accountType);
    public Account findByAccountNumber(String accountNumber);
    
}
