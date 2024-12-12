import dev.elshan.Calculator;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Test Math operations in calculator class")
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

    @Test
    @DisplayName("Subtraction 10 - 2")
    void testSubtraction(){
        System.out.println("Running subtraction test");
        int i = calculator.integerSubtraction(10, 2);
        assertEquals(8,i,() -> "The test failed because you have to subtract not sum");
    }
}
