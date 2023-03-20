package de.cronos.awesometesting.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
class MathIsFunITest {

    @Autowired
    private MathIsFun mathIsFun;

    private static Stream<Arguments> factorialInputProvider() {
        return Stream.of(
                Arguments.of(1, 1),
                Arguments.of(2, 2),
                Arguments.of(3, 6),
                Arguments.of(5, 120),
                Arguments.of(10, 3628800)
        );
    }

    @DisplayName("Should return a valid result for numbers greater than zero")
    @ParameterizedTest(name = "{index} => n={0}, factorial={1}")
    @MethodSource("factorialInputProvider")
    public void shouldReturnValidResultForValidInput(int n, int factorial) {
        assertThat(mathIsFun.factorial(n), is(factorial));
    }
}