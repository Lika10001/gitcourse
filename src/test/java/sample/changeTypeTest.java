package sample;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class changeTypeTest {
    @Test
    void testInputPathToFile() {
        String expected = "in.txt";
        String actual;
        actual = ChangeType.inputPathToFile();
        assertEquals(expected, actual);
    }
    @Test
    void testCalculate1() {
        int a = 6;
        int b = 10;
        int actual;
        int expected = 16;
        actual = ChangeType.calculate1();
        assertEquals(expected, actual);
    }
    @Test
    void testCalculate2() {
        int actual;
        int expected = 18;
        actual = ChangeType.calculate2();
        assertEquals(expected, actual);
    }
    @Test
    void testCalculate3() {
        int actual;
        int expected = 20;
        actual = ChangeType.calculate3();
        assertEquals(expected, actual);
    }
    @Test
    void testCalculate4() {
        int actual;
        int expected = 22;
        actual = ChangeType.calculate4();
        assertEquals(expected, actual);
    }
    @Test
    void testCalculate5() {
        int actual;
        int expected = 24;
        actual = ChangeType.calculate5();
        assertEquals(expected, actual);
    }
    @Test
    void testCalculate6() {
        int actual;
        int expected = 26;
        actual = ChangeType.calculate6();
        assertEquals(expected, actual);
    }
    @Test
    void testCalculate7() {
        int actual;
        int expected = 28;
        actual = ChangeType.calculate7();
        assertEquals(expected, actual);
    }
    @Test
    void testCalculate8() {
        int actual;
        int expected = 30;
        actual = ChangeType.calculate8();
        assertEquals(expected, actual);
    }
    @Test
    void testCalculate9() {
        int actual;
        int expected = 32;
        actual = ChangeType.calculate9();
        assertEquals(expected, actual);
    }
    @Test
    void testCalculate10() {
        int actual;
        int expected = 34;
        actual = ChangeType.calculate10();
        assertEquals(expected, actual);
    }
    @Test
    void testCalculate11() {
        int actual;
        int expected = 36;
        actual = ChangeType.calculate11();
        assertEquals(expected, actual);
    }
    @Test
    void testCalculate12() {
        int actual;
        int expected = 38;
        actual = ChangeType.calculate12();
        assertEquals(expected, actual);
    }
    @Test
    void testCalculate13() {
        int actual;
        int expected = 40;
        actual = ChangeType.calculate13();
        assertEquals(expected, actual);
    }
    @Test
    void testCalculate14() {
        int actual;
        int expected = 42;
        actual = ChangeType.calculate14();
        assertEquals(expected, actual);
    }
    @Test
    void testCalculate15() {
        int actual;
        int expected = 44;
        actual = ChangeType.calculate15();
        assertEquals(expected, actual);
    }
}
