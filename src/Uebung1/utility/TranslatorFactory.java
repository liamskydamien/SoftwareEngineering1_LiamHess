package Uebung1.utility;

import Uebung1.control.GermanTranslator;
/**
 * Factory Method Pattern
 */
public class TranslatorFactory{
    public static GermanTranslator createGermanTranslator(){
        return new GermanTranslator();
    }
}
