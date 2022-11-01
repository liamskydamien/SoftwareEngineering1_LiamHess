package uebung4.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uebung4.Client.Client;
import uebung4.Datastructure.Container;
import uebung4.Exception.ClientException;
import uebung4.Model.Expertise;
import uebung4.Persistance.PersistenceStrategyStream;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class TestClient {
    private Client client;
    private final HashMap<String, Expertise> expertise = createHashMap();

    private HashMap<String, Expertise> createHashMap() {
        HashMap<String, Expertise> hashMap = new HashMap<>();
        hashMap.put("Java", Expertise.Experte);
        hashMap.put("SCRUM", Expertise.Beginner);
        hashMap.put("SQL", Expertise.TopPerformer);
        return hashMap;
    }

    @BeforeEach
    void setup(){
        client = new Client();
    }

    @Test
    void testeEnter() throws ClientException {
        client.enter(1, "Liam", "Hess", "Projekt-Leiter", "IT", expertise);
        client.enter(2, "Siri","Senma","Usability", "Design", expertise);
        client.enter(5, "Niklas", "Müseler", "Software-Entwickler", "-", expertise);
        assertEquals(3,client.size());
        ClientException alreadyIn = assertThrows(ClientException.class, () -> client.enter(1, "Test", "test", "test", "test", expertise));
        ClientException IdNull = assertThrows(ClientException.class, () -> client.enter(null, "Test", "test", "test", "test", expertise));
        ClientException wrongFN = assertThrows(ClientException.class, () -> client.enter(6, "Test4", "test", "test", "test", expertise));
        ClientException wrongLN = assertThrows(ClientException.class, () -> client.enter(7, "Test", "test5", "test", "test", expertise));
        assertEquals(ClientException.ClientExceptionType.IdAlreadyDefined, alreadyIn.getClientExceptionType());
        assertEquals(ClientException.ClientExceptionType.IdIsNotSet, IdNull.getClientExceptionType());
        assertEquals(ClientException.ClientExceptionType.WrongFormatFirstname, wrongFN.getClientExceptionType());
        assertEquals(ClientException.ClientExceptionType.WrongFormatLastname, wrongLN.getClientExceptionType());
    }

    @Test
    void testeDump() throws ClientException {
        client.enter(1, "Sascha", "Alda", "Projekt-Leiter", "IT", expertise);
        client.enter(2, "Thorsten","Bonne","Controlling", "Financial", expertise);
        client.enter(5, "Karl", "Jonas", "Software-Entwickler", "-", expertise);
        assertEquals(3,client.size());

        assertEquals("""
               ID |         Vorname |        Nachname |       Abteilung |                   Rolle |        Expertise
               ---+-----------------+-----------------+-----------------+-------------------------+-------------------------
                1 |          Sascha |            Alda |              IT |          Projekt-Leiter |[(Java : Experte),(SCRUM : Beginner),(SQL : TopPerformer)]
                2 |        Thorsten |           Bonne |       Financial |             Controlling |[(Java : Experte),(SCRUM : Beginner),(SQL : TopPerformer)]
                5 |            Karl |           Jonas |     --------    |     Software-Entwickler |[(Java : Experte),(SCRUM : Beginner),(SQL : TopPerformer)]
               """ , client.dump());
        client.enter(4,"Corinna", "Ruppel", "Usability", "Creative", expertise);
        assertEquals("""
               ID |         Vorname |        Nachname |       Abteilung |                   Rolle |        Expertise
               ---+-----------------+-----------------+-----------------+-------------------------+-------------------------
                1 |          Sascha |            Alda |              IT |          Projekt-Leiter |[(Java : Experte),(SCRUM : Beginner),(SQL : TopPerformer)]
                2 |        Thorsten |           Bonne |       Financial |             Controlling |[(Java : Experte),(SCRUM : Beginner),(SQL : TopPerformer)]
                4 |         Corinna |          Ruppel |        Creative |               Usability |[(Java : Experte),(SCRUM : Beginner),(SQL : TopPerformer)]
                5 |            Karl |           Jonas |     --------    |     Software-Entwickler |[(Java : Experte),(SCRUM : Beginner),(SQL : TopPerformer)]
               """ , client.dump());

        System.out.println(client.dump());
    }

    @Test
    void testeSearch() throws ClientException {
        client.enter(1, "Sascha", "Alda", "Projekt-Leiter", "IT", expertise);
        client.enter(2, "Thorsten","Bonne","Controlling", "Financial", expertise);
        client.enter(5, "Karl", "Jonas", "Software-Entwickler", "-", expertise);
        assertEquals("""
                1 : Sascha Alda, Projekt-Leiter, IT, [(Java : Experte),(SCRUM : Beginner),(SQL : TopPerformer)]
                2 : Thorsten Bonne, Controlling, Financial, [(Java : Experte),(SCRUM : Beginner),(SQL : TopPerformer)]
                5 : Karl Jonas, Software-Entwickler, - , [(Java : Experte),(SCRUM : Beginner),(SQL : TopPerformer)]
                """, client.search("Java"));
        assertEquals("",client.search("SEO"));
        assertEquals(3,client.size());
        HashMap<String, Expertise> second = new HashMap<>();
        second.put("SEO",Expertise.TopPerformer);
        client.enter(3, "Peter", "Becker", "Controller", "-", second);
        assertEquals("3 : Peter Becker, Controller, - , [(SEO : TopPerformer)]\n",client.search("SEO"));
        assertEquals(4,client.size());
        System.out.println(client.search("Java"));
    }

    @Test
    void testeLoadAndStorePositiveTest() throws ClientException {
        Container container = Container.getInstance();
        container.setStrategy(new PersistenceStrategyStream<>());
        client.enter(1, "Sascha", "Alda", "Projekt-Leiter", "IT", expertise);
        client.enter(2, "Thorsten","Bonne","Controlling", "Financial", expertise);
        client.enter(5, "Karl", "Jonas", "Software-Entwickler", "-", expertise);
        assertEquals(3,client.size());
        client.store();
        client.remove(1);
        client.remove(2);
        client.remove(5);
        assertEquals(0,client.size());
        client.load(true);
        assertEquals(3,client.size());
        client.remove(1);
        client.remove(2);
        client.remove(5);
        client.enter(4, "Karl", "Jonas", "Software-Entwickler", "-", expertise);
        client.enter(3, "Karl", "Jonas", "Software-Entwickler", "-", expertise);
        client.load(false);
        assertEquals(5,client.size());
    }

    @Test
    void testeLoadAndStoreNegativeTest() throws ClientException {
        Container container = Container.getInstance();
        client.enter(1, "Sascha", "Alda", "Projekt-Leiter", "IT", expertise);
        client.enter(2, "Thorsten","Bonne","Controlling", "Financial", expertise);
        client.enter(5, "Karl", "Jonas", "Software-Entwickler", "-", expertise);
        assertEquals(3,client.size());
        ClientException noStrategy = assertThrows(ClientException.class, client::store);
        assertEquals(ClientException.ClientExceptionType.ErrorWhileSaving, noStrategy.getClientExceptionType());
        container.setStrategy(new PersistenceStrategyStream<>());
        client.store();
        client.remove(1);
        client.remove(2);
        client.remove(5);
        assertEquals(0,client.size());
        container.setStrategy(null);
        ClientException noStrategyLoad = assertThrows(ClientException.class, () -> client.load(true));
        assertEquals(ClientException.ClientExceptionType.ErrorWhileLoading, noStrategyLoad.getClientExceptionType());
        container.setStrategy(new PersistenceStrategyStream<>());
        client.enter(1, "Sigrid", "Weil", "Projekt-Leiter", "IT", expertise);
        client.enter(2, "Thorsten","Dum","Controlling", "Financial", expertise);
        client.load(false);
        assertEquals(3,client.size());
        assertEquals("""
               ID |         Vorname |        Nachname |       Abteilung |                   Rolle |        Expertise
               ---+-----------------+-----------------+-----------------+-------------------------+-------------------------
                1 |          Sigrid |            Weil |              IT |          Projekt-Leiter |[(Java : Experte),(SCRUM : Beginner),(SQL : TopPerformer)]
                2 |        Thorsten |             Dum |       Financial |             Controlling |[(Java : Experte),(SCRUM : Beginner),(SQL : TopPerformer)]
                5 |            Karl |           Jonas |     --------    |     Software-Entwickler |[(Java : Experte),(SCRUM : Beginner),(SQL : TopPerformer)]
               """ , client.dump());
    }
}
