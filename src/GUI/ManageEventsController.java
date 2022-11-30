package GUI;

import Model.BoardGameClub;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
public class ManageEventsController
{

  private ViewHandler viewHandler;
  private BoardGameClub clubmanager;
  private Scene scene;
  @FXML Button back;
  @FXML Button edit;
  @FXML Button delete;
  @FXML Button search;
  @FXML RadioButton rName;
  @FXML RadioButton rLocation;
  @FXML RadioButton rTime;
  @FXML RadioButton rAllEvents;
  @FXML TextField fSearch;
  @FXML TextArea aList;

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
