package com.saisai.web.auth.repo;

import com.saisai.web.auth.api.UserRefreshToken;
import com.saisai.web.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class UserRefreshTokenMongoRepoImpl implements UserRefreshTokenMongoRepoCustom {
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public UserRefreshToken findByUserId(String userId){
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        return mongoTemplate.findOne(query, UserRefreshToken.class);
    }
    @Override
    public UserRefreshToken findByUserIdAndRefreshToken(String userId, String refreshToken){
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId).and("refreshToken").is(refreshToken));
        return mongoTemplate.findOne(query, UserRefreshToken.class);
    }
}
