package uebung2.Entities;

import uebung2.Interfaces.Member;

public class MemberObject implements Member {
    private int ID;

    public MemberObject(int ID) {
        this.ID = ID;
    }

    @Override
    public Integer getID() {
        return null;
    }

    @Override
    public String toString() {
        return "Member (ID = " + ID + " )";
    }
}
