package de.cronos.awesometesting.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.*;

@DisplayName("Testing the really fun math class")
@Slf4j
class MathIsFunTest {

    @BeforeAll
    static void setup() {
        log.info("@BeforeAll - executes once before all test methods in this class");
    }

    @BeforeEach
    void init() {
        log.info("@BeforeEach - executes before each test method in this class");
    }

    @AfterAll
    static void teardown() {
        log.info("@AfterAll - executes once after all test methods in this class");
    }

    @AfterEach
    void destroy() {
        log.info("@AfterEach - executes after each test method in this class");
    }

    // For JUnit 4: @Before, @BeforeClass, @After, @AfterClass

    private MathIsFun mathIsFun = new MathIsFun();

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

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5})
    public void resultShouldBeGreaterThanZero(int n) {
        assertThat(mathIsFun.factorial(n), greaterThan(0));
    }

    @Test
    public void shouldReturnOneForZero() {
        assertThat(mathIsFun.factorial(0), is(1));
    }

    @Test
    public void shouldThrowExceptionForNegativeNumbers() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            mathIsFun.factorial(-1);
        });

        String actualMessage = exception.getMessage();

        assertThat(actualMessage, equalTo("Factorial for negative numbers is not defined"));
    }

    /** For JUnit 4 with the same behavior
     @Rule
     public ExpectedException exceptionRule = ExpectedException.none();

     @Test
     public void shouldThrowExceptionForNegativeNumbers() {
         exceptionRule.expect(IllegalArgumentException.class);
         exceptionRule.expectMessage("Factorial for negative numbers is not defined");
         mathIsFun.factorial(-1);
     }

     // Without message
     @Test(expected = IllegalArgumentException.class)
     public void shouldThrowExceptionForNegativeNumbers() {
        mathIsFun.factorial(-1);
     }
     */

    @Test
    @Disabled("Not implemented yet")
    void doSomethingLater() {
    }

    @Test
    void trueAssumption() {
        int input = 5;
        assumeTrue(input > 0);
        assertEquals(mathIsFun.factorial(input), 120);
    }

    @Test
    void falseAssumption() {
        int input = -5;
        assumeTrue(input > 0);
        assertEquals(mathIsFun.factorial(input), 120);
    }
}