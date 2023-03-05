package LearningPatterns.Composite;

public class TextDocument extends CoreDocument{
    private final String text;

    public TextDocument(String text) {
        super();
        this.text = text;
    }

    @Override
    public void operation() {
        System.out.println(text);
    }
}
