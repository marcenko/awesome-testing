package de.cronos.awesometesting.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
@Slf4j
class IdGeneratorITest {

    @Mock
    private MathIsFun mathIsFun;

    @InjectMocks
    private IdGenerator idGenerator;

    @Test
    public void shouldReturnRandomId() {
        when(mathIsFun.getRandomNumber()).thenReturn(1);
        assertThat(idGenerator.getRandomIntegerId(), is(1));
    }
}