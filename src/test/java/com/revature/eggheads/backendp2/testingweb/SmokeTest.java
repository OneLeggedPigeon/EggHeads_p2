package com.revature.eggheads.backendp2.testingweb;

import static org.assertj.core.api.Assertions.assertThat;

import com.revature.eggheads.backendp2.controller.*;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

// https://spring.io/guides/gs/testing-web/
@SpringBootTest
public class SmokeTest {

    @Autowired
    private EggTemplateController eggTemplateController;

    @Autowired
    private EggImageController eggImageController;

    @Autowired
    private IncubatorController incubatorController;

    @Autowired
    private PetController petController;

    @Autowired
    private UserController userController;

    /**
     * Tests if all of the Controllers are formatted well enough be created
     */
    @Test
    public void contextLoads() throws Exception {
        assertThat(eggTemplateController).isNotNull();
        assertThat(eggImageController).isNotNull();
        assertThat(incubatorController).isNotNull();
        assertThat(petController).isNotNull();
        assertThat(userController).isNotNull();
    }
}