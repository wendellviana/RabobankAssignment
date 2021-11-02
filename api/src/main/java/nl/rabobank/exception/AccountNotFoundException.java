package nl.rabobank.exception;

public class AccountNotFoundException extends RuntimeException{
    public AccountNotFoundException(String accountNumber){
        super("Could not find account " + accountNumber);
    }
    
}
