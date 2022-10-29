package uebung4.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uebung4.Client.Client;
import uebung4.Exception.ClientException;
import uebung4.Model.Expertise;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestClient {
    private Client client;
    private HashMap<String, Expertise> expertise = createHashMap();

    private HashMap<String, Expertise> createHashMap() {
        HashMap<String, Expertise> hashMap = new HashMap<>();
        hashMap.put("Java", Expertise.Experte);
        hashMap.put("SCRUM", Expertise.Beginner);
        hashMap.put("SQL", Expertise.TopPerformer);
        return hashMap;
    }

    @BeforeEach
    private void setup(){
        client = new Client();
    }

    @Test
    private void testeEnter() throws ClientException {
        client.enter(1L, "Liam", "Hess", "Projekt-Leiter", "IT", expertise);
        client.enter(2L, "Siri","Senma","Usability", "Design", expertise);
        client.enter(5L, "Niklas", "Müseler", "Software-Entwickler", null, expertise);
        assertEquals(3,client.size());
        ClientException alreadyIn = assertThrows(ClientException.class, () -> client.enter(1L, "Test", "test", "test", "test", expertise));
        ClientException IdNull = assertThrows(ClientException.class, () -> client.enter(null, "Test", "test", "test", "test", expertise));
        ClientException wrongFN = assertThrows(ClientException.class, () -> client.enter(6L, "Test4", "test", "test", "test", expertise));
        ClientException wrongLN = assertThrows(ClientException.class, () -> client.enter(7L, "Test", "test5", "test", "test", expertise));
        assertEquals(ClientException.ClientExceptionType.IdAlreadyDefined, alreadyIn.getClientExceptionType());
        assertEquals(ClientException.ClientExceptionType.IdIsNotSet, IdNull.getClientExceptionType());
        assertEquals(ClientException.ClientExceptionType.WrongFormatFirstname, wrongFN.getClientExceptionType());
        assertEquals(ClientException.ClientExceptionType.WrongFormatLastname, wrongLN.getClientExceptionType());
    }

}
