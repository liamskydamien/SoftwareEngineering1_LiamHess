import Uebung1.view.Client;

public class Main {
    public static void main(String[] args) {
        Client showCaseClient = new Client();
        for (Integer i: new int[]{1, 5, 7, 10, 11, -1}) {
            showCaseClient.display(i);
        }
    }
}