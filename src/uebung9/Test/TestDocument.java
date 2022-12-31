package uebung9.Test;

import uebung9.Document.ComplexDocument;
import uebung9.Document.EncodingTypes;
import uebung9.Document.GraphicDocument;
import uebung9.Document.TextDocument;

public class TestDocument {
    public static void main(String[] args) {
        ComplexDocument main = new ComplexDocument(1);
        main.add(new TextDocument(2, "Hello World", EncodingTypes.UTF8));
        ComplexDocument secondComp = new ComplexDocument(3);
        secondComp.add(new TextDocument(3,"Test 123", EncodingTypes.UTF32));
        secondComp.add(new GraphicDocument(4, "localhost:8080"));
        main.add(secondComp);
        System.out.println(main.size());
    }
}
