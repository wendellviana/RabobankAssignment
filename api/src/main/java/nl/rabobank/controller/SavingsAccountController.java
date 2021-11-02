package nl.rabobank.controller;


import java.util.ArrayList;
import java.util.List;

import nl.rabobank.dto.AccessDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import nl.rabobank.account.SavingsAccount;
import nl.rabobank.mongo.entity.Access;
import nl.rabobank.mongo.entity.Account;
import nl.rabobank.service.AccessService;
import nl.rabobank.service.AccountService;


@RequestMapping("/savings")
@RestController
public class SavingsAccountController {

    private AccessService accessService; 
    private AccountService accountService;

    private final String SAVINGS = "savings";

    @Autowired
    public SavingsAccountController (AccessService accessService,AccountService accountService){
        this.accessService = accessService;
        this.accountService = accountService;
    }
   
    @GetMapping
    public List<SavingsAccount> getSavingsAccount(){
        List<Account> accounts = accountService.findByAccountType(SAVINGS);
        List<SavingsAccount> savings = new ArrayList<SavingsAccount>();
        for(Account a : accounts){
            savings.add(transformAccount(a));
        }
        return savings;
    }

    @PostMapping("/access")
    @ResponseStatus(HttpStatus.CREATED)
    public void giveAccess(@RequestBody AccessDTO access){
        accessService.giveAccess(access, SAVINGS);
    }

    private SavingsAccount transformAccount(Account account){
        SavingsAccount sa = new SavingsAccount(account.getAccountNumber(), account.getAccountHolderName(), account.getBalance());
        return sa;
    }
    
}