package nl.rabobank.service;

import nl.rabobank.authorizations.Authorization;
import nl.rabobank.dto.AccessDTO;
import nl.rabobank.exception.AccountNotFoundException;
import nl.rabobank.mongo.entity.Access;
import nl.rabobank.mongo.entity.Account;
import nl.rabobank.mongo.repository.AccessRepository;
import nl.rabobank.service.impl.AccessServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AccessServiceTest {

    @Mock
    private AccessRepository accessRepository;

    @Mock
    private HelpService helpService;

    private AccessService accessService;

    private AutoCloseable closeable;

    @BeforeEach
    public void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        this.accessService = new AccessServiceImpl(accessRepository,helpService);
        Access access = new Access(1, "6", "Wendell", "Test", "READ");
        Mockito.when(accessRepository.save(access)).thenReturn(access);
        Mockito.when(helpService.getAccount("6", "payments")).thenReturn(new Account("6", "Wendell", 300.0, "payments"));
        Mockito.when(helpService.generateSequence(Access.ACCESS_SEQUENCE)).thenReturn(1);
        Mockito.when(helpService.getAccount("0", "payments")).thenReturn(null);
    }
    @AfterEach
    public void releaseMocks() throws Exception {
        closeable.close();
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