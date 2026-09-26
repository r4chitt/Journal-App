package net.rachit.controller;

import net.rachit.entity.JournalEntry;
import net.rachit.entity.User;
import net.rachit.service.JournalEntryService;
import net.rachit.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService JournalEntryService;

    @Autowired
    private UserService UserService;

    @GetMapping("")
    public ResponseEntity<?> getAllJournalEntriesOfUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();;
        User user = UserService.findbyUserName(username);
        List<JournalEntry> all = user.getJournalEntries();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public ResponseEntity<?> CreateEntry(@RequestBody JournalEntry Entry) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            User user = UserService.findbyUserName(username);

            JournalEntryService.SaveEntry(Entry, username);
            return new ResponseEntity<>(Entry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("id/{myid}")

    public ResponseEntity<JournalEntry> getjournalbyid(@PathVariable ObjectId myid) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = UserService.findbyUserName(username);
        Optional<JournalEntry> journalEntry = user.getJournalEntries()
                .stream()
                .filter(x -> x.getId().equals(myid))
                .findFirst();

        if (journalEntry.isPresent()) {
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{myid}")
    public ResponseEntity<?> deletebyid(@PathVariable ObjectId myid ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        JournalEntryService.deleteById(myid, username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);


    }

    @PutMapping("id/{myid}")
    public ResponseEntity<?> updatebyid(@PathVariable ObjectId myid, @RequestBody JournalEntry newEntry) {
       Authentication auth = SecurityContextHolder.getContext().getAuthentication();
       String username = auth.getName();
       User user = UserService.findbyUserName(username);
        Optional<JournalEntry> oldEntry = user.getJournalEntries()
                .stream()
                .filter(x -> x.getId().equals(myid))
                .findFirst();

        if (oldEntry.isPresent()) {
            JournalEntry entry = oldEntry.get();
            entry.setTitle(newEntry.getTitle());
            entry.setContent(newEntry.getContent());
            JournalEntryService.SaveEntry(entry);

            return new ResponseEntity<>(entry, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}


