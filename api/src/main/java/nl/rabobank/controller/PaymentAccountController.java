package nl.rabobank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import nl.rabobank.mongo.entity.Access;
import nl.rabobank.service.AccessService;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/payments")
@RestController
public class PaymentAccountController {

    @Autowired
    private AccessService accessService;

    @PostMapping("/access")
    @ResponseStatus(HttpStatus.CREATED)
    public void giveAccess(@RequestBody Access access) {
        accessService.giveAccess(access);
    }
    
}