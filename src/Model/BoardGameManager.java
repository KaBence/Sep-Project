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

  /**
   * A constructor that takes in three strings and sets them to the variables in the class.
   * @param boardgames String
   * @param events String
   * @param members String
   */
  public BoardGameManager(String boardgames,String members,String events)
  {
    this.events=events;
    this.members=members;
    this.boardgames=boardgames;
  }



  /**
   * It reads the memberList from the file and returns it
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
   * It gets all the boardGames from the boardGameList.
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
   * It gets all the events from the EventList.
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
   * It saves all the members from the MemberList.
   * It tries to write the memberList to a binary file called "members.bin"
   * @param memberList the variable to MemberList.
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
   * It saves all the board games from the BoardGameList.
   * The function takes a BoardGameList object which is boardGameList as a parameter and saves it to a binary file called "Boardgames.bin"
   * @param boardGameList the variable to BoardGameList.
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
   * It saves all the events from the EventList.
   * This function takes an EventList object which is eventList as a parameter and saves it to a binary file called events.bin
   * @param eventList the variable to EventList.
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