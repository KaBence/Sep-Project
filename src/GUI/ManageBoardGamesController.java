package GUI;

import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.skin.TableRowSkinBase;
import javafx.scene.input.MouseEvent;

import java.util.Optional;

/**
 * A controller for manageBoardGames fxml file
 * @author Bence Kabaly
 */

public class ManageBoardGamesController
{
  @FXML RadioButton name, type, numOfPlayers;
  @FXML RadioButton available, reserved, borrowed, allGames;
  @FXML TextField searchField;
  @FXML Button search;
  @FXML TableView<BoardGame> games;
  @FXML Button back;
  @FXML TableColumn<BoardGame, String> tableColName;
  @FXML TableColumn<BoardGame, String> tableColType;
  @FXML TableColumn<BoardGame, Integer> tableColMinNoP;
  @FXML TableColumn<BoardGame, Integer> tableColMaxNoP;
  @FXML TableColumn<BoardGame, Member> tableColOwner;

  private ViewHandler viewHandler;
  private BoardGameManager boardGameManager;
  private Scene scene;

  /**
   * After everything is loaded properlu this method is automaticallu called and sets the table columns to it's proper values
   */

  public void initialize()
  {
    tableColName.setCellValueFactory(
        new PropertyValueFactory<BoardGame, String>("name"));
    tableColType.setCellValueFactory(
        new PropertyValueFactory<BoardGame, String>("type"));
    tableColMinNoP.setCellValueFactory(
        new PropertyValueFactory<BoardGame, Integer>("minNoP"));
    tableColMaxNoP.setCellValueFactory(
        new PropertyValueFactory<BoardGame, Integer>("maxNoP"));
    tableColOwner.setCellValueFactory(
        new PropertyValueFactory<BoardGame, Member>("owner"));
    tableColOwner.setText("Owner");
  }

  /**
   * A method for setting the parameters
   * @param viewHandler sets the viewHandler
   * @param scene sets The scene
   * @param boardGameManager sets the BoardGameManager
   */

  public void init(ViewHandler viewHandler, Scene scene,
      BoardGameManager boardGameManager)
  {
    this.viewHandler = viewHandler;
    this.scene = scene;
    this.boardGameManager = boardGameManager;
  }

  /**
   * A method for updating the tableView with the given parameter
   * @param boardGameList This parameter gets filled to the tableView
   */

  public void update(BoardGameList boardGameList)
  {

    games.getItems().clear();
    for (int i = 0; i < boardGameList.size(); i++)
    {
      games.getItems().add(boardGameList.get(i));
    }
  }

  /**
   * Returns the scene of the ManageBoardGame
   * @return return rhe current controller scene
   */
  public Scene getScene()
  {
    return scene;
  }

  /**
   * Sorts the tableView based on what radioButton is checked
   */

  public void sorting(){
    BoardGameList boardGameList=boardGameManager.getAllBoardGames(); //
    BoardGameList tempBoardGameList=new BoardGameList();//O(1) creates an empty boardGameList
    BoardGameList finalBoardGameList=new BoardGameList();//O(1) creates an empty boardGameList

    if (name.isSelected()){// O(1)
      tempBoardGameList=boardGameList.getBoardGamesByName(searchField.getText()); //O(n) this method has a for loop inside that takes O(n) time
    }
    if (type.isSelected()){// O(1)
      tempBoardGameList=boardGameList.getBoardGamesByType(searchField.getText());//O(n) this method has a for loop inside that takes O(n) time
    }
    if (numOfPlayers.isSelected()){// O(1)
      if (!(searchField.getText().isEmpty())){// O(1)
        tempBoardGameList=boardGameList.getBoardGamesByNoP(Integer.parseInt(searchField.getText()));//O(n) this method has a for loop inside that takes O(n) time
      }
    }
    //if available is selected it shows all available games minus the borrowed games
    if (available.isSelected()){// O(1)
      tableColOwner.setText("Owner");// O(1)
      tableColOwner.setCellValueFactory(new PropertyValueFactory<BoardGame,Member>("owner"));// O(1)
      if (searchField.getText().isEmpty()) finalBoardGameList=boardGameList.getBoardGamesByAvailability(boardGameList,true);// If is O(1) and //O(n) this method has a for loop inside that takes O(n) time
      else finalBoardGameList=boardGameList.getBoardGamesByAvailability(tempBoardGameList,true);
      BoardGameList temp=boardGameList.getBoardGamesByBorrow(boardGameList); //O(n) this method has a for loop inside that takes O(n) time
      for (int i = 0; i < finalBoardGameList.size(); i++)//The for loop takes 2n+2
      {
        for (int j = 0; j < temp.size(); j++) //the for loop takes 2n+2 at every object of the first loop
        {
          if (finalBoardGameList.get(i).equals(temp.get(j)))finalBoardGameList.removeBoardGame(finalBoardGameList.get(i));//if takes O(1) time and the removing takes o(1) time as well
        }
      }
      //the nested for loop takes O(n^2)
      update(finalBoardGameList); //O(n) this method has a for loop inside that takes O(n) time
    }
    //Shows the borrowed games and changes the owner column to borrower
    if (borrowed.isSelected()){// O(1)
      if (searchField.getText().isEmpty())finalBoardGameList=boardGameList.getBoardGamesByBorrow(boardGameList); // If is O(1) and //O(n) this method has a for loop inside that takes O(n) time
      else finalBoardGameList=boardGameList.getBoardGamesByBorrow(tempBoardGameList);
      tableColOwner.setText("Borrower");// O(1)
      tableColOwner.setCellValueFactory(new PropertyValueFactory<BoardGame,Member>("borrower"));// O(1)

      update(finalBoardGameList); //O(n) this method has a for loop inside that takes O(n) time
    }
    //Shows the reserved games
    if (reserved.isSelected()){// O(1)
      tableColOwner.setText("Owner");// O(1)
      tableColOwner.setCellValueFactory(new PropertyValueFactory<BoardGame,Member>("owner"));
      if (searchField.getText().isEmpty())finalBoardGameList=boardGameList.getBoardGamesByReserved(boardGameList); // If is O(1) and //O(n) this method has a for loop inside that takes O(n) time
      else finalBoardGameList=boardGameList.getBoardGamesByReserved(tempBoardGameList);
      update(finalBoardGameList);//O(n) this method has a for loop inside that takes O(n) time
    }
    //shows every game
    if (allGames.isSelected()){// O(1)
      tableColOwner.setText("Owner");// O(1)
      tableColOwner.setCellValueFactory(new PropertyValueFactory<BoardGame,Member>("owner"));// O(1)
      if (searchField.getText().isEmpty()) update(boardGameList); // If is O(1) and //O(n) this method has a for loop inside that takes O(n) time
      else update(tempBoardGameList);//O(n) this method has a for loop inside that takes O(n) time
    }
  }

  /**
   * Changes the borrowed radioButton check status
   * @param borrowed sets the status of the radiobutton
   */

  public void setBorrowed(boolean borrowed)
  {
    this.borrowed.setSelected(borrowed);
  }

  /**
   * Changes the allGames radioButton check status
   * @param allGames sets the status of the radiobutton
   */
  public  void setAllGames(boolean allGames){
    this.allGames.setSelected(allGames);
  }

  /**
   * Changes the availabe radioButton check status
   * @param avl sets the status of the radiobutton
   */

  public void setAvailable(boolean avl){
    this.available.setSelected(avl);
  }

  /**
   * A method for handling the actions with the buttons
   * @param e  the event that is called when something happens
   */

  public void actionHandler(ActionEvent e)
  {
    if (e.getSource() == back) viewHandler.start();
    if (e.getSource()==search) sorting();
  }

  /**
   * Sets the boardGame's borrow class to null and saves it to the file
   * @param boardGame This boardGame is getting returned
   */

  public void ReturnGame(BoardGame boardGame){
    BoardGameList boardGameList=boardGameManager.getAllBoardGames();
    for (int i = 0; i < boardGameList.size(); i++)
    {
      if (boardGameList.get(i).equals(boardGame))boardGameList.get(i).setBorrow(null);
    }
    boardGameManager.saveAllBoardGames(boardGameList);
  }

  /**
   * This method is listening for the double click inside the tableView
   * @param event Listens for any mouse events
   */

  public void tableAction(MouseEvent event)
  {
    BoardGame row = games.getSelectionModel().getSelectedItem();
    if (event.getClickCount() == 2 && !(row == null))
    {
      viewHandler.getShowBoardGameController().setShowBoardGame(row);
      viewHandler.getMakeReservationController().setSelectedGame(row);
      viewHandler.getBorrowGameController().setSelectedBoardGame(row);
      //if the value is 1 it opens the selected boardGame reservation window
      if (viewHandler.getMenuController().getValue() == 1) {
        if (!row.isAvailable()){
          Alert alert=new Alert(Alert.AlertType.WARNING,"You can only reserve a game if it is available",ButtonType.OK);
          alert.setTitle("Warning");
          alert.setHeaderText(null);
          alert.showAndWait();
          return;
        }
        viewHandler.openView("reservation");
      }
      //if the value is 2 it opens the selected boardGame borrow window
      else if (viewHandler.getMenuController().getValue() == 2){
        if (!row.isAvailable()){
          Alert alert=new Alert(Alert.AlertType.ERROR,"You can only borrow an available Game",ButtonType.OK);
          alert.setHeaderText(null);
          alert.setTitle("Warning");
          alert.showAndWait();
          return;
        }
        viewHandler.getBorrowGameController().updateUpperPart();
        viewHandler.openView("borrow");
      }
      //if the value is 3 it opens the selected boardGame return window
      else if (viewHandler.getMenuController().getValue() == 3) {

      if (!row.isBorrowed()){
        Alert alert=new Alert(Alert.AlertType.ERROR,"You can only return a borrowed game",ButtonType.OK);
        alert.setHeaderText(null);
        alert.setTitle("Warning");
        alert.showAndWait();
        return;
      }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Would you like to leave a review?",ButtonType.YES,ButtonType.NO);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES){
          viewHandler.getReturnGameController().setSelectedGame(row.getName());
          viewHandler.openView("returnGame");
        }
        else {
          ReturnGame(row);
          viewHandler.openView("Menu");
        }

      }
      else
        viewHandler.openView("showBoardGame");
    }

  }

}
