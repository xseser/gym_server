package com.backend.gym.controller.proxy;

import com.gym.user.registration.model.User;
import com.gym.user.registration.repository.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SuppressWarnings("SpringJavaAutowiringInspection")
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
public class ControllerProxyServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test_integration() {
        User user = new User();
        user.setMail("dnjasdlska");
        user.setId(UUID.randomUUID());
        userRepository.save(user);
        User user1 = userRepository.findByMail("dnjasdlska").orElseGet(null);

        Assert.assertNotEquals(user1, null);
        Assert.assertEquals(user1, user);
    }

}