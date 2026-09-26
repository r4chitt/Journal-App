package net.rachit.journalApp.service;

import net.rachit.repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
public class UserServiceTests {
    @Autowired
    private UserRepo UserRepo;

    @Test
    public void findbyusernametest()
    {
        assertNotNull(UserRepo.findByUsername("Alex"));
    }

}
