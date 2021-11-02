package nl.rabobank.service.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import nl.rabobank.mongo.entity.Account;
import nl.rabobank.mongo.entity.CustomSequences;
import nl.rabobank.service.HelpService;

@Service
public class HelpServiceImpl implements HelpService{

    private MongoOperations mongoOperations;

    @Autowired 
    public HelpServiceImpl(MongoOperations mongoOperations){
        this.mongoOperations = mongoOperations;
    }
    
    
    public int generateSequence(String seqName) {
        Query query = new Query(Criteria.where("id").is(seqName));
        Update update = new Update().inc("seq", 1);

        CustomSequences customSequences = mongoOperations.findAndModify(query, update, new FindAndModifyOptions().upsert(true).returnNew(true), CustomSequences.class);
        return !Objects.isNull(customSequences) ? customSequences.getSeq(): 1;
    }

    public Account getAccount(String accountNumber, String accountType){
        Query query = new Query(Criteria.where("accountNumber").is(accountNumber).and("accountType").is(accountType));
        return mongoOperations.findOne(query, Account.class);
    
    }


}

