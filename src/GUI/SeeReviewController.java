package GUI;

import Model.BoardGameManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class SeeReviewController
{
  @FXML TextField nameOfBoardGame;
  @FXML TableView reviews;
  @FXML Button back;
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
    if (e.getSource()==back) viewHandler.openView("manageBoardGame");
  }
}
