package net.rachit.controller;

import net.rachit.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/_journal")
public class JournalEntryController {

    private Map<Long, JournalEntry> JournalEntries = new HashMap<>();

    @GetMapping
    public List<JournalEntry> getAll()
    {
        return new ArrayList<>(JournalEntries.values());
    }

    @PostMapping
    public boolean CreateEntry(@RequestBody JournalEntry Entry)
    {
        return  true;

    }
    @GetMapping("id/{myid}")

    public JournalEntry getjournalbyid(@PathVariable long myid)
    {
        return JournalEntries.get(myid);

    }
    @DeleteMapping("id/{myid}")
    public JournalEntry deletebyid(@PathVariable long myid)
    {
        return JournalEntries.remove(myid);

    }

    @PutMapping("id/{myid}")
    public JournalEntry updatebyid(@PathVariable long myid,@RequestBody JournalEntry Entry)
    {
        return JournalEntries.put(myid, Entry);
    }

}
