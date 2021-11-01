package nl.rabobank.mongo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "account")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    private String accountNumber;
    private String accountHolderName; 
    private Double balance;
    private String accountType;    
}
