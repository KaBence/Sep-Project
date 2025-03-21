package Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The MemberList class is a container for a list of Member objects
 */
public class MemberList implements Serializable
{
  private ArrayList<Member> members;

  /**
   * Creating default Constructor
   */
  public MemberList()
  {
    members = new ArrayList<>();
  }

  /**
   * This function adds a member to the members list.
   * @param member The member to add to the list.
   */
  public void addMember(Member member)
  {
    members.add(member);
  }

  /**
   * This function takes email String as a parameter and returns a MemberList object.
   * @param email The email address of the member you want to find.
   * @return A list of members which contains passed parameter.
   */
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

  /**
   * This function returns the member with the given name, or null if no member with that name exists.
   * @param Name The name of the member you want to get.
   * @return The member object that contains the name.
   */
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

  /**
   * This function takes a string as a parameter and returns a MemberList object
   * @param Name The name of the member you want to search for.
   * @return A MemberList object
   */
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

  /**
   * Remove a member from the list of members.
   * @param member The member to remove from the group.
   */
  public void removeMember(Member member)
  {
    members.remove(member);
  }

  /**
   * This function returns a MemberList object that contains all the members in the current MemberList object that have a
   * phone number that contains the given phone number.
   * @param phoneNumber The phone number of the member you want to find.
   * @return A MemberList object.
   */
  public MemberList getMembersByPhoneNumber(String phoneNumber)
  {
    MemberList temp = new MemberList();
    for (int i = 0; i < members.size(); i++)
    {
      if (phoneNumber!=null&&members.get(i).getPhoneNumber().contains(phoneNumber))
      {
        temp.addMember(members.get(i));
      }
    }
    return temp;
  }

  public Member getMemberByOwnerID(String phoneNumber){
    for (int i = 0; i < members.size(); i++)
    {
      if (phoneNumber!=null&&members.get(i).getPhoneNumber().contains(phoneNumber))
      {
        return members.get(i);
      }
    }
    return null;
  }

  /**
   * The function returns a string of all the members fullName.
   * @return The full name of each member in the members array list.
   */
  public String toString()
  {

    String temp = "";
    for (int i = 0; i < members.size(); i++)
    {
      Member member = members.get(i);
      temp += member.getFullName() + "\n";
    }
    return temp;
  }

  /**
   * This function returns the number of members in the memberList.
   * @return The size of the members arraylist.
   */
  public int size()
  {
    return members.size();
  }


  /** This is a getter method that returns the member at the given index.
   * @param index the index of the memberList that you want to get.
   * @return a member of the provided index.
   */
  public Member get(int index)
  {
    return members.get(index);
  }
}
