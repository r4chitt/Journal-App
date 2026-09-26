package net.rachit.entity;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Journalapp_config")
@Data

public class JournalappConfigEntity {

    private String key;
    private String value;
}
