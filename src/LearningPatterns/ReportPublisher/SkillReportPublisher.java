package LearningPatterns.ReportPublisher;

import java.util.HashMap;

public class SkillReportPublisher extends AbstractReportPublisher{
    private int counter = 0;

    @Override
    public void produce() {
        String[] strings = RandomData.getSkillData();
        Report report = new SkillReport(++counter, strings[0], strings[1]);
        super.reports.put(counter, report);
    }

    @Override
    public Report getReport(int id) {
        return super.reports.get(id);
    }
}
