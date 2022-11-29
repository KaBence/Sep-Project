package GUI;

import Model.BoardGameClub;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class AddBoardGameController
{
  @FXML TextField name;
  @FXML TextField min;
  @FXML TextField max;
  @FXML TextField type;
  @FXML ComboBox owner;
  @FXML Button back;
  @FXML Button addForVoting;
  @FXML Button AddToTheRegistry;
  private ViewHandler viewHandler;
  private BoardGameClub clubmanager;
  private Scene scene;

  public void init(ViewHandler viewHandler, Scene scene,
      BoardGameClub clubManager)
  {
    this.viewHandler = viewHandler;
    this.scene = scene;
    this.clubmanager = clubmanager;
  }

  public Scene getScene()
  {
    return scene;
  }

  public void actionHandler(ActionEvent e)
  {

  }
}
