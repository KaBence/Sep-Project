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
  }

  public void init(ViewHandler viewHandler, Scene scene,
      BoardGameManager boardGameManager)
  {
    this.viewHandler = viewHandler;
    this.scene = scene;
    this.boardGameManager = boardGameManager;
  }

  public void update(BoardGameList boardGameList)
  {
    games.getItems().clear();
    for (int i = 0; i < boardGameList.size(); i++)
    {
      games.getItems().add(boardGameList.get(i));
    }
  }

  public Scene getScene()
  {
    return scene;
  }

  public void actionHandler(ActionEvent e)
  {
    BoardGameList boardGameList=boardGameManager.getAllBoardGames();
    BoardGameList tempBoardGameList=new BoardGameList();
    BoardGameList finalBoardGameList=new BoardGameList();
    if (e.getSource() == back) viewHandler.start();
    if (e.getSource()==search&&name.isSelected()){
      tempBoardGameList=boardGameList.getBoardGamesByName(searchField.getText());
    }
    if (e.getSource()==search&&type.isSelected()){
      tempBoardGameList=boardGameList.getBoardGamesByType(searchField.getText());
    }
    if (e.getSource()==search&&numOfPlayers.isSelected()){
      if (!(searchField.getText().isEmpty())){
        tempBoardGameList=boardGameList.getBoardGamesByNoP(Integer.parseInt(searchField.getText()));
      }
    }
    if (e.getSource()==search&&available.isSelected()){
      if (searchField.getText().isEmpty()) finalBoardGameList=boardGameList.getBoardGamesByAvailability(boardGameList,true);
      else finalBoardGameList=boardGameList.getBoardGamesByAvailability(tempBoardGameList,true);
      update(finalBoardGameList);
    }
    if (e.getSource()==search&&borrowed.isSelected()){
      if (searchField.getText().isEmpty())finalBoardGameList=boardGameList.getBoardGamesByBorrow(boardGameList);
      else finalBoardGameList=boardGameList.getBoardGamesByBorrow(tempBoardGameList);
      update(finalBoardGameList);
    }
    if (e.getSource()==search&&reserved.isSelected()){
      if (searchField.getText().isEmpty())finalBoardGameList=boardGameList.getBoardGamesByReserved(boardGameList);
      else finalBoardGameList=boardGameList.getBoardGamesByReserved(tempBoardGameList);
      update(finalBoardGameList);
    }
    if (e.getSource()==search&&allGames.isSelected()){
      if (searchField.getText().isEmpty()) update(boardGameList);
      else update(tempBoardGameList);
    }

  }

  public void ReturnGame(BoardGame boardGame){
    BoardGameList boardGameList=boardGameManager.getAllBoardGames();
    for (int i = 0; i < boardGameList.size(); i++)
    {
      if (boardGameList.get(i).equals(boardGame))boardGameList.get(i).setBorrow(null);
    }
    boardGameManager.saveAllBoardGames(boardGameList);
  }

  public void tableAction(MouseEvent event)
  {

    BoardGame row = games.getSelectionModel().getSelectedItem();
    if (event.getClickCount() == 2 && !(row == null))
    {
      viewHandler.getShowBoardGameController().setShowBoardGame(row);
      viewHandler.getMakeReservationController().setSelectedGame(row);
      viewHandler.getBorrowGameController().setSelectedBoardGame(row);

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
      else if (viewHandler.getMenuController().getValue() == 2){
        if (!row.isAvailable()){
          Alert alert=new Alert(Alert.AlertType.ERROR,"You can only borrow an available Game",ButtonType.OK);
          alert.setHeaderText(null);
          alert.setTitle("Warning");
          alert.showAndWait();
          return;
        }
        viewHandler.openView("borrow");
      }
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
