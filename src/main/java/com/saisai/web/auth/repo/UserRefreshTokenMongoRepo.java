package com.saisai.web.auth.repo;

import com.saisai.web.auth.api.UserRefreshToken;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRefreshTokenMongoRepo extends MongoRepository<UserRefreshToken, ObjectId>, UserRefreshTokenMongoRepoCustom {
}
