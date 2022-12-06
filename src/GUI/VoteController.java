package GUI;

import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class VoteController
{

  private ViewHandler viewHandler;
  private BoardGameManager boardGameManager;
  private Scene scene;
  private BoardGameList boardGameList;
  @FXML Button back, addVote, removeVote, search;
  @FXML TextField textfield;

  @FXML TableView<BoardGame> games;
  @FXML TableColumn<BoardGame, String> TName;
  @FXML TableColumn<BoardGame, Integer> TVote;

  public void initialize()
  {
    TName.setCellValueFactory(
        new PropertyValueFactory<BoardGame, String>("name"));
    TVote.setCellValueFactory(
        new PropertyValueFactory<BoardGame, Integer>("vote"));
  }

  public void update()
  {
    BoardGameList boardGameList = boardGameManager.getAllBoardGames();
    BoardGameList temp = boardGameList.getBoardGamesByAvailability(
        boardGameList, false);
    games.getItems().clear();
    for (int i = 0; i < temp.size(); i++)
    {
      games.getItems().add(temp.get(i));
    }
  }

  public void updateList(BoardGameList list)
  {
    games.getItems().clear();
    boardGameList = list;
    for (int i = 0; i < boardGameList.size(); i++)
    {
      games.getItems().add(boardGameList.get(i));
    }
  }
public void clean(){
    textfield.clear();
}
  public void init(ViewHandler viewHandler, Scene scene,
      BoardGameManager boardGameManager)
  {
    this.viewHandler = viewHandler;
    this.scene = scene;
    this.boardGameManager = boardGameManager;
  }

  public Scene getScene()
  {
    return scene;
  }

  public void actionHandler(ActionEvent e)
  {
    BoardGameList boardGameList1 = boardGameManager.getAllBoardGames();
    BoardGameList temp = boardGameList1.getBoardGamesByAvailability(
        boardGameList1, false);

    if (e.getSource() == back)
      viewHandler.openView("Menu");
    if (e.getSource() == addVote)
    {

      BoardGame boardGame1 = games.getSelectionModel().getSelectedItem();


      BoardGameList boardGameList = boardGameManager.getAllBoardGames();

      BoardGame boardGame2 = new BoardGame(boardGame1.getName(),
          boardGame1.getType(), boardGame1.getMinNoP(), boardGame1.getMaxNoP(),
          boardGame1.getOwner(), boardGame1.isAvailable());
      boardGame2.setVoteList(boardGame1.getVoteList());
      boardGameList.removeBoardGame(boardGame1);
      boardGame2.getVoteList().addVote();
      System.out.println("vote added");
      boardGameList.addBoardGame(boardGame2);
      boardGameManager.saveAllBoardGames(boardGameList);
      update();
    }
    if (e.getSource() == removeVote)
    {

      BoardGame boardGame1 = games.getSelectionModel().getSelectedItem();

      BoardGameList boardGameList = boardGameManager.getAllBoardGames();

      BoardGame boardGame2 = new BoardGame(boardGame1.getName(),
          boardGame1.getType(), boardGame1.getMinNoP(), boardGame1.getMaxNoP(),
          boardGame1.getOwner(), boardGame1.isAvailable());
      boardGame2.setVoteList(boardGame1.getVoteList());
      boardGameList.removeBoardGame(boardGame1);
      if(boardGame2.getVote()==0){
        Alert alert =new Alert(Alert.AlertType.WARNING,"YOU ARE A DISGRACE...don't go below zero",ButtonType.OK);
      alert.setTitle("stupid");
      alert.setHeaderText(null);
        alert.showAndWait();
        return;
      }
      boardGame2.getVoteList().removeVote();
      System.out.println("vote removed");
      boardGameList.addBoardGame(boardGame2);
      boardGameManager.saveAllBoardGames(boardGameList);
      update();
    }
    if (e.getSource() == search)
    {
      if (textfield.getText().isEmpty())
      {
        updateList(temp);
      }
      else
      {
        BoardGameList boardGameList2 = boardGameList1.getBoardGamesByName(
            textfield.getText());
        updateList(boardGameList2);
        clean();
      }

    }
  }
}
