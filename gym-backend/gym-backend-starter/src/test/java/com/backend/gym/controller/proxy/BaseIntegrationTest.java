package com.backend.gym.controller.proxy;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SuppressWarnings("SpringJavaAutowiringInspection")
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
public class BaseIntegrationTest {
}
