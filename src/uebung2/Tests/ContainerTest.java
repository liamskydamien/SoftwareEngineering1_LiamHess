package uebung2.Tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uebung2.Datastructures.Container;
import uebung2.Entities.MemberObject;
import uebung2.Exceptions.ContainerException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContainerTest {
    private Container container;
    private MemberObject[] members = {new MemberObject(11), new MemberObject(22)};

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
    void testeÜbergangVon0zu1() throws ContainerException {
        container.addMember(members[0]);
        assertEquals(1, container.size());
    }

    @Test
    void testeÜbergangVon1zu2() throws ContainerException {
        container.addMember(members[0]);
        assertEquals(1, container.size());
        container.addMember(members[1]);
        assertEquals(2, container.size());
    }
}
