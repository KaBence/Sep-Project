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

public class VoteController
{

  private ViewHandler viewHandler;
  private BoardGameManager boardGameManager;
  private Scene scene;
  @FXML Button back, addVote,removeVote,search;
  @FXML TextField textField;
  @FXML RadioButton RBName,RBType,RBNoP;
  @FXML TableView<BoardGame> games;
  @FXML TableColumn<BoardGame,String> TName;
  @FXML TableColumn<BoardGame,Integer>TVote;

  public void initialize(){
    TName.setCellValueFactory(new PropertyValueFactory<BoardGame, String>("name"));
    TVote.setCellValueFactory(new PropertyValueFactory<BoardGame, Integer>("vote"));
  }

  public void update(){
    BoardGameList boardGameList=boardGameManager.getAllBoardGames();
    BoardGameList temp=boardGameList.getBoardGamesByAvailability(boardGameList,false);
    games.getItems().clear();
    for (int i = 0; i < temp.size(); i++)
    {
      games.getItems().add(temp.get(i));
    }
  }

  public void init(ViewHandler viewHandler, Scene scene, BoardGameManager boardGameManager)
  {
    this.viewHandler = viewHandler;
    this.scene = scene;
    this.boardGameManager = boardGameManager;
  }

  public Scene getScene(){
    return scene;
  }

  public void actionHandler(ActionEvent e){
    if (e.getSource()==back) viewHandler.openView("Menu");
  }
}
