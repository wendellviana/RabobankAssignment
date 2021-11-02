package nl.rabobank.mongo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "customSequences")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomSequences {
    @Id
    private String id;
    private int seq;
    
}
