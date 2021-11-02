package nl.rabobank.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import nl.rabobank.account.SavingsAccount;
import nl.rabobank.exception.AccountNotFoundException;
import nl.rabobank.mongo.entity.Access;
import nl.rabobank.mongo.entity.Account;
import nl.rabobank.service.AccessService;
import nl.rabobank.service.AccountService;


@RequestMapping("/savings")
@RestController
public class SavingsAccountController {

    private AccessService accessService; 
    private AccountService accountService;

    @Autowired
    public SavingsAccountController (AccessService accessService,AccountService accountService){
        this.accessService = accessService;
        this.accountService = accountService;
    }
   
    @GetMapping
    public List<SavingsAccount> getSavingsAccount(){
        final String SAVINGS = "savings";
        List<Account> accounts = accountService.findByAccountType(SAVINGS);
        List<SavingsAccount> savings = new ArrayList<SavingsAccount>();
        for(Account a : accounts){
            savings.add(transformAccount(a));
        }
        return savings;
    }

    @PostMapping("/access")
    @ResponseStatus(HttpStatus.CREATED)
    public void giveAccess(@RequestBody Access access){
        try{
            accessService.giveAccess(access);
        }catch(AccountNotFoundException ex){
            new AccountNotFoundException(access.getAccountNumber());
        }
    }

    private SavingsAccount transformAccount(Account account){
        SavingsAccount sa = new SavingsAccount(account.getAccountNumber(), account.getAccountHolderName(), account.getBalance());
        return sa;
    }
    
}