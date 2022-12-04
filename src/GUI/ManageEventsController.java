package GUI;

import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class ManageEventsController
{

  private ViewHandler viewHandler;
  private BoardGameManager boardGameManager;
  private Scene scene;
  @FXML Button back;
  @FXML ToggleGroup toggle;
  @FXML Button edit;
  @FXML Button delete;
  @FXML Button search;
  @FXML RadioButton rName;
  @FXML RadioButton rLocation;
  @FXML RadioButton rTime;
  @FXML RadioButton rAllEvents;
  @FXML TextField fSearch;
  @FXML TableView<Event> events;
  @FXML TableColumn<Event,String> eventName;
  @FXML TableColumn<Event,MyDate> eventDate;
  @FXML TableColumn<Event,String> eventLocation;
  @FXML TableColumn<Event,String> eventGuests;
  public void initialize()
  {
    eventName.setCellValueFactory(new PropertyValueFactory<Event, String>("name"));
    eventDate.setCellValueFactory(new PropertyValueFactory<Event, MyDate>("date"));
    eventLocation.setCellValueFactory(new PropertyValueFactory<Event, String>("location"));
    eventGuests.setCellValueFactory(new PropertyValueFactory<Event, String>("guests"));

  }
  public void init(ViewHandler viewHandler, Scene scene, BoardGameManager boardGameManager)
  {
    this.viewHandler = viewHandler;
    this.scene = scene;
    this.boardGameManager = boardGameManager;
  }
  public void update(){
    EventList list = boardGameManager.getAllEvents();
    for (int i = 0; i < list.size(); i++)
    {
      events.getItems().add(list.get(i));
    }
  }
  public Scene getScene(){
    return scene;
  }

  public void actionHandler(ActionEvent e){
    if (e.getSource()==back) viewHandler.openView("Menu");
  }
}
