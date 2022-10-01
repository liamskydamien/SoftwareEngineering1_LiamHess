package Uebung1.view;

import Uebung1.control.TranslatorUtility;

public class Client {

		/*
		 * Methode zur Ausgabe einer Zahl auf der Console (auch bezeichnet als CLI, Terminal)
		 */
		public void display( int aNumber ){
			// In dieser Methode soll die Methode translateNumber
			// mit dem übergegebenen Wert der Variable aNumber
			// aufgerufen werden.
			//
			// Strenge Implementierung gegen das Interface Translator gewuenscht!

			//Implementierung mittels Utitlity Klasse
			String translatedNumber = TranslatorUtility.translateNumber(aNumber);
			System.out.println("Das Ergebnis der Berechnung: " + translatedNumber);
		}
}





