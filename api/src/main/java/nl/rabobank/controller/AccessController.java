package nl.rabobank.controller;

import nl.rabobank.account.PaymentAccount;
import nl.rabobank.authorizations.Authorization;
import nl.rabobank.authorizations.PowerOfAttorney;
import nl.rabobank.mongo.entity.Access;
import nl.rabobank.mongo.entity.Account;
import nl.rabobank.service.AccessService;
import nl.rabobank.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
    public List<PowerOfAttorney> accessByGranteeName(@PathVariable String granteeName){
        List<Access> accessList = accessService.findByGranteeName(granteeName);
        List<PowerOfAttorney> pwList = new ArrayList<PowerOfAttorney>();
        for(Access access : accessList){
            pwList.add(transformAccess(access));
        }

        return pwList;
    }

    @GetMapping("/grantorName/{grantorName}")
    public List<PowerOfAttorney> accessByGrantorName(@PathVariable String grantorName){
        List<Access> accessList = accessService.findByGrantorName(grantorName);
        List<PowerOfAttorney> pwList = new ArrayList<PowerOfAttorney>();
        for(Access access : accessList){
            pwList.add(transformAccess(access));
        }

        return pwList;
    }

    private PowerOfAttorney transformAccess (Access access){
        Account a = accountService.findByAccountNumber(access.getAccountNumber());
        PaymentAccount pa = new ModelMapper().map(a, PaymentAccount.class);
        Authorization auth = access.getAuthorization().toUpperCase().equals("READ") ? Authorization.READ : Authorization.WRITE;

        PowerOfAttorney pw = new PowerOfAttorney(access.getGranteeName(), access.getGrantorName(), pa, auth);

        return pw;
    }
}
