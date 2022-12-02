package GUI;

import Model.BoardGame;
import Model.BoardGameManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class ShowBoardGameController
{
  @FXML TextField nameOfGame;
  @FXML TextField min;
  @FXML TextField max;
  @FXML TextField typeOfBoardGame;
  @FXML ComboBox owner;
  @FXML RadioButton available2, nonAvailable2, reserved2, borrowed2;
  @FXML Button edit;
  @FXML Button reserve;
  @FXML Button remove;
  @FXML Button seeReviews;
  @FXML Button back;

  private ViewHandler viewHandler;
  private BoardGameManager boardGameManager;
  private Scene scene;
  private BoardGame showBoardGame;


  public void init(ViewHandler viewHandler, Scene scene, BoardGameManager boardGameManager)
  {
    this.viewHandler = viewHandler;
    this.scene = scene;
    this.boardGameManager = boardGameManager;
  }

  public Scene getScene(){
    return scene;
  }

  public BoardGame getShowBoardGame()
  {
    return showBoardGame;
  }

  public void setShowBoardGame(BoardGame showBoardGame)
  {
    this.showBoardGame = showBoardGame;
  }

  public void update(){
    nameOfGame.setText(showBoardGame.getName());
    typeOfBoardGame.setText(showBoardGame.getType());
    min.setText(String.valueOf(showBoardGame.getMinNoP()));
    max.setText(String.valueOf(showBoardGame.getMaxNoP()));
  }

  public void actionHandler(ActionEvent e){
    if (e.getSource()==back){
      viewHandler.openView("manageBoardGame");
    }
    if (e.getSource()==seeReviews){
      viewHandler.openView("seeReviews");
    }
    if (e.getSource()==reserve) viewHandler.openView("");
  }
}
