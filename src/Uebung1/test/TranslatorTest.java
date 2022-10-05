package Uebung1.test;

import Uebung1.control.GermanTranslator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TranslatorTest {
    private final GermanTranslator germanTranslator = new GermanTranslator();

    @Test
    public void testWorking() {
        assertEquals("sieben",germanTranslator.translateNumber(7));
    }

    /**
     * Hier ist ein Boundries test nicht nötig. Der vollständigkeit nehme ich aber als Test mit auf
     */
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
        assertEquals("Übersetzung der Zahl 11 nicht möglich (1.0)",germanTranslator.translateNumber(11));
    }

    @Test
    public void testNegative(){
        assertEquals("Übersetzung der Zahl -1 nicht möglich (1.0)",germanTranslator.translateNumber(-1));
    }
}
