package com.saisai.web.auth.repo;

import com.saisai.web.user.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserMongoRepo extends MongoRepository<User, ObjectId>, UserMongoRepoCustom {
}
