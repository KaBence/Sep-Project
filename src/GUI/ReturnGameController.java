package GUI;

import Model.BoardGameManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ReturnGameController
{
  private ViewHandler viewHandler;
  private BoardGameManager clubmanager;
  private Scene scene;

  @FXML Button back,returnB,search;
  @FXML TextField textField;
  @FXML RadioButton RBName,RBType,RBNoP,RBBorrowed,RBAvl,RBReserved,RBAllGames;

  @FXML Button cancel,submit;
  @FXML RadioButton RB1,RB2,RB3,RB4,RB5;
  @FXML TextArea textArea;

  public void init(ViewHandler viewHandler, Scene scene, BoardGameManager clubManager)
  {
    this.viewHandler = viewHandler;
    this.scene = scene;
    this.clubmanager = clubmanager;
  }

  public Scene getScene(){
    return scene;
  }

  public void actionHandler(ActionEvent e){
    if (e.getSource()==cancel) viewHandler.openView("Menu");
  }
}
