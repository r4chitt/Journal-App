package net.rachit.Cache;

import net.rachit.entity.JournalappConfigEntity;
import net.rachit.repository.JournalappConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
public class AppCache {

    public enum keys{
        weather_api;
    }

    public Map<String,String> app_Cache = new HashMap<>();
   @Autowired
    private JournalappConfig journalappConfig;
    @PostConstruct
    public void init(){
        List<JournalappConfigEntity> all= journalappConfig.findAll();
        for(JournalappConfigEntity journalappConfigEntity : all)
        {
            app_Cache.put(journalappConfigEntity.getKey(), journalappConfigEntity.getValue());
        }

    }
}
