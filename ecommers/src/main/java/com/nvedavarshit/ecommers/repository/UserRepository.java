package com.nvedavarshit.ecommers.repository;

import com.nvedavarshit.ecommers.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
