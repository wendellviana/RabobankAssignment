package nl.rabobank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessDTO {
    private Integer id;
    private String accountNumber;
    private String granteeName;
    private String grantorName;
    private String authorization;

    public AccessDTO(String accountNumber, String granteeName, String grantorName, String authorization){
        this.accountNumber = accountNumber;
        this.granteeName = granteeName;
        this.grantorName = grantorName;
        this.authorization = authorization;
    }
}
