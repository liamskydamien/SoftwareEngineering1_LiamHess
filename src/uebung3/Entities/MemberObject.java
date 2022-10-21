package uebung3.Entities;

import uebung3.Interfaces.Member;

import java.io.Serializable;

public class MemberObject implements Member, Serializable{
    private final int ID;

    public MemberObject(int ID) {
        this.ID = ID;
    }

    @Override
    public Integer getID() {
        return ID;
    }

    @Override
    public String toString() {
        return "Member (ID = " + ID + " )";
    }
}
