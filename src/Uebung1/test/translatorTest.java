package Uebung1.test;

import Uebung1.control.GermanTranslator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class translatorTest {
    private final GermanTranslator germanTranslator = new GermanTranslator();

    @Test
    public void testWorking() {
        assertEquals("zwei", germanTranslator.translateNumber(2));
        assertEquals("fünf",germanTranslator.translateNumber(5));
        assertEquals("sieben",germanTranslator.translateNumber(7));
        assertEquals("neun",germanTranslator.translateNumber(9));
    }

    @Test
    public void testBoundries(){
        assertEquals("eins",germanTranslator.translateNumber(1));
        assertEquals("zehn",germanTranslator.translateNumber(10));
    }

    @Test
    public void testZero(){
        assertEquals("Übersetzung der Zahl 0 nicht möglich (1.0)",germanTranslator.translateNumber(0));
    }

    @Test
    public void testBigger(){
        assertEquals("Übersetzung der Zahl 100 nicht möglich (1.0)",germanTranslator.translateNumber(100));
        assertEquals("Übersetzung der Zahl 11 nicht möglich (1.0)",germanTranslator.translateNumber(11));
        assertEquals("Übersetzung der Zahl 1000000 nicht möglich (1.0)",germanTranslator.translateNumber(1000000));
    }

    @Test
    public void testNegative(){
        assertEquals("Übersetzung der Zahl -1 nicht möglich (1.0)",germanTranslator.translateNumber(-1));
        assertEquals("Übersetzung der Zahl -11 nicht möglich (1.0)",germanTranslator.translateNumber(-11));
        assertEquals("Übersetzung der Zahl -10000 nicht möglich (1.0)",germanTranslator.translateNumber(-10000));
    }
}
