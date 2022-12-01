package Main;

import Model.Member;
import Model.MemberList;
import Util.MyFileHandler;

import java.io.FileNotFoundException;
import java.io.IOException;

public class LoadInitialData
{
  public static void main(String[] args)
  {
    MemberList members = new MemberList();
    String[] memberArray = null;

    try
    {
      memberArray = MyFileHandler.readArrayFromTextFile("dummyDataMembers.txt");

      for(int i = 0; i<memberArray.length; i++)
      {
        String temp = memberArray[i];
        String[] tempArr = temp.split(",");
        String firstName = tempArr[0];
        String lastName = tempArr[1];
        String phoneNumber = tempArr[2];
        String email = tempArr[3];

        members.addMember(new Member(firstName, lastName, phoneNumber, email));
      }
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File was not found, or could not be opened");
    }

    try
    {
      MyFileHandler.writeToBinaryFile("members.bin", members);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("Error opening file ");
    }
    catch (IOException e)
    {
      System.out.println("IO Error writing to file ");
    }

    System.out.println("Done");
  }
}
