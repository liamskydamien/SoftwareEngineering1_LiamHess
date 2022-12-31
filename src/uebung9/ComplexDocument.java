package uebung9;

import java.util.ArrayList;
import java.util.List;

public class ComplexDocument extends DocumentImplementation{

    List<Document> inhalt = new ArrayList<>();

    public ComplexDocument(int id) {
        super(id);
    }

    public void add(Document newDoc){
        inhalt.add(newDoc);
    }

    public void remove(int id){
        inhalt = inhalt.stream().filter(document -> document.get() != id).toList();
    }

    @Override
    public int size() {
        return traverse();
    }

    private int traverse(){
        return inhalt.stream().reduce(0, (partialResult, document) -> partialResult + document.size(), Integer::sum);
    }
}
