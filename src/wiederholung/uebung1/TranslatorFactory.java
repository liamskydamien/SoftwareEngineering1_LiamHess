package wiederholung.uebung1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TranslatorFactory {
    public static Translator createGermanTranslator(){
        Translator translator = new GermanTranslator();
        translator.setDate(LocalDate.now().format(DateTimeFormatter.ofPattern("MMM/yy")));
        return translator;
    }
}
