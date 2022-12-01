package GUI;

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
  @FXML Button remove;
  @FXML Button seeReviews;

  private ViewHandler viewHandler;
  private BoardGameManager boardGameManager;
  private Scene scene;


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

  }
}
