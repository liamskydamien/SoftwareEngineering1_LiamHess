package uebung3.Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uebung3.Client.Client;
import uebung3.Datastructures.Container;
import uebung3.Entities.MemberObject;
import uebung3.Exceptions.ContainerException;
import uebung3.Interfaces.Member;
import uebung3.Main;
import uebung3.persistence.PersistenceException;
import uebung3.persistence.PersistenceStrategy;
import uebung3.persistence.PersistenceStrategyMongoDB;
import uebung3.persistence.PersistenceStrategyStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ContainerTest {
    private final Container container = Container.getInstance();
    private final Member[] members = {new MemberObject(11), new MemberObject(22), new MemberObject(33)};
    private final Client client = new Client();
    @BeforeEach
    void setup(){
        for (Member member: members) {
            container.deleteMember(member.getID());
        }
    }

    @Test
    void testClientFuncunality(){
        assertEquals(0, container.size());
        client.addMember(11);
        assertEquals(1,container.size());
        client.addMember(11);
        assertEquals(1, container.size());
        client.deleteMember(22);
        assertEquals(1, container.size());
        client.dump();
        assertEquals(1,container.size());
        client.deleteMember(11);
        assertEquals(0,container.size());
        client.dump();
        assertEquals(0,container.size());
    }

    @Test
    void testeOhneStrategie(){
        try {
            Main.setStrategy(null);
            container.addMember(members[0]);
            container.addMember(members[1]);
            container.addMember(members[2]);
            assertThrows(PersistenceException.class, container::store);
        } catch (ContainerException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testeMitMongoDBStrategie(){
        try{
            Main.setStrategy(new PersistenceStrategyMongoDB<>());
            container.addMember(members[0]);
            container.addMember(members[1]);
            container.addMember(members[2]);
            assertThrows(UnsupportedOperationException.class, container::store);
        }
        catch (ContainerException e){
            e.printStackTrace();
        }
    }

    @Test
    void testeMitFehlerhafterLocation(){
            PersistenceStrategyStream<Member> persistenceStrategy = new PersistenceStrategyStream<>();
            persistenceStrategy.setLocation("/project/test/objects.ser");
            Main.setStrategy(persistenceStrategy);
        try{
            container.addMember(members[0]);
            container.addMember(members[1]);
            container.addMember(members[2]);
            assertThrows(PersistenceException.class, container::store);
        }
        catch (ContainerException e){
            e.printStackTrace();
        }
    }

    @Test
    void testeKorrektesSzenario(){
        Main.setStrategy(new PersistenceStrategyStream<>());
        try{
            container.addMember(members[0]);
            assertEquals(1, container.size());
            container.store();
            assertEquals(1, container.size());
            container.deleteMember(11);
            assertEquals(0, container.size());
            container.load();
            assertEquals(1, container.size());
        }catch (ContainerException | PersistenceException e){
            e.printStackTrace();
        }
    }
}
