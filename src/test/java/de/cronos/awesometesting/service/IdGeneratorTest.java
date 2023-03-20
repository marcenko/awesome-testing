package de.cronos.awesometesting.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Slf4j
class IdGeneratorTest {

    @Mock
    private MathIsFun mathIsFun;

    @InjectMocks
    private IdGenerator idGenerator;

    @Test
    public void shouldReturnRandomId() {
        when(mathIsFun.getRandomNumber()).thenReturn(1);
        assertThat(idGenerator.getRandomIntegerId(), is(1));
    }

    @Test
    public void shouldReturnRealRandomId() {
        when(mathIsFun.getRandomNumber()).thenCallRealMethod();
        int randomId = idGenerator.getRandomIntegerId();
        log.info("Random Id is: %d".formatted(randomId));
        assertThat(randomId, is(instanceOf(Integer.class)));
    }

    @Test
    public void returnDifferentIdForFirstCall() {
        when(mathIsFun.getRandomNumber())
                .thenReturn(1)
                .thenReturn(2)
                .thenCallRealMethod();

        // First call
        assertThat(mathIsFun.getRandomNumber(), is(1));
        // Second call
        assertThat(mathIsFun.getRandomNumber(), is(2));

        // All other calls
        int randomId = idGenerator.getRandomIntegerId();
        log.info("Random Id is: %d".formatted(randomId));
    }

    @Test
    public void shouldThrowException() {
        when(mathIsFun.getRandomNumber()).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> {
            mathIsFun.getRandomNumber();
        });
    }
}