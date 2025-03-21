package Main;

import Model.BoardGameList;
import Model.BoardGameManager;
import Model.EventList;
import parser.ParserException;
import parser.XmlJsonParser;

/**
 * A class for creating xml files from the bin files
 * @author Bence Kabaly
 */
public class LoadXML
{
  /**
   *  The main method that starts when this is started
   * @param args We can give arguments when the program starts
   */
  public static void main(String[] args)
  {
    XmlJsonParser parser=new XmlJsonParser();
    BoardGameManager boardGameManager=new BoardGameManager("Boardgames.bin","members.bin","events.bin");
    BoardGameList boardGameList=boardGameManager.getAllBoardGames();
    BoardGameList boardGameListAvl= boardGameList.getBoardGamesByAvailability(boardGameList,true);

    try
    {
      parser.toXml(boardGameListAvl,"Website/Xml/BoardgamesAvl.xml");
    }
    catch (ParserException e){
      System.out.println("Parser Exception");
    }
    BoardGameList boardGameListNonAvl= boardGameList.getBoardGamesByAvailability(boardGameList,false);
    try
    {
      parser.toXml(boardGameListNonAvl,"Website/Xml/BoardgamesNonAvl.xml");
    }
    catch (ParserException e){
      System.out.println("Parser Exception");
    }
    EventList eventList=boardGameManager.getAllEvents();
    try
    {
      parser.toXml(eventList,"Website/Xml/Events.xml");
    }
    catch (ParserException e){
      System.out.println("Parser exception");
    }
  }
}
