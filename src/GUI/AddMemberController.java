package GUI;

import Model.BoardGameClub;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddMemberController
{

  private ViewHandler viewHandler;
  private BoardGameClub clubmanager;
  private Scene scene;

  @FXML
  TextField fName;

  @FXML TextField lastName;
  @FXML TextField phone;
  @FXML TextField email;

  @FXML
  Button yesButton;
  @FXML
  Button noButton;
  @FXML
  Button back;
  @FXML
  Button addTheMember;

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
