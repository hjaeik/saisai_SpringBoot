package com.saisai.web.user;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserSideInfoMongoRepo extends MongoRepository<UserSideInfo, ObjectId>, UserSideInfoMongoRepoCustom {}
