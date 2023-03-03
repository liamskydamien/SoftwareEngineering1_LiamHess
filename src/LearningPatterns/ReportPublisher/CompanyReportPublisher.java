package LearningPatterns.ReportPublisher;

import java.util.HashMap;

public class CompanyReportPublisher extends AbstractReportPublisher{
    private int counter = 0;

    private final HashMap<Integer, Report> reports = new HashMap<>();
    @Override
    public void produce() {
        String[] strings = RandomData.getCompanyData();
        Report report = new CompanyReport(++counter, strings[0], strings[1]);
        reports.put(counter, report);
    }

    @Override
    public Report getReport(int id) {
        return reports.get(id);
    }
}