package uebung1.test;

import uebung1.control.GermanTranslator;
import uebung1.control.Translator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GermanTranslatorTest {
    private final GermanTranslator translator = new GermanTranslator();

    @Test
    public void testWorking() {
        assertEquals("sieben", translator.translateNumber(7));
    }

    /**
     * Hier ist ein Boundries test nicht nötig. Der vollständigkeit nehme ich aber als Test mit auf
     */
    @Test
    public void testBoundries(){
        assertEquals("eins", translator.translateNumber(1));
        assertEquals("zehn", translator.translateNumber(10));
    }

    @Test
    public void testZero(){
        assertEquals("Übersetzung der Zahl 0 nicht möglich (1.0)", translator.translateNumber(0));
    }

    @Test
    public void testBigger(){
        assertEquals("Übersetzung der Zahl 11 nicht möglich (1.0)", translator.translateNumber(11));
    }

    @Test
    public void testNegative(){
        assertEquals("Übersetzung der Zahl -1 nicht möglich (1.0)", translator.translateNumber(-1));
    }
}
