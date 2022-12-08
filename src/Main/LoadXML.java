package Main;

import Model.BoardGameList;
import Model.BoardGameManager;
import parser.ParserException;
import parser.XmlJsonParser;

import java.io.File;
import java.io.IOException;

public class LoadXML
{
  public static void main(String[] args)
  {
    XmlJsonParser parser=new XmlJsonParser();
    BoardGameManager boardGameManager=new BoardGameManager("Boardgames.bin","members.bin","events.bin");
    BoardGameList boardGameList=boardGameManager.getAllBoardGames();
    BoardGameList boardGameListAvl= boardGameList.getBoardGamesByAvailability(boardGameList,true);

    try
    {
      File file = parser.toXml(boardGameListAvl,"Website/Xml/BoardgamesAvl.xml");
    }
    catch (ParserException e){
      System.out.println("Parser Exception");
    }
    BoardGameList boardGameListNonAvl= boardGameList.getBoardGamesByAvailability(boardGameList,false);
    try
    {
      File file = parser.toXml(boardGameListNonAvl,"Website/Xml/BoardgamesNonAvl.xml");
    }
    catch (ParserException e){
      System.out.println("Parser Exception");
    }
  }
}
