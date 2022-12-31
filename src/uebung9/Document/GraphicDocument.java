package uebung9.Document;

public class GraphicDocument extends CoreDocument{

    String url;

    public GraphicDocument(int id, String url) {
        super(id);
        this.url = url;
    }

    @Override
    public int size() {
        return 1200;
    }
}
