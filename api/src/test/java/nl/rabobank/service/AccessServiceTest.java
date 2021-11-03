package nl.rabobank.service;

import nl.rabobank.authorizations.Authorization;
import nl.rabobank.dto.AccessDTO;
import nl.rabobank.exception.AccountNotFoundException;
import nl.rabobank.mongo.repository.AccessRepository;
import org.bson.assertions.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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