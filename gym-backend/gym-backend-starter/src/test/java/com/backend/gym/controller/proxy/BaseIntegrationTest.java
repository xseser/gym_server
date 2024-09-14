package com.backend.gym.controller.proxy;

import com.backend.gym.controller.proxy.config.TestConfig;
import com.gym.user.registration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;


@SuppressWarnings("SpringJavaAutowiringInspection")
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestConfig.class)
public class BaseIntegrationTest {

    @Autowired
    protected ControllerProxyService controllerProxyService;

    @Autowired
    protected UserRepository userRepository;
}
