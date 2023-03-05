package LearningPatterns.Composite;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

public class CompositeDocument extends AbstractDocument{
    private final List<Document> documents = new ArrayList<>();
    public CompositeDocument(){
        super();
    }

    public void addDocument(Document document){
        documents.add(document);
    }

    public void deleteDocument(){
        documents.remove(0);
    }

    public void operation(){
        for (Document document : documents){
            document.operation();
        }
    }

}
