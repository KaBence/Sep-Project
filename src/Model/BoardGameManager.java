package Model;

import Util.MyFileHandler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;


public class BoardGameManager implements Serializable
{
  private String boardgames;
  private String members;
  private String events;

  private MemberList memberList;
  private BoardGameList boardGameList;
  private EventList eventList;

  public BoardGameManager(String boardgames,String members,String events)
  {
    this.events=events;
    this.members=members;
    this.boardgames=boardgames;
  }



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