package junit_basics;

import dev.elshan.Calculator;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Order(1)
public class DemoRepeatedTest {


    Calculator calculator;

    @BeforeEach
    void beforeEachMethod(){
        calculator = new Calculator();
        System.out.println("Before each method");
    }

//    @RepeatedTest(5)
    @RepeatedTest(value = 3,name = "{displayName}, repetition: {currentRepetition}, totalReps: {totalRepetitions}")
    @DisplayName("Division 4/2")
    void testIntegerDivision_WhenTenIsDividedByTwo_ShouldReturnFive(RepetitionInfo repetitionInfo, TestInfo testInfo) {
        System.out.println(testInfo.getDisplayName());
        System.out.println(repetitionInfo.getCurrentRepetition());
        System.out.println(repetitionInfo.getTotalRepetitions());
        System.out.println("Running division to 2 test");

        // Arrange or Given
        int num1 = 10;
        int num2 = 2;

        // Act or When
        int i = calculator.integerDivision(num1, num2);

        // Assert or Then
        assertEquals(5,i,"The test failed because you have to multiply not divide");
    }

}
