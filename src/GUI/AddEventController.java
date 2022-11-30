package GUI;

import Model.BoardGameManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
public class AddEventController
{
  @FXML Button back;
  @FXML Button addEvent;
  @FXML TextField name;
  @FXML TextField fLocation;
  @FXML TextField maxCapacity;
  @FXML ComboBox chooseGame;
  @FXML DatePicker date;
  private ViewHandler viewHandler;
  private BoardGameManager clubmanager;
  private Scene scene;

  public void init(ViewHandler viewHandler, Scene scene, BoardGameManager clubManager)
  {
    this.viewHandler = viewHandler;
    this.scene = scene;
    this.clubmanager = clubmanager;
  }

  public Scene getScene(){
    return scene;
  }

  public void actionHandler(ActionEvent e)
  {
    if (e.getSource()==back) viewHandler.openView("Menu");
  }
}
