package nl.rabobank.mongo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Value;

@Document(collection = "customSequences")
@Value
public class CustomSequences {
    @Id
    private String id;
    private int seq;
    
}
