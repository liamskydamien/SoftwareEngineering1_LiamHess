package LearningPatterns.ReportPublisher;

public class CompanyReport implements Report{

    private int id;
    private String companyName;
    private String news;

    public CompanyReport(int id, String companyName, String news) {
        this.id = id;
        this.companyName = companyName;
        this.news = news;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public void setID(int id) {
        this.id = id;
    }
}
