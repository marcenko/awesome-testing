package de.cronos.awesometesting.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.cronos.awesometesting.db.UserRepository;
import de.cronos.awesometesting.db.model.User;
import de.cronos.awesometesting.db.model.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.Optional;

import static de.cronos.awesometesting.NoInvalidChars.noInvalidChars;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserController.class)
class UserControllerTest {

    private static final long USER_ID = 1337L;
    private static final String USER_FIRSTNAME = "John";
    private static final String USER_LASTNAME = "Doe";
    private static final User USER = User.builder()
            .id(USER_ID)
            .firstname(USER_FIRSTNAME)
            .lastname(USER_LASTNAME)
            .build();

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(USER));
    }

    @MockBean
    UserRepository userRepository;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void shouldReturnUserForId() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/user/%d".formatted(USER_ID)))
                .andExpect(status().isOk())
                .andReturn();

        UserDTO user = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), UserDTO.class);

        assertThat(Arrays.asList(user), anyOf(
                hasItem(
                        allOf(
                                hasProperty("firstname", is(USER_FIRSTNAME)),
                                hasProperty("lastname", is(USER_LASTNAME))
                        )
                )
        ));

        assertThat(user.getFirstname(), noInvalidChars());
//        assertThat("Jo#hn", noInvalidChars());

        verify(userRepository, times(1)).findById(USER_ID);
    }

    @Test
    public void shouldReturnNotFoundForInvalidId() throws Exception {
        mockMvc.perform(get("/api/user/%d".formatted(100)))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    public void shouldCreateNewUser() throws Exception {
        UserDTO userDTO = UserDTO.builder().firstname(USER_FIRSTNAME).lastname(USER_LASTNAME).build();

        mockMvc.perform(
                post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO))
                )
                .andExpect(status().isCreated())
                .andReturn();
        verify(userRepository).save(any());
    }
}