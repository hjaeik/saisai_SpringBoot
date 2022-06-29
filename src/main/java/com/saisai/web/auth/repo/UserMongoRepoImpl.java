package com.saisai.web.auth.repo;

import com.saisai.web.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class UserMongoRepoImpl implements UserMongoRepoCustom {
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public User findByUserId(String userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        return mongoTemplate.findOne(query, User.class);
    }
}
