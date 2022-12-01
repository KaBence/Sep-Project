package Main;

import Model.Member;
import Model.MemberList;
import Util.MyFileHandler;

import java.io.FileNotFoundException;
import java.io.IOException;

import Model.BoardGame;
import Model.BoardGameList;
import Model.MemberList;
import Util.MyFileHandler;

import java.io.FileNotFoundException;
import java.io.IOException;

public class LoadInitialData
{
  public static void main(String[] args)
  {
    BoardGameList boardgames=new BoardGameList();
    MemberList members=new MemberList();
    String[] boardgamesarray=null;
    String[] memberArray = null;

    // reading the dummydata for members

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

    //reading the dummydata for the boardgames

    try
    {
      boardgamesarray= MyFileHandler.readArrayFromTextFile("dummydataboardgames.txt");

      for (String item:boardgamesarray){
        String[] temp=item.split(",");
        String name=temp[0];
        String type=temp[1];
        int minNoP=Integer.parseInt(temp[2]);
        int maxNop=Integer.parseInt(temp[3]);
        String owner=temp[4];
        boolean avl= Boolean.parseBoolean(temp[5]);
        boardgames.addBoardGame(new BoardGame(name,type,minNoP,maxNop,
            members.getMemberByName(owner),avl));
      }
    }
    catch (FileNotFoundException e){
      System.out.println("File not found");
    }

    //Writing the bin file for members
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

    //Writing the bin file for boardgames
    try
    {
      MyFileHandler.writeToBinaryFile("Boardgames.bin",boardgames);
    }
    catch (FileNotFoundException e){
      System.out.println("File not found");
    }
    catch (IOException e){
      System.out.println("IO error writing to the file");
    }

    System.out.println("Done");
  }
}
