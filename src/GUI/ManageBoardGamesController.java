package GUI;

import Model.BoardGame;
import Model.BoardGameList;
import Model.BoardGameManager;
import Model.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.skin.TableRowSkinBase;
import javafx.scene.input.MouseEvent;

public class ManageBoardGamesController
{
  @FXML RadioButton name, type, numOfPlayers;
  @FXML RadioButton available, reserved, borrowed, allGames;
  @FXML TextField searchField;
  @FXML Button search;
  @FXML TableView<BoardGame> games;
  @FXML Button back;

  @FXML Button reserve;
  @FXML Button borrow;

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

  public void update()
  {

    BoardGameList boardGameList = boardGameManager.getAllBoardGames();
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
    if (e.getSource() == back)
      viewHandler.start();
    if (e.getSource() == reserve)
    {

    }
  }

  public void tableAction(MouseEvent event)
  {
    BoardGame row = games.getSelectionModel().getSelectedItem();
    if (event.getClickCount() == 2 && !(row == null))
    {
      viewHandler.getShowBoardGameController().setShowBoardGame(row);
      if (viewHandler.getMenuController().getValue() == 1)
        viewHandler.openView("reservation");
      else if (viewHandler.getMenuController().getValue() == 2)
        viewHandler.openView("borrow");
      else if (viewHandler.getMenuController().getValue() == 3)
        viewHandler.openView("returnGame");
      //.....................................
      else
        viewHandler.openView("showBoardGame");
    }

  }

}
