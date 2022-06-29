package com.saisai.web.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
public class UserSideInfoMongoRepoImpl implements UserSideInfoMongoRepoCustom {
    private final MongoTemplate mongoTemplate;

    @Override
    public List<UserSideInfo> getUserSideInfo(User user) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(user.getUserId()));
        return mongoTemplate.find(query, UserSideInfo.class);
    }

    @Override
    public List<UserSideInfo> getUserSideInfoList(Set<String> users) {
        Query query = new Query();
        List<Criteria> criteriaList = new ArrayList<>();
        for(String user : users) {
            criteriaList.add(Criteria.where("userId").is(user));
        }
        query.addCriteria(Criteria.where("").orOperator(criteriaList));
        return mongoTemplate.find(query, UserSideInfo.class);
    }
}
