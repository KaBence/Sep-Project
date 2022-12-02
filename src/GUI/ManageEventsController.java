package GUI;

import Model.BoardGameManager;
import Model.Event;
import Model.EventList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

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
  @FXML TextArea aList;
  @FXML TableView events;
  @FXML TableColumn<Event,String>tableName;
  @FXML TableColumn<Event,String>tableLocation;
  @FXML TableColumn<Event,String>tableDate;

  public void initialize(){
    tableName.setCellValueFactory(new PropertyValueFactory<Event,String>("name"));
    tableLocation.setCellValueFactory(new PropertyValueFactory<Event,String>("location"));
    tableDate.setCellValueFactory(new PropertyValueFactory<Event,String>("date"));
  }

  public void init(ViewHandler viewHandler, Scene scene, BoardGameManager boardGameManager)
  {
    this.viewHandler = viewHandler;
    this.scene = scene;
    this.boardGameManager = boardGameManager;
  }

  public void update(){
    EventList eventList= boardGameManager.getAllEvents();
    for (int i = 0; i < eventList.size(); i++)
    {
      events.getItems().add(eventList.get(i));
    }
  }

  public Scene getScene(){
    return scene;
  }

  public void actionHandler(ActionEvent e){
    if (e.getSource()==back) viewHandler.openView("Menu");
  }
}
