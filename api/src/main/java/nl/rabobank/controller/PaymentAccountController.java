package nl.rabobank.controller;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import nl.rabobank.account.PaymentAccount;
import nl.rabobank.mongo.entity.Access;
import nl.rabobank.mongo.entity.Account;
import nl.rabobank.service.AccessService;
import nl.rabobank.service.AccountService;


@RequestMapping("/payments")
@RestController
public class PaymentAccountController {

    private AccessService accessService; 
    private AccountService accountService;

    @Autowired
    public PaymentAccountController(AccessService accessService,AccountService accountService){
        this.accessService = accessService;
        this.accountService = accountService;
    }

    @GetMapping
    public List<PaymentAccount> getSavingsAccount(){
        final String PAYMENTS = "payments";
        List<Account> accounts = accountService.findByAccountType(PAYMENTS);
        List<PaymentAccount> payments = new ArrayList<PaymentAccount>();
        for(Account a : accounts){
            payments.add(transform(a));
        }
        return payments;
    }

    @PostMapping("/access")
    @ResponseStatus(HttpStatus.CREATED)
    public void giveAccess(@RequestBody Access access) {
       accessService.giveAccess(access);     
    }
    private PaymentAccount transform(Account account){
        PaymentAccount sa = new PaymentAccount(account.getAccountNumber(), account.getAccountHolderName(), account.getBalance());
        return sa;
    }

   
    
}