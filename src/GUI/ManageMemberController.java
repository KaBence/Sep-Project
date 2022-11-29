package GUI;

import Model.BoardGameClub;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class ManageMemberController
{

  private ViewHandler viewHandler;
  private BoardGameClub clubmanager;
  private Scene scene;

  @FXML
  Button n;
  @FXML
  Button noButton;
  @FXML
  Button back;
  public void init(ViewHandler viewHandler, Scene scene, BoardGameClub clubManager)
  {
    this.viewHandler = viewHandler;
    this.scene = scene;
    this.clubmanager = clubmanager;
  }

  public Scene getScene(){
    return scene;
  }

  public void actionHandler(ActionEvent e){

  }
}
