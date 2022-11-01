package uebung4;

import uebung4.Client.Client;
import uebung4.Datastructure.Container;
import uebung4.Exception.ClientException;
import uebung4.Model.Expertise;
import uebung4.Persistance.PersistenceStrategyStream;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Client client = new Client();
        Container container = Container.getInstance();
        container.setStrategy(new PersistenceStrategyStream<>());
        Scanner scanner = new Scanner(System.in);
        System.out.println("Willkommen. Mit dem Befehl \"help\" bekommen Sie eine Übersicht über alle Befehle.");
        while(scanner.hasNext()){
            String currentLine = scanner.nextLine();
            Scanner lineScanner = new Scanner(currentLine);
            switch (lineScanner.next()){
                case "help":
                    System.out.println("""
                            Hier ist eine Sammlung aller gültigen Befehle:
                            help    gibt eine Liste aller möglichen Befehle aus.
                            load [Force / Merge] lädt eine vorherige Datensammlung in den RAM Speicher. 
                                Force signalisiert eine komplette neubeladung und ignoriert bestehende Mitarbeiter
                                Merge mischt die bestehnenden Mitarbeiter mit den gespeicherten (Doppelte IDs, werden nicht beachtet!)
                            store   speichert die aktuell im RAM befindliche Liste
                            dump    gibt die aktuell im RAM befindlichen Mitarbeiter tabellarisch und nach ID sortiert aus.
                            search  [Expertise]     gibt alle Mitarbeirter nach ID sortiert aus die diese Expertise besitzen.
                            enter [ID] [Vorname] [Nachname] [Rolle] [Abteilung / -] [Expertise1:Level] ... [ExpertiseN:Level]
                                fügt einen neuen Mitarbeiter in den RAM ein. Bitte beachte das falls dieser keine Abteilung hat diese mit - angeben werden muss.
                                Expertisen können beliebig viele eingefügt werden und sollen nach dem Schema ExpertiseName:ExpertisenLevel eingeben werden.
                            exit    beendet das Programm
                            """);
                    break;
                case "load":
                    try {
                        String isForce = scanner.next();
                        if(isForce.equals("Force")){
                            client.load(true);
                            System.out.println("Laden mittels Merge erfolgreich.");
                        }
                        else if (isForce.equals("Merge")){
                            client.load(false);
                            System.out.println("Laden mittels Merge erfolgreich.");

                        }
                        else {
                            System.out.println("Übergebener Parametwer ungültig bitte probiere es erneut");
                            break;
                        }
                    }
                    catch (ClientException e){
                        System.out.println(e.getMessage());
                    }
                    catch (Exception exception){
                        System.out.println("Ein Fehler ist aufgetreten, bitte versuche es erneut.");
                    }
                    break;
                case "store":
                    try {
                        client.store();
                        System.out.println("Speicher erfolgreich.");
                    }
                    catch (ClientException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "dump":
                    System.out.println(client.dump());
                    break;
                case "search":
                    try{
                        String parameter = lineScanner.next();
                        System.out.println(client.search(parameter));
                    }
                    catch (Exception e){
                        System.out.println("Ungültiger Suchparameter bitte probiere es erneut.");
                    }
                    break;
                case "enter":
                    try {
                        int id = lineScanner.nextInt();
                        String firstname = lineScanner.next();
                        String lastname = lineScanner.next();
                        String role = lineScanner.next();
                        String department = lineScanner.next();
                        HashMap<String, Expertise> expertise = new HashMap<>();
                        while (lineScanner.hasNext()) {
                            String[] expertiseAndLevel = lineScanner.next().split(":");
                            Expertise expertiseType = null;
                            switch (expertiseAndLevel[1]) {
                                case "Beginner" -> expertiseType = Expertise.Beginner;
                                case "Experte" -> expertiseType = Expertise.Experte;
                                case "TopPerformer" -> expertiseType = Expertise.TopPerformer;
                                default -> {
                                    System.out.println("Ungültiges Expertisen Level. Bitte erneut versuchen.");
                                    throw new RuntimeException();
                                }
                            }
                            expertise.put(expertiseAndLevel[0], expertiseType);
                        }
                        try {
                            client.enter(id, firstname, lastname, role, department, expertise);
                            System.out.println("Neuer Mitarbeiter erfolgreich hinzugefügt.");
                        }
                        catch (ClientException exception){
                            System.out.println(exception.getMessage());
                        }
                    }
                    catch (Exception e){
                        System.out.println("Ungültige Eingabe. Probieren Sie es erneut.");
                        break;
                    }
                    break;
                case "exit":
                    scanner.close();
                    System.out.println("System wird heruntergefahren...");
                    return;
                default:
                    System.out.println("Eingabe leider ungültig. Bitte verwenden Sie \"help\" um eine Liste aller gültigen Befehle zu bekommen und probieren es erneut.");
                    break;
            }
        }
    }
}
