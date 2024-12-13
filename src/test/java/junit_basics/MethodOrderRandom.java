package junit_basics;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

//@TestMethodOrder(MethodOrderer.Random.class)
//@TestMethodOrder(MethodOrderer.MethodName.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Order(3)
public class MethodOrderRandom {

    @Test
    @Order(1)
    void testB(){
        System.out.println("B");
    }

    @Test
    @Order(3)
    void testA(){
        System.out.println("A");
    }

    @Test
    @Order(2)
    void testC(){
        System.out.println("C");
    }
}
