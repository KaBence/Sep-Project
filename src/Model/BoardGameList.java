package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class BoardGameList implements Serializable
{
   private ArrayList<BoardGame> boardGames;
   public BoardGameList(){
     boardGames=new ArrayList<>();
   }

   public int size(){
     return boardGames.size();
   }

   public BoardGame get(int index){
     return boardGames.get(index);
   }

   public void addBoardGame(BoardGame boardGame){
     boardGames.add(boardGame);
   }

   public void removeBoardGame(BoardGame boardGame){
     boardGames.remove(boardGame);
   }

  public BoardGameList getBoardGamesByType(String type){
    BoardGameList temp=new BoardGameList();
    for (int i = 0; i < boardGames.size(); i++)
    {
      if (boardGames.get(i).getType().equals(type)){
        temp.addBoardGame(boardGames.get(i));
      }
    }
    return temp;
  }

  public BoardGameList getBoardGamesByNoP(int num){
    BoardGameList temp=new BoardGameList();
    for (int i = 0; i < boardGames.size(); i++)
    {
      if (boardGames.get(i).getMaxNoP()>=num&&boardGames.get(i).getMinNoP()<=num)temp.addBoardGame(boardGames.get(i));
    }
    return temp;
  }

  public BoardGameList getBoardGamesByName(String name){
    BoardGameList temp=new BoardGameList();
    for (int i = 0; i < boardGames.size(); i++)
    {
      if (boardGames.get(i).getName().contains(name)) temp.addBoardGame(boardGames.get(i));
    }
    return temp;
  }

  public BoardGameList getBoardGamesByAvailability(BoardGameList previous,boolean availability){
    BoardGameList temp=new BoardGameList();
     for (BoardGame item: previous.boardGames){
       if (item.isAvailable()==availability) temp.addBoardGame(item);
     }
     return temp;
  }

  public BoardGameList getBoardGamesByBorrow(BoardGameList previous){
    BoardGameList temp=new BoardGameList();
     for (BoardGame item: previous.boardGames){
       if (item.isBorrowed())temp.addBoardGame(item);
     }
     return temp;
  }

  public BoardGameList getBoardGamesByReserved(BoardGameList previous){
    BoardGameList temp=new BoardGameList();
     for (BoardGame item:previous.boardGames){
       if (item.isReserved())temp.addBoardGame(item);
     }
     return temp;
  }

  public BoardGameList getBoardGamesByOwner(Member owner){
    BoardGameList temp=new BoardGameList();
    for (BoardGame item:boardGames){
      if (item.getOwner().equals(owner)) temp.addBoardGame(item);
    }
    return temp;
  }


}
