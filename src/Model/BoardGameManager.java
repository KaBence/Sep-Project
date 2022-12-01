package Model;

import Util.MyFileHandler;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;


public class BoardGameManager implements Serializable
{
  private String filename;

  public BoardGameManager(String filename)
  {
    this.filename = filename;

  }

 /* public void addMemberToClub(Member member)
  {
    // getting all member list from file
    MemberList allMemberFromDatabase = getAllMembersFromFile();
    // adding provided member to list
    allMemberFromDatabase.addMember(member);
    // adding updated list to file
    saveMembers(allMemberFromDatabase);
  }
*/
  /**
   * It saves the provided list of members to a binary file
   *
   * @param listToSave The list of members to save to the file.
   */
  /*private void saveMembers(MemberList listToSave)
  {
    try
    {
      // saving provided list to binary file
      MyFileHandler.writeToBinaryFile(filename, listToSave);
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
  }
*/
  /**
   * This function reads the binary file and returns the MemberList object
   *
   * @return A list of all members from the database.
   */
  /*private MemberList getAllMembersFromFile()
  {

    try
    {
      // getting all list of all members from file
      MemberList tempList = (MemberList) MyFileHandler.readFromBinaryFile(
          filename);
      return tempList;
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    catch (ClassNotFoundException e)
    {
      e.printStackTrace();
    }
    return null;
  }*/

  /**
   * It takes a string as an argument, and returns an ArrayList of Members that have the string in their name
   *
   * @param name The name of the member you want to search for.
   * @return An ArrayList of Members
   */
  /*public ArrayList<Member> searchByName(String name)
  {
    ArrayList<Member> allMembers = getAllMembersFromFile().getAllMembers();
    ArrayList<Member> temp = new ArrayList<>();
*/
    //for (int i = 0; i < allMembers.size(); i++) {
    //  Member member = allMembers.get(i);
    // Checking if the member's name contains the name that is being searched for.
    // if (member.getFullName().toLowerCase().contains(name.toLowerCase())){
    //   temp.add(member);
    //  }
    // }
    //  return temp;
    // }

    /**
     * This function searches for a member by phone number
     *
     * @param phone The phone number to search for.
     * @return An ArrayList of Members
     */
  /*public ArrayList<Member> searchByPhone(String phone) {
    ArrayList<Member> allMembers = getAllMembersFromFile().getAllMembers();
    ArrayList<Member> temp = new ArrayList<>();


    for (int i = 0; i < allMembers.size(); i++) {
      Member member = allMembers.get(i);
      if (member.getPhoneNumber().contains(phone)){
        temp.add(member);
      }
    }
    return temp;
  }*/

    /**
     * It searches for a member by email
     *
     * @param email The email to search for.
     * @return An ArrayList of Members
     */
  /*public ArrayList<Member> searchByEmail(String email) {

    ArrayList<Member> allMembers = getAllMembersFromFile().getAllMembers();
    ArrayList<Member> temp = new ArrayList<>();


    for (int i = 0; i < allMembers.size(); i++) {
      if(allMembers.get(i).getEmail().contains(email))
      {
        temp.add(allMembers.get(i));
      }
    }
    return temp;
  }*/
  }