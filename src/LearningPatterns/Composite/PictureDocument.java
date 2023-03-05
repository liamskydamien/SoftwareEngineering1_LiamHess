package LearningPatterns.Composite;

public class PictureDocument extends CoreDocument{

    private final String altText;

    public PictureDocument(String altText){
        super();
        this.altText = altText;
    }

    @Override
    public void operation() {
        System.out.println(altText);
    }
}
