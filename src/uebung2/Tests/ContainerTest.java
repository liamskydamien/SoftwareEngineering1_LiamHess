package uebung2.Tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uebung2.Datastructures.Container;
import uebung2.Entities.MemberObject;
import uebung2.Exceptions.ContainerException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ContainerTest {
    private Container container;
    private final MemberObject[] members = {new MemberObject(11), new MemberObject(22)};

    @BeforeEach
    void setup(){
        container = new Container();
    }

    @AfterEach
    void teardown(){
        container = null;
    }

    @Test
    void testeInitisalisierung() {
        assertEquals(0,container.size());
    }

    @Test
    void testeUebergangVon0zu1zu2() throws ContainerException {
        container.addMember(members[0]);
        assertEquals(1, container.size());
        container.addMember(members[1]);
        assertEquals(2, container.size());
    }

    @Test
    void testeUebergangVon2zu1zu0() throws ContainerException {
        //Hinzufügen
        container.addMember(members[0]);
        container.addMember(members[1]);
        //Eigentlicher Test
        container.deleteMember(11);
        assertEquals(1,container.size());
        container.deleteMember(22);
        assertEquals(0,container.size());
    }

    @Test
    void testeUebergangVon1zu1MitSizeUndDump() throws ContainerException {
        //Hinzufügen von einem Memeber
        container.addMember(members[0]);
        //Eigentlicher Test
        container.size();
        assertEquals(1, container.size());
        container.dump();
        assertEquals(1, container.size());
    }

    @Test
    void testeUebergangVon1zu1MitAddUndDelete() throws ContainerException {
        //Hinzufügen von einem Member
        container.addMember(members[0]);
        //Eigentlicher Test
        //Ausführung der Methode in assertThrows um im Falle der ContainerException den Test fortführen zu können.
        assertThrows(ContainerException.class, () -> container.addMember(new MemberObject(11)));
        assertEquals(1, container.size());
        container.deleteMember(22);
        assertEquals(1, container.size());
    }

    @Test
    void testeEingabeVonNull() {
        assertThrows(ContainerException.class, () -> container.addMember(null));
        assertEquals(0,container.size());
        //Delete Methode kann nicht auf null getestet werden da nach einem Integer und nicht nach einem Objekt gefragt wird.
    }
}
