package Model;

import java.util.ArrayList;

public class MemberList {
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

    public void editMember(int index, String name, String phone, String email)
    {
        list.get(index).setName(name);
        list.get(index).setPhoneNumber(phone);
        list.get(index).setEmail(email);
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

    public Member getMemberByName(String name)
    {
        for(int i=0; i<list.size(); i++)
        {
            if(list.get(i).getName().equals(name))
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






}
