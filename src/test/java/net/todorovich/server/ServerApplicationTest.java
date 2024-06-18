package net.todorovich.server;

import lombok.extern.slf4j.Slf4j;

import net.todorovich.controller.DatabaseController;
import net.todorovich.controller.UserController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

@Slf4j
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ServerApplicationTest {

    @LocalServerPort
    private Integer port;

    @Autowired
    private DatabaseController controller1;

    @Autowired
    private UserController controller2;

    @Autowired
    private ApplicationContext applicationContext;

    @Test @DisplayName("Load Context")
    void contextLoads()
    {
        Assertions.assertNotNull(port);
        Assertions.assertNotNull(controller1);
        Assertions.assertNotNull(controller2);
        Assertions.assertNotNull(applicationContext);
    }
}