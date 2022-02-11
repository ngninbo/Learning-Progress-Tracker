
class CalculatorTest {
    
    private static final int EXPECTED = 3;
    
    void testAddition() {
        int actual = new Calculator().add(1, 2);
        Assertions.assertEquals(EXPECTED, actual);
    }
}
