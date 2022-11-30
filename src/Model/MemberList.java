package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class MemberList implements Serializable {
    private ArrayList<Member> list;

    //Creating default Constructor
    public MemberList()
    {
        list = new ArrayList<>();
    }

    public void addMember(Member member)
    {
        list.add(member);
    }


    public Member getMemberByEmail(String email)
    {
        for(int i=0; i<list.size(); i++)
        {
            if(list.get(i).getEmail().equals(email))
                return list.get(i);
        }
        return null;
    }

    public Member getMemberByName(String firstName)
    {
        for(int i=0; i<list.size(); i++)
        {
            if(list.get(i).getFirstName().equals(firstName))
            {
                return list.get(i);
            }
        }
        return null;
    }

    public void removeMember(Member member)
    {
        list.remove(member);
    }

    public Member getMemberByPhoneNumber(String phoneNumber)
    {

        for(int i=0; i<list.size(); i++)
        {
            if(list.get(i).getPhoneNumber().equals(phoneNumber))
                return list.get(i);
        }
        return null;
    }


    public ArrayList<Member> getAllMembers()
    {
        return list;
    }


    @Override
    public String toString() {

        String temp ="";
        for (int i = 0; i < list.size(); i++) {
            Member member = list.get(i);
            temp += member.getFirstName() +"\n";
        }
        return temp;
    }
}
