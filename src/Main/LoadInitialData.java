package Main;

import Model.*;
import Util.MyFileHandler;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;
import Model.MyDate;

import Model.BoardGame;
import Model.BoardGameList;
import Model.MemberList;
import Util.MyFileHandler;

import java.io.FileNotFoundException;
import java.io.IOException;

public class LoadInitialData
{
  public static MyDate stringToDate(String date, String time){
    String[] temp = date.split("/");
    int tempDate = Integer.valueOf(temp[0]);
    int tempMonth = Integer.valueOf(temp[1]);
    int tempyear = Integer.valueOf(temp[2]);
    String[] timetemp = time.split(":");
    int tempHour = Integer.valueOf(timetemp[0]);
    int tempMin = Integer.valueOf(timetemp[1]);
    return new MyDate(tempDate,tempMonth,tempyear,tempHour,tempMin);
  }
  public static void main(String[] args)
  {
    BoardGameList boardgames=new BoardGameList();
    MemberList members=new MemberList();
    String[] boardgamesarray=null;
    String[] memberArray = null;
    EventList events = new EventList();
    String[] eventsArray = null;

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
        String email = tempArr[2];
        String phoneNumber = tempArr[3];

        members.addMember(new Member(firstName, lastName, phoneNumber, email));
      }


    }
    catch (FileNotFoundException e)
    {
      System.out.println("File was not found, or could not be opened");
    }
    //Reading the events from the dummydata
    try
    {
      eventsArray = MyFileHandler.readArrayFromTextFile("dummyDataEvents.txt");
      for(int i = 0; i<eventsArray.length; i++)
      {
        String temp = eventsArray[i];
        String[] tempArr = temp.split("-");
        String date = tempArr[0];
        String timeTemp = tempArr[1];
        String location = tempArr[2];
        int capacity = Integer.parseInt(tempArr[3]);
        String name = tempArr[4];
        String guestsString = tempArr[5];
        events.addEvent(new Event(stringToDate(date,timeTemp), location,capacity, name,tempArr[5]));
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

    System.out.println("Members Done");

    try
    {
      MyFileHandler.writeToBinaryFile("events.bin", events);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("Error opening file ");
    }
    catch (IOException e)
    {
      System.out.println("IO Error writing to file ");
    }

    System.out.println("Events Done");

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

    System.out.println("Board Games Done");
  }
}
