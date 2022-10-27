package uebung4;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            String current = scanner.next();
            if(current.equals("exit")){
                System.out.println("Tschauuu");
                break;
            }
        }
        scanner.close();
    }
}
