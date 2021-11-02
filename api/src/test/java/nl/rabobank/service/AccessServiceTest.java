package nl.rabobank.service;

import nl.rabobank.authorizations.Authorization;
import nl.rabobank.dto.AccessDTO;
import nl.rabobank.exception.AccountNotFoundException;
import nl.rabobank.mongo.entity.Access;
import nl.rabobank.mongo.entity.Account;
import nl.rabobank.mongo.repository.AccessRepository;
import nl.rabobank.service.impl.AccessServiceImpl;
import org.bson.assertions.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.internal.Classes;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest

public class AccessServiceTest {

    @Autowired
    private AccessRepository accessRepository;

    @Autowired
    private HelpService helpService;

    @Autowired
    private AccessService accessService;

    @Test
    void giveAccessValidAccount() {
        AccessDTO accessDTO = new AccessDTO("6", "Wendell", "Test", Authorization.READ.toString());
        AccessDTO returnDTO = accessService.giveAccess(accessDTO, "payments");
        Assertions.notNull("Acces id", returnDTO.getId());
    }
    @Test
    void giveAccessInvalidAccount(){
        AccessDTO accessDTO = new AccessDTO("00", "Wendell", "Test", Authorization.READ.toString());
        Assert.assertThrows(AccountNotFoundException.class, () -> {
            accessService.giveAccess(accessDTO, "payments");
        });
    }
}