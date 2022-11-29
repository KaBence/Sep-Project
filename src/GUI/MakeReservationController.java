package GUI;

import Model.BoardGameClub;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;

public class MakeReservationController
{

  @FXML Button save;
  @FXML ComboBox borrower;
  @FXML DatePicker pickUpDate;
  @FXML DatePicker returnDate;
  @FXML TableView reservations;
  @FXML Button home;
  @FXML Button back;
  @FXML Button edit;
  @FXML Button remove;

  private ViewHandler viewHandler;
  private BoardGameClub clubmanager;
  private Scene scene;


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
