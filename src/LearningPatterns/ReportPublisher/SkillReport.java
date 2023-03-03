package LearningPatterns.ReportPublisher;

public class SkillReport extends Report {
    private int id;
    private String skill;
    private String student;

    public SkillReport(int id, String skill, String student) {
        this.id = id;
        this.skill = skill;
        this.student = student;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
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
