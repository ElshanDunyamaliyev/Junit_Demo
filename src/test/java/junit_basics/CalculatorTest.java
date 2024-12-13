package junit_basics;

import dev.elshan.Calculator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test Math operations in calculator class")
@Order(2)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CalculatorTest {

    Calculator calculator;

    @BeforeAll
    static void setup(){
        System.out.println("Before All");
    }

    @AfterAll
    static void cleanup(){
        System.out.println("After All");
    }

    @BeforeEach
    void beforeEachMethod(){
        System.out.println("Before Each");
        calculator = new Calculator();
    }

    @AfterEach
    void afterEachMethod(){
        System.out.println("After Each");
    }

//    @Disabled("Todo: write test for this method")
    @Test
    @DisplayName("Division 4/2")
    void testIntegerDivision_WhenTenIsDividedByTwo_ShouldReturnFive(){
        System.out.println("Running division to 2 test");

        // Arrange or Given
        int num1 = 10;
        int num2 = 2;

        // Act or When
        int i = calculator.integerDivision(num1, num2);

        // Assert or Then
        assertEquals(5,i,"The test failed because you have to multiply not divide");
    }

    @Test
    @DisplayName("Division by zero")
    void testIntegerDivision_WhenDividendIsDividedByZero_ShouldThrowArithmeticException(){
        System.out.println("Running division to 0 test");
        assertThrows(ArithmeticException.class,() -> calculator.integerDivision(10, 0),"The test failed because you have divided to zero");
    }

    @ParameterizedTest
    @ValueSource(strings = {"salam","sagol","as"})
    void testValueSource(String value){
        System.out.println(value);
        assertNotNull(value,"string is null");
    }

    @DisplayName("Subtraction 10 - 2")
    @ParameterizedTest
//    @MethodSource("integerSubtractionInputParameters") // Eger argument getiren method adi test method adi ile eynidise ad yazmaga ehtiyac yoxdur.
//    @MethodSource() // Eger argument getiren method adi test method adi ile eynidise ad yazmaga ehtiyac yoxdur.
//    @CsvSource({"33,1,32","12,4,8"})
    @CsvFileSource(resources = "./integerSubtraction.csv")
    void testSubtraction(int num1, int num2, int expectedResult){
        System.out.println("Running subtraction test");
        int i = calculator.integerSubtraction(num1, num2);
        assertEquals(expectedResult,i,() -> "The test failed because you have to subtract not sum");
    }

    private static Stream<Arguments> testSubtraction(){
        return Stream.of(
                Arguments.of(10,2,8),
                Arguments.of(15,11,4),
                Arguments.of(19,2,17)
        );
    }

//    private static Stream<Arguments> integerSubtractionInputParameters(){
//        return Stream.of(
//                Arguments.of(10,2,8),
//                Arguments.of(15,11,4),
//                Arguments.of(19,2,17)
//        );
//    }
}
