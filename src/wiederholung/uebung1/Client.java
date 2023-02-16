package wiederholung.uebung1;

public class Client {
    /*
     * Methode zur Ausgabe einer Zahl auf der Console
     * (auch bezeichnet als CLI, Terminal)
     *
     */
    public void display( int aNumber ){
        // In dieser Methode soll die Methode translateNumber
        // mit dem übergegebenen Wert der Variable aNumber
        // aufgerufen werden.
        //
        // Strenge Implementierung gegen das Interface Translator gewuenscht!

        Translator translator = TranslatorFactory.createGermanTranslator();
        String result = translator.translateNumber(3);

        // translator = TranslatorFactory.createEnglishTranslator();


        translator.printInfo();

        System.out.println("Das Ergebnis der Berechnung: " +
                "[das Ergebnis an dieser Stelle]" + result);

    }
}
