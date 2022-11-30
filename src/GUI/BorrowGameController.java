package GUI;

import Model.BoardGameManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;

public class BorrowGameController
{
@FXML RadioButton name,type,numOfPlayers;
@FXML RadioButton available,reserved, borrowed,allGames;
@FXML TextField searchField;
@FXML Button searchButton;
@FXML TableView games;
@FXML Button save;
@FXML ComboBox borrower;
@FXML DatePicker pickUpDate;
@FXML DatePicker returnDate;
@FXML TableView reservations;
@FXML Button home;

@FXML Button back;

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

  public void actionHandler(ActionEvent e){
    if (e.getSource()==back) viewHandler.openView("Menu");
  }
}
