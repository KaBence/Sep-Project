package Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * It's a list of board games
 */
public class BoardGameList implements Serializable
{
   private ArrayList<BoardGame> boardGames;

  /**
   * method for creating new array list of type board game
   */
  public BoardGameList(){
     boardGames=new ArrayList<>();
   }

   /**
    * Returns the number of elements in the board game list.
    * @return The size of the boardGames arraylist.
    */
   public int size(){
     return boardGames.size();
   }

   /**
    * This function returns the board game at the given index.
    * @param index the index of the element to return
    * @return The board game at the index.
    */
   public BoardGame get(int index){
     return boardGames.get(index);
   }

   /**
    * This function adds a board game to the list of board games
    * @param boardGame The board game to be added to the list.
    */
   public void addBoardGame(BoardGame boardGame){
     boardGames.add(boardGame);
   }

   /**
    * Remove the board game from the list of board games.
    * @param boardGame The board game to be removed from the list.
    */
   public void removeBoardGame(BoardGame boardGame){
     boardGames.remove(boardGame);
   }

  /**
   * This function get the board game by its type from the board game size of the type BoardGameList.
   * @param type The type of board game you want to search for.
   * @return A BoardGameList game selected by its type.
   */
  public BoardGameList getBoardGamesByType(String type){
    BoardGameList temp=new BoardGameList();
    for (int i = 0; i < boardGames.size(); i++)
    {
      if (boardGames.get(i).getType().contains(type)){
        temp.addBoardGame(boardGames.get(i));
      }
    }
    return temp;
  }

  /**
   * This function get the board game by the total number of players from the board game size of the type BoardGameList.
   * @param num the number of players
   * @return  BoardGameList game selected by the number of players.
   */
  public BoardGameList getBoardGamesByNoP(int num){
    BoardGameList temp=new BoardGameList();
    for (int i = 0; i < boardGames.size(); i++)
    {
      if (boardGames.get(i).getMaxNoP()>=num&&boardGames.get(i).getMinNoP()<=num)temp.addBoardGame(boardGames.get(i));
    }
    return temp;
  }

  /**
   * This function returns a list of board games that contain the name of game of the type BoardGameList.
   * @param name The name of the board game you want to search for.
   * @return A BoardGameList selected by its name.
   */
  public BoardGameList getBoardGamesByName(String name){
    BoardGameList temp=new BoardGameList();
    for (int i = 0; i < boardGames.size(); i++)
    {
      if (boardGames.get(i).getName().contains(name)) temp.addBoardGame(boardGames.get(i));
    }
    return temp;
  }

  /**
   * > This function takes a list of board games and a boolean value and returns a list of board games that have the same
   * availability as the boolean value
   * @param previous the list of board games to filter
   * @param availability true if you want to get the available games, false if you want to get the unavailable games
   * @return A list of board games that are available or not available.
   */
  public BoardGameList getBoardGamesByAvailability(BoardGameList previous,boolean availability){
    BoardGameList temp=new BoardGameList();
     for (BoardGame item: previous.boardGames){
       if (item.isAvailable()==availability) temp.addBoardGame(item);
     }
     return temp;
  }

  /**
   * > This function takes a list of board games and returns a list of board games that are borrowed
   * @param previous the list of board games to be filtered
   * @return A list of board games that are borrowed.
   */
  public BoardGameList getBoardGamesByBorrow(BoardGameList previous){
    BoardGameList temp=new BoardGameList();
     for (BoardGame item: previous.boardGames){
       if (item.isBorrowed())temp.addBoardGame(item);
     }
     return temp;
  }

  /**
   * This function takes a list of board games and returns a list of board games that are reserved
   * @param previous The list of board games that you want to filter.
   * @return A list of board games that are reserved.
   */
  public BoardGameList getBoardGamesByReserved(BoardGameList previous){
    BoardGameList temp=new BoardGameList();
     for (BoardGame item:previous.boardGames){
       if (item.isReserved())temp.addBoardGame(item);
     }
     return temp;
  }

  /**
   * This function returns a list of board games owned by the owner of the game.
   * @param owner The owner of the board games you want to get.
   * @return A BoardGameList object.
   */
  public BoardGameList getBoardGamesByOwner(Member owner){
    BoardGameList temp=new BoardGameList();
    for (BoardGame item:boardGames){
      if (item.getOwner().equals(owner)) temp.addBoardGame(item);
    }
    return temp;
  }
    /**
     * This function returns a string that contains all the board games in the collection
     * @return The toString method is returning a string of all the board games in the collection.
     */
    public String toString(){
       String a = "";
       for (int i = 0; i<boardGames.size();i++){
           a+=boardGames.get(i).toString()+" \n";
       }
       return a;
    }

}
