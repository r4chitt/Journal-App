package net.rachit.service;

import net.rachit.entity.JournalEntry;
import net.rachit.entity.User;
import net.rachit.repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepo JournalEntryRepo;

    private static final Logger logger = LoggerFactory.getLogger(JournalEntryService.class);

    @Autowired
    private UserService UserService;
    @Transactional
    public void SaveEntry(JournalEntry JournalEntry, String username)
    {
        try {
            JournalEntry.setDate(LocalDateTime.now());
            User user = UserService.findbyUserName(username);
            JournalEntry saved= JournalEntryRepo.save(JournalEntry);
            user.getJournalEntries().add(saved);
            UserService.SaveNewUser(user);
        } catch (Exception e) {
            logger.info("e");
        }
    }
    public void SaveEntry(JournalEntry JournalEntry)
    {
        JournalEntryRepo.save(JournalEntry);
    }
    public List<JournalEntry> getAll(){
        return JournalEntryRepo.findAll();

    }
    public  List<JournalEntry> findbyusername(String username){
     User user = UserService.findbyUserName(username);
      return user.getJournalEntries();
}   public Optional<JournalEntry> findById(ObjectId id){

        return JournalEntryRepo.findById(id);
    }
    @Transactional
    public void deleteById(ObjectId id, String username)
    {
        try {
            User user = UserService.findbyUserName(username);
            boolean removed = user.getJournalEntries().removeIf( x->x.getId().equals(id));
            if (removed) {
                UserService.SaveNewUser(user);
                JournalEntryRepo.deleteById(id);
            }
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while deleting the entry",e);
        }
    }
}
