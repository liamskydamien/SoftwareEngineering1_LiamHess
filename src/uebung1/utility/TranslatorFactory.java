package uebung1.utility;

import uebung1.control.GermanTranslator;
/**
 * Factory Method Pattern
 */
public class TranslatorFactory{
    public static GermanTranslator createGermanTranslator(){
        return new GermanTranslator();
    }
}
