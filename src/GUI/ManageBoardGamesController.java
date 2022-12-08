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
    BoardGameList boardGameList=boardGameManager.getAllBoardGames();
    BoardGameList tempBoardGameList=new BoardGameList();
    BoardGameList finalBoardGameList=new BoardGameList();

    if (name.isSelected()){
      tempBoardGameList=boardGameList.getBoardGamesByName(searchField.getText());
    }
    if (type.isSelected()){
      tempBoardGameList=boardGameList.getBoardGamesByType(searchField.getText());
    }
    if (numOfPlayers.isSelected()){
      if (!(searchField.getText().isEmpty())){
        tempBoardGameList=boardGameList.getBoardGamesByNoP(Integer.parseInt(searchField.getText()));
      }
    }
    //if available is selected it shows all available games minus the borrowed games
    if (available.isSelected()){
      tableColOwner.setText("owner");
      tableColOwner.setCellValueFactory(new PropertyValueFactory<BoardGame,Member>("owner"));
      if (searchField.getText().isEmpty()) finalBoardGameList=boardGameList.getBoardGamesByAvailability(boardGameList,true);
      else finalBoardGameList=boardGameList.getBoardGamesByAvailability(tempBoardGameList,true);
      BoardGameList temp=boardGameList.getBoardGamesByBorrow(boardGameList);
      for (int i = 0; i < finalBoardGameList.size(); i++)
      {
        for (int j = 0; j < temp.size(); j++)
        {
          if (finalBoardGameList.get(i).equals(temp.get(j)))finalBoardGameList.removeBoardGame(finalBoardGameList.get(i));
        }
      }
      update(finalBoardGameList);
    }
    //Shows the borrowed games and changes the owner column to borrower
    if (borrowed.isSelected()){
      if (searchField.getText().isEmpty())finalBoardGameList=boardGameList.getBoardGamesByBorrow(boardGameList);
      else finalBoardGameList=boardGameList.getBoardGamesByBorrow(tempBoardGameList);
      tableColOwner.setText("Borrower");
      tableColOwner.setCellValueFactory(new PropertyValueFactory<BoardGame,Member>("borrower"));

      update(finalBoardGameList);
    }
    //Shows the reserved games
    if (reserved.isSelected()){
      tableColOwner.setText("owner");
      tableColOwner.setCellValueFactory(new PropertyValueFactory<BoardGame,Member>("owner"));
      if (searchField.getText().isEmpty())finalBoardGameList=boardGameList.getBoardGamesByReserved(boardGameList);
      else finalBoardGameList=boardGameList.getBoardGamesByReserved(tempBoardGameList);
      update(finalBoardGameList);
    }
    //shows every game
    if (allGames.isSelected()){
      tableColOwner.setText("owner");
      tableColOwner.setCellValueFactory(new PropertyValueFactory<BoardGame,Member>("owner"));
      if (searchField.getText().isEmpty()) update(boardGameList);
      else update(tempBoardGameList);
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
