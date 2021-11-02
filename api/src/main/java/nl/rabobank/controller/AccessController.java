package nl.rabobank.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.rabobank.account.PaymentAccount;
import nl.rabobank.authorizations.Authorization;
import nl.rabobank.authorizations.PowerOfAttorney;
import nl.rabobank.mongo.entity.Access;
import nl.rabobank.mongo.entity.Account;
import nl.rabobank.service.AccessService;
import nl.rabobank.service.AccountService;

@RequestMapping("/access")
@RestController
public class AccessController {

    private AccessService accessService; 
    private AccountService accountService;

    @Autowired
    public AccessController (AccessService accessService,AccountService accountService){
        this.accessService = accessService;
        this.accountService = accountService;
    }
   
    @GetMapping
    public List<PowerOfAttorney> getAll(){
        List<Access> accessList = accessService.findAll();
        List<PowerOfAttorney> pwList = new ArrayList<PowerOfAttorney>();
        for(Access access : accessList){
            pwList.add(transformAccess(access));
        }

        return pwList;
    }

    
    @GetMapping("/granteeName/{granteeName}")
    public List<PowerOfAttorney> accessByGranteeName(String granteeName){
        List<Access> accessList = accessService.findByGranteeName(granteeName);
        List<PowerOfAttorney> pwList = new ArrayList<PowerOfAttorney>();
        for(Access access : accessList){
            pwList.add(transformAccess(access));
        }

        return pwList;
    }

    @GetMapping("/grantorName/{grantorName}")
    public List<PowerOfAttorney> accessByGrantorName(String grantorName){
        List<Access> accessList = accessService.findByGrantorName(grantorName);
        List<PowerOfAttorney> pwList = new ArrayList<PowerOfAttorney>();
        for(Access access : accessList){
            pwList.add(transformAccess(access));
        }

        return pwList;
    }

    private PowerOfAttorney transformAccess (Access access){
        Account a = accountService.findByAccountNumber(access.getAccountNumber());
        PaymentAccount pa = transform(a);
        Authorization auth = access.getAuthorization().toUpperCase().equals("READ") ? Authorization.READ : Authorization.WRITE;

        PowerOfAttorney pw = new PowerOfAttorney(access.getGranteeName(), access.getGrantorName(), pa, auth);

        return pw;
    }

    private PaymentAccount transform(Account account){
        PaymentAccount sa = new PaymentAccount(account.getAccountNumber(), account.getAccountHolderName(), account.getBalance());
        return sa;
    }
}
