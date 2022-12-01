package Main;

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
