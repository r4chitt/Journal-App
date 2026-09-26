package net.rachit.repository;

import net.rachit.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, ObjectId> {
  User findByUsername(String username);

    void deleteByUsername(String username);
}
