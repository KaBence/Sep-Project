package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class MemberList implements Serializable {
    private ArrayList<Member> members;

    //Creating default Constructor
    public MemberList()
    {
        members = new ArrayList<>();
    }

    public void addMember(Member member)
    {
        members.add(member);
    }


    public Member getMemberByEmail(String email)
    {
        for(int i = 0; i< members.size(); i++)
        {
            if(members.get(i).getEmail().equals(email))
                return members.get(i);
        }
        return null;
    }

    public Member getMemberByName(String firstName)
    {
        for(int i = 0; i< members.size(); i++)
        {
            if(members.get(i).getFirstName().equals(firstName))
            {
                return members.get(i);
            }
        }
        return null;
    }

    public void removeMember(Member member)
    {
        members.remove(member);
    }

    public Member getMemberByPhoneNumber(String phoneNumber)
    {

        for(int i = 0; i< members.size(); i++)
        {
            if(members.get(i).getPhoneNumber().equals(phoneNumber))
                return members.get(i);
        }
        return null;
    }


    public ArrayList<Member> getAllMembers()
    {
        return members;
    }


    public String toString() {

        String temp ="";
        for (int i = 0; i < members.size(); i++) {
            Member member = members.get(i);
            temp += member.getFirstName() +"\n";
        }
        return temp;
    }
    public int size(){
        return members.size();
    }
    public Member get(int index){
        return members.get(index);
    }
}
