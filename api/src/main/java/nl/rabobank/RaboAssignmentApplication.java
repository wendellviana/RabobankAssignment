package nl.rabobank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import nl.rabobank.authorizations.Authorization;
import nl.rabobank.mongo.MongoConfiguration;
import nl.rabobank.mongo.entity.Access;
import nl.rabobank.mongo.entity.Account;
import nl.rabobank.mongo.repository.AccessRepository;
import nl.rabobank.mongo.repository.AccountRepository;
import nl.rabobank.service.NextSequenceService;

@SpringBootApplication
@Import(MongoConfiguration.class)
public class RaboAssignmentApplication implements CommandLineRunner {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccessRepository accessRepository;

    @Autowired
    private NextSequenceService nextSequenceService;

    public static void main(final String[] args)
    {
        System.setProperty("server.servlet.context-path", "/rabobank");
        SpringApplication.run(RaboAssignmentApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        
        accountRepository.deleteAll();
        accessRepository.deleteAll();
        
        // save a couple of accounts
        accountRepository.save(new Account("123", "Test", 3000.00, "payments"));
        accountRepository.save(new Account("1", "Test2", 4000.00, "payments"));
        accountRepository.save(new Account("2", "Test3", 53000.00, "payments"));
        accountRepository.save(new Account("3", "Test5", 63000.00, "payments"));
        accountRepository.save(new Account("5", "Test6", 8000.00, "payments"));
        accountRepository.save(new Account("6", "Test7", 9000.00, "payments"));

        accountRepository.save(new Account("10", "sav", 3000.00, "savings"));
        accountRepository.save(new Account("11", "sav2", 4000.00, "savings"));
        accountRepository.save(new Account("22", "sav3", 53000.00, "savings"));
        accountRepository.save(new Account("33", "sav5", 63000.00, "savings"));
        accountRepository.save(new Account("55", "sav6", 8000.00, "savings"));
        accountRepository.save(new Account("61", "sav7", 9000.00, "savings"));

        accessRepository.save(new Access(nextSequenceService.generateSequence(Access.ACCESS_SEQUENCE), "11", "Test1", "Test", Authorization.READ.toString()));
 
    }
}
