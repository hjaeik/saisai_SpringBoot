package com.saisai.web.chat;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatMongoRepo extends MongoRepository<ChatMessage, ObjectId>, ChatMongoRepoCustom {}
