package nl.rabobank.service;

import java.util.List;

import nl.rabobank.mongo.entity.Access;


public interface AccessService {
    public void giveAccess(Access access, String accountType);
    public List<Access> findByGrantorName(String grantorName);
    public List<Access> findByGranteeName(String granteeName);
    public List<Access> findAll();
    
}
