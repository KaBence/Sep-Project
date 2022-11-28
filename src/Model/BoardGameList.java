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


}
