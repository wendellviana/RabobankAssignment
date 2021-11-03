package nl.rabobank.service;

import nl.rabobank.mongo.entity.Access;
import nl.rabobank.mongo.entity.Account;
import nl.rabobank.mongo.repository.AccountRepository;
import nl.rabobank.service.impl.AccessServiceImpl;
import nl.rabobank.service.impl.AccountServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountServiceTest {

    final String PAYMENTS = "payments";
    final String SAVINGS = "savings";

    @Mock
    private AccountRepository accountRepository;

    private AccountService accountService;

    private AutoCloseable closeable;

    @BeforeEach
    public void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        accountService = new AccountServiceImpl(accountRepository);
        Account sa = new Account("6", "TestSavings", 250.0, "savings");
        Account pa = new Account("10", "TestPayments", 300.0, "payments");
        Mockito.when(accountRepository.findByAccountType(SAVINGS)).thenReturn(List.of(sa));
        Mockito.when(accountRepository.findByAccountType(PAYMENTS)).thenReturn(List.of(pa));
        Mockito.when(accountRepository.findByAccountNumber("6")).thenReturn(sa);
        Mockito.when(accountRepository.findByAccountNumber("0")).thenReturn(null);
    }
    @AfterEach
    public void releaseMocks() throws Exception {
        closeable.close();
    }

    @Test
    public void findSavingsAccount() {
        List<Account> savingAccounts = accountService.findByAccountType(SAVINGS);
        Assertions.assertNotNull(savingAccounts.get(0));
    }

    @Test
    public void findPaymentsAccount() {
        List<Account> savingAccounts = accountService.findByAccountType(PAYMENTS);
        Assertions.assertNotNull(savingAccounts.get(0));
    }

    @Test
    public void existAccountNumber() {
        Account account = accountService.findByAccountNumber("6");
        Assertions.assertNotNull(account);
    }
    @Test
    public void notExistAccountNumber() {
        Account account = accountService.findByAccountNumber("0");
        Assertions.assertNull(account);
    }
}