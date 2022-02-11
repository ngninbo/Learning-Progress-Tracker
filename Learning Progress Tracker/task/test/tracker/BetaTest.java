package tracker;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class BetaTest {

    static int number = 14;

    BetaTest() {
        number *= 2;
    }

    @BeforeAll // is executed before the constructor
    static void method3() {
        number += 11;
    }

    @BeforeEach
    void method2() {
        number -= 4;
    }

    @AfterAll
    static void method4() {
        number /= 3;
    }

    @AfterEach
    void method5() {
        System.out.println(number);
    }

    @Test
    void method6() {
        number += 9;
    }
}