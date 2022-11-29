package GUI;

import Model.BoardGameClub;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ManageMemberController
{

  private ViewHandler viewHandler;
  private BoardGameClub clubmanager;
  private Scene scene;

  @FXML
  RadioButton name;
  @FXML
  RadioButton phone;
  @FXML
  RadioButton email;
  @FXML
  TextField search;
  @FXML
  Button searchButton;
  @FXML
  TableView tableView;
  @FXML Button back;


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
    if (e.getSource()==back) viewHandler.openView("Menu");
  }
}
