package wiederholung.uebung1;

import java.util.HashMap;

public class GermanTranslator implements Translator{
    public String date;
    /**
     * Methode zur Übersetzung einer Zahl in eine String-Repraesentation
     */
    public String translateNumber( int number ) {
        String[] strings = {"eins", "zwei", "drei", "vier", "fünf", "sechs", "sieben", "acht", "neun", "zehn"};
        return strings[number-1];
    }

    /**
     * Objektmethode der Klasse GermanTranslator zur Ausgabe einer Info.
     */
    public void printInfo(){
        System.out.println( "GermanTranslator v1.9, erzeugt am " + this.date );
    }

    /**
     * Setzen des Datums, wann der Uebersetzer erzeugt wurde (Format: Monat/Jahr (Beispiel: "Okt/2022"))
     * Das Datum sollte system-intern durch eine Control-Klasse gesetzt werden und nicht von externen View-Klassen
     */
    public void setDate( String date ) {
        this.date = date;
    }
}
