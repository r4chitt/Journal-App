package net.rachit.repository;

import net.rachit.entity.JournalappConfigEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalappConfig extends MongoRepository<JournalappConfigEntity, ObjectId> {
}
