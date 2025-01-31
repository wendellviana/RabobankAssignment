package nl.rabobank.service;

import java.util.List;

import nl.rabobank.dto.AccessDTO;
import nl.rabobank.mongo.entity.Access;


public interface AccessService {
    public AccessDTO giveAccess(AccessDTO access, String accountType);
    public List<Access> findByGrantorName(String grantorName);
    public List<Access> findByGranteeName(String granteeName);
    public List<Access> findAll();
    
}
