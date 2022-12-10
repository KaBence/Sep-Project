package Model;

import Util.MyFileHandler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;



/**
 * The BoardGameManager class is a class that manages the boardgames, members, and events
 */
public class BoardGameManager implements Serializable
{
  private String boardgames;
  private String members;
  private String events;

  private MemberList memberList;
  private BoardGameList boardGameList;
  private EventList eventList;

  // A constructor that takes in three strings and sets them to the variables in the class.
  public BoardGameManager(String boardgames,String members,String events)
  {
    this.events=events;
    this.members=members;
    this.boardgames=boardgames;
  }



  /**
   * It reads the memberList from the file and returns it
   *
   * @return A list of all members.
   */
  public MemberList getAllMembers(){
    memberList=new MemberList();
    try
    {
      memberList=(MemberList) MyFileHandler.readFromBinaryFile(members);
    }
    catch (FileNotFoundException e){
      System.out.println("File not found");
    }
    catch (IOException e){
      System.out.println("IO error when opening the file");
    }
    catch (ClassNotFoundException e){
      System.out.println("Wrong class in file");
    }
    return memberList;
  }

  /**
   * It reads the boardgame list from a binary file and returns it
   *
   * @return The method returns a BoardGameList object.
   */
  public BoardGameList getAllBoardGames(){
    boardGameList=new BoardGameList();
    try
    {
      boardGameList=(BoardGameList) MyFileHandler.readFromBinaryFile(boardgames);
    }
    catch (FileNotFoundException e){
      System.out.println("File not found");
    }
    catch (IOException e){
      System.out.println("IO error when opening the file");
    }
    catch (ClassNotFoundException e){
      System.out.println("Wrong class in file");
    }
    return boardGameList;
  }



  /**
   * This function reads the binary file and returns the eventList object
   *
   * @return The method returns the eventList.
   */

  public EventList getAllEvents(){
    eventList=new EventList();
    try
    {
      eventList=(EventList) MyFileHandler.readFromBinaryFile(events);
    }
    catch (FileNotFoundException e){
      System.out.println("File not found");
    }
    catch (IOException e){
      System.out.println("IO error when opening the file");
    }
    catch (ClassNotFoundException e){
      System.out.println("Wrong class in file");
    }
    return eventList;
  }

  /**
   * It tries to write the memberList to a binary file called "members.bin"
   *
   * @param memberList The MemberList object that you want to save to the file.
   */
  public void saveAllMembers(MemberList memberList){
    try
    {
      MyFileHandler.writeToBinaryFile("members.bin",memberList);
    }
    catch (FileNotFoundException e){
      System.out.println("File not found");
    }
    catch (IOException e){
      System.out.println("IO error when opening the file");
    }
  }

  /**
   * The function takes a BoardGameList object as a parameter and saves it to a binary file called "Boardgames.bin"
   *
   * @param boardGameList The list of board games to be saved
   */
  public void saveAllBoardGames(BoardGameList boardGameList){
    try
    {
      MyFileHandler.writeToBinaryFile("Boardgames.bin",boardGameList);
    }
    catch (FileNotFoundException e){
      System.out.println("File not found");
    }
    catch (IOException e){
      System.out.println("IO error when opening the file");
    }
  }

  /**
   * This function takes an EventList object as a parameter and saves it to a binary file called events.bin
   *
   * @param eventList The list of events to be saved
   */
  public void saveAllEvents(EventList eventList){
    try
    {
      MyFileHandler.writeToBinaryFile("events.bin",eventList);
    }
    catch (FileNotFoundException e){
      System.out.println("File not found");
    }
    catch (IOException e){
      System.out.println("IO error when opening the file");
    }
  }
}