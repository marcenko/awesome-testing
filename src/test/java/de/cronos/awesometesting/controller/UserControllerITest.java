package de.cronos.awesometesting.controller;

import de.cronos.awesometesting.db.model.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerITest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private static final String USER_FIRSTNAME = "John";
    private static final String USER_LASTNAME = "Doe";

    @Test
    public void shouldReturnUserForId() {
        UserDTO user = this.restTemplate.getForObject("http://localhost:" + port + "/api/user/1" ,UserDTO.class);

        assertThat(user, allOf(
                hasProperty("firstname", is(USER_FIRSTNAME)),
                hasProperty("lastname", is(USER_LASTNAME))
                )
        );
    }
}