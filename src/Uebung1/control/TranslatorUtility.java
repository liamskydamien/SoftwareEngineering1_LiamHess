package Uebung1.control;

/**
 * Utility erzeugt ein static Objekt vom GermanTranslator welcher dann von Client aufgerufen werden kann ohne ein Objekt von GermanTranslator zu erzeugen.
 */
public class TranslatorUtility{
    private static final Translator translator = new GermanTranslator();

    /**
     * @param aNumber übergebener Wert
     * @return von GermanTranslator übersetzter Wert.
     */
    public static String translateNumber(int aNumber){
        return translator.translateNumber(aNumber);
    }
}
