package nl.rabobank.service;

import nl.rabobank.authorizations.Authorization;
import nl.rabobank.dto.AccessDTO;
import nl.rabobank.exception.AccountNotFoundException;
import nl.rabobank.mongo.entity.Access;
import nl.rabobank.mongo.repository.AccessRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(MockitoExtension.class)
public class AccessServiceTest {

    @Mock
    private AccessRepository accessRepository;

    @Mock
    private HelpService helpService;

    @Autowired
    private AccessService accessService;

    @BeforeEach
    public void setUp(){
        Access access = new Access(1, "6", "Wendell", "Test", "READ");
        Mockito.when(accessRepository.save(access)).thenReturn(access);
        Mockito.when(helpService.getAccount("0", "payments")).thenReturn(null);
    }
    @Test
    void giveAccessValidAccount() {
        AccessDTO accessDTO = new AccessDTO("6", "Wendell", "Test", Authorization.READ.toString());
        AccessDTO returnDTO = accessService.giveAccess(accessDTO, "payments");
        Assertions.assertNotNull(returnDTO.getId());
    }
    @Test
    void giveAccessInvalidAccount(){
        AccessDTO accessDTO = new AccessDTO("0", "Wendell", "Test", Authorization.READ.toString());
        Assertions.assertThrows(AccountNotFoundException.class, () -> {
            accessService.giveAccess(accessDTO, "payments");
        });
    }
}