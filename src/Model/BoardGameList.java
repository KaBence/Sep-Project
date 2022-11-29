package Model;

import java.util.ArrayList;

public class BoardGameList
{
   private ArrayList<BoardGame> boardGames;
   public BoardGameList(){
     boardGames=new ArrayList<>();
   }

   public void addBoardGame(BoardGame boardGame){
     boardGames.add(boardGame);
   }

   public void removeBoardGame(BoardGame boardGame){
     boardGames.remove(boardGame);
   }

  public BoardGameList getBoardGamesByType(String type){
     BoardGameList temp=new BoardGameList();
     for (BoardGame item: boardGames){
       if (item.getType().equals(type)){
         temp.addBoardGame(item);
       }
     }
     return temp;
  }

  public BoardGameList getBoardGamesByNoP(int num){
     BoardGameList temp=new BoardGameList();
     for (BoardGame item: boardGames){
       if (item.getMaxNoP()>num&&item.getMinNoP()<num)temp.addBoardGame(item);
     }
     return temp;
  }

  public BoardGameList getBoardGamesByAvailability(boolean availability){
     BoardGameList temp=new BoardGameList();
     for (BoardGame item:boardGames){
       if (item.isAvailable()) temp.addBoardGame(item);
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

  public BoardGameList getBoardGamesByName(String name){
    BoardGameList temp=new BoardGameList();
    for (BoardGame item:boardGames){
      if (item.getName().equals(name)) temp.addBoardGame(item);
    }
    return temp;
  }
}
