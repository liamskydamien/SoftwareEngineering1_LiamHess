package uebung9.Document;

public enum EncodingTypes {
    UTF8("UTF-8"),
    UTF16("UTF-16"),
    UTF32("UTF-32");

    private final String encoding;

    EncodingTypes(String encoding) {
        this.encoding = encoding;
    }

    public String getEncoding() {
        return encoding;
    }
}
