package com.saisai.web.order;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderMongoRepo extends MongoRepository<Order, Long>, OrderMongoRepoCustom {

}
