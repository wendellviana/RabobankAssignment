package nl.rabobank.mongo.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Document(collection = "access")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Access {

    @Transient
    public static final String ACCESS_SEQUENCE = "access_sequence";

    @Id
    private Integer id;
    private String accountNumber;
    private String granteeName;
    private String grantorName; 
    private String authorization;

}
