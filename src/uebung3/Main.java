package uebung3;

import uebung3.Interfaces.Member;
import uebung3.Datastructures.Container;
import uebung3.persistence.PersistenceStrategy;

public class Main {
    public static void setStrategy(PersistenceStrategy<Member> persistenceStrategy){
        Container.getInstance().setStrategy(persistenceStrategy);
    }
}
