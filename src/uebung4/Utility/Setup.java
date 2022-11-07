package uebung4.Utility;

import uebung4.Client.Client;
import uebung4.Datastructure.Container;
import uebung4.Persistance.PersistenceStrategyStream;

/**
 * @author Liam Hess
 * @version 1.0
 */
public class Setup {
    public static Client setupClient(){
        Client client = new Client();
        Container container = Container.getInstance();
        container.setStrategy(new PersistenceStrategyStream<>());
        return client;
    }
}
