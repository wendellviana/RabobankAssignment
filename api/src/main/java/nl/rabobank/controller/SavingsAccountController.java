package nl.rabobank.controller;


import nl.rabobank.account.SavingsAccount;
import nl.rabobank.dto.AccessDTO;
import nl.rabobank.mongo.entity.Account;
import nl.rabobank.service.AccessService;
import nl.rabobank.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


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
            savings.add(new ModelMapper().map(a, SavingsAccount.class));
        }
        return savings;
    }

    @PostMapping("/access")
    @ResponseStatus(HttpStatus.CREATED)
    public AccessDTO giveAccess(@RequestBody AccessDTO access){
        return accessService.giveAccess(access, SAVINGS);
    }

    private SavingsAccount transformAccount(Account account){
        SavingsAccount sa = new SavingsAccount(account.getAccountNumber(), account.getAccountHolderName(), account.getBalance());
        return sa;
    }
    
}