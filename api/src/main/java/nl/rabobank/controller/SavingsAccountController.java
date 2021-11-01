package nl.rabobank.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import nl.rabobank.mongo.entity.Access;
import nl.rabobank.service.AccessService;


@RequestMapping("/savings")
@RestController
public class SavingsAccountController {

    @Autowired
    private AccessService accessService;

    @PostMapping("/access")
    @ResponseStatus(HttpStatus.CREATED)
    public void giveAccess(@RequestBody Access access){
        accessService.giveAccess(access);
    }
    
}