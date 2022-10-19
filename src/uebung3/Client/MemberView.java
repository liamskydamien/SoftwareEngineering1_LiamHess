package uebung3.Client;

import uebung3.Interfaces.Member;
import uebung3.Datastructures.Container;

import java.util.List;

public class MemberView {
    public void dump(List<Member> members){
        for (Member member: members) {
            System.out.println(member.toString());
        }
    }
}
