package uebung9.Document;

public class TextDocument extends CoreDocument{

    String text;
    EncodingTypes encoding;

    public TextDocument(int id, String text, EncodingTypes encoding) {
        super(id);
        this.text = text;
        this.encoding = encoding;
    }

    @Override
    public int size() {
        try {
            return text.getBytes(encoding.getEncoding()).length;
        }
        catch (Exception e) {
            return 0;
        }
    }
}
