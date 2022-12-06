package GUI;

import Model.BoardGame;
import Model.BoardGameManager;
import Model.Rank;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class SeeReviewController
{
  @FXML TextField nameOfBoardGame,rank;
  @FXML TableView reviews;
  @FXML TableColumn<BoardGame,String> tableText;
  @FXML Button back;
  private ViewHandler viewHandler;
  private BoardGameManager boardGameManager;
  private Scene scene;
  private BoardGame selectedBoardGame;
  public void init(ViewHandler viewHandler, Scene scene, BoardGameManager boardGameManager)
  {
    this.viewHandler = viewHandler;
    this.scene = scene;
    this.boardGameManager = boardGameManager;
  }

  public void initialize(){
    nameOfBoardGame.setEditable(false);
    rank.setEditable(false);

  }

  public void update(){
    reviews.getItems().clear();
    if (selectedBoardGame.getRankList()==null) tableText.setCellValueFactory(null);
    else tableText.setCellValueFactory(new PropertyValueFactory<BoardGame,String>("review"));
    if (selectedBoardGame.getRankList()==null){
      reviews.getItems().add("Empty");
      rank.setText(String.valueOf(0));
    }
    else {
      for (int i = 0; i < selectedBoardGame.getRankList().size(); i++)
      {
        reviews.getItems().add(selectedBoardGame.getRankList().get(i));
        rank.setText(String.valueOf(selectedBoardGame.getRankList().getAverage()));
      }
    }
    nameOfBoardGame.setText(selectedBoardGame.getName());
  }


  public void setSelectedBoardGame(BoardGame selectedBoardGame) {
    this.selectedBoardGame = selectedBoardGame;
  }

  public Scene getScene(){
    return scene;
  }

  public void actionHandler(ActionEvent e){
    if (e.getSource()==back) viewHandler.openView("manageBoardGame");
  }
}
