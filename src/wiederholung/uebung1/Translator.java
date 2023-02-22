package wiederholung.uebung1;

public interface Translator {
    double version = 1.0; // Version des Interface
    String date = "Okt/2022"; // Default-Wert

    /*
     * Uebersetzt eine numerische Zahl in eine String-basierte
     * Repraesentation gemaess der Spezifikation in der Aufgabe 1-2
     */
    public String translateNumber(int number);
    public void printInfo();
    public void setDate(String date);
}
