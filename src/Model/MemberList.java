package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class MemberList implements Serializable
{
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

  public MemberList getMembersByEmail(String email)
  {
    MemberList temp = new MemberList();
    for (int i = 0; i < members.size(); i++)
    {
      if (members.get(i).getEmail().contains(email))
      {
        temp.addMember(members.get(i));
      }

    }
    return temp;
  }

  public Member getMemberByName(String Name)
  {
    for (int i = 0; i < members.size(); i++)
    {
      if (members.get(i).contains(Name))
      {
        return members.get(i);
      }
    }
    return null;
  }

  public MemberList getMembersByName(String Name)
  {
    MemberList temp = new MemberList();
    for (int i = 0; i < members.size(); i++)
    {
      if (members.get(i).contains(Name))
      {
        temp.addMember(members.get(i));
      }
    }
    return temp;
  }

  public void removeMember(Member member)
  {
    members.remove(member);
  }

  public MemberList getMembersByPhoneNumber(String phoneNumber)
  {
    MemberList temp = new MemberList();
    for (int i = 0; i < members.size(); i++)
    {
      if (members.get(i).getPhoneNumber().contains(phoneNumber))
      {
        temp.addMember(members.get(i));
      }
    }
    return temp;
  }



  public String toString()
  {

    String temp = "";
    for (int i = 0; i < members.size(); i++)
    {
      Member member = members.get(i);
      temp += member.getFirstName() + "\n";
    }
    return temp;
  }

  public int size()
  {
    return members.size();
  }

  public Member get(int index)
  {
    return members.get(index);
  }
}
