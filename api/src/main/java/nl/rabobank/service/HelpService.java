package nl.rabobank.service;

import nl.rabobank.mongo.entity.Account;


public interface HelpService {
    public int generateSequence(String seqName);
    public Account getAccount(String accountNumber, String accountType);

    
}
