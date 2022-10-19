package uebung3.Client;

import uebung3.Datastructures.Container;
import uebung3.Entities.MemberObject;
import uebung3.Exceptions.ContainerException;
import uebung3.Interfaces.Member;

public class Client {
    private final Container container = Container.getInstance();
    private final MemberView memberView = new MemberView();

    public void addMember(int id){
        Member member = new MemberObject(id);
        try {
            container.addMember(member);
            System.out.println("Member wurde erfolgreich hinzugefügt.");
        } catch (ContainerException e) {
            System.out.println("Member konnte nicht hinzugefügt werden.");
        }
    }

    public void deleteMember(int id){
        String message = container.deleteMember(id);
        if(message.equals("Member-Objekt mit der ID" + id + " existiert nicht im Container und konnte somit nicht gelöscht werden.")){
            System.out.println("Member konnte nicht gelöscht werden.");
        }
        else {
            System.out.println("Member erfolgreich gelöscht.");
        }
    }

    public void dump(){
        memberView.dump(container.getCurrentList());
    }
}
