package LearningPatterns.ReportPublisher;


public class RandomData {
    public static String[] getSkillData(){
        int number = (int) (Math.random() * 10);
        if(number < 5){
            String[] ret = {"Java" ,"Liam Hess"};
            return ret;
        }

        if(number > 5){
            String[] ret = {"TypeScript" ,"Siriporn Senma"};
            return ret;
        }

        String[] ret = {"SQL" ,"Tim Polland"};
        return ret;
    }

    public static String[] getCompanyData(){
        int number = (int) (Math.random() * 10);
        if(number < 5){
            String[] ret = {"Adesso SE" ,"Stellt 100000000 neue Werkstudenten ein"};
            return ret;
        }

        if(number > 5){
            String[] ret = {"VenTrade" ,"Hat einen neuen App Manager"};
            return ret;
        }

        String[] ret = {"ZDH-Zert" ,"Verliert Mitarbeiter"};
        return ret;
    }
}
