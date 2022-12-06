package GUI;

import Model.*;
import Util.MyFileHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ManageEventsController
{

  private ViewHandler viewHandler;
  private BoardGameManager boardGameManager;
  private Scene scene;
  private EventList eventList;

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
  @FXML TextField time;
  @FXML DatePicker date;
  @FXML Label output;
  @FXML TableView<Event> events;
  @FXML TableColumn<Event, String> tableName;
  @FXML TableColumn<Event, String> tableLocation;
  @FXML TableColumn<Event, String> tableCapacity;
  @FXML TableColumn<Event, String> tableDate;

  public void initialize()
  {
    tableName.setCellValueFactory(
        new PropertyValueFactory<Event, String>("name"));
    tableLocation.setCellValueFactory(
        new PropertyValueFactory<Event, String>("location"));
    tableCapacity.setCellValueFactory(
        new PropertyValueFactory<Event, String>("capacity"));
    tableDate.setCellValueFactory(
        new PropertyValueFactory<Event, String>("date"));
    rAllEvents.setSelected(true);
    date.setVisible(false);
    time.setVisible(false);
  }

  public void init(ViewHandler viewHandler, Scene scene,
      BoardGameManager boardGameManager)
  {
    this.viewHandler = viewHandler;
    this.scene = scene;
    this.boardGameManager = boardGameManager;
  }

  public void update()
  {
    updateList(boardGameManager.getAllEvents());
  }

  public void updateList(EventList list)
  {
    events.getItems().clear();
    eventList = list;
    for (int i = 0; i < eventList.size(); i++)
    {
      events.getItems().add(eventList.get(i));
    }
  }

  public Scene getScene()
  {
    return scene;
  }


  public void actionHandler(ActionEvent e){
        Event row = events.getSelectionModel().getSelectedItem();
        EventList list = boardGameManager.getAllEvents();
        // back to the menu
        if (e.getSource() == back) viewHandler.openView("Menu");
//    edit selected event
        if (e.getSource() == edit && !(row == null)) {
            viewHandler.getEditEventController().editEvent(row);
            viewHandler.openView("EditEvent");
        }
        // delete selected event and updates the list
                if (e.getSource() == delete && !(row == null)) {
                    try {
                        System.out.println(row);
                        list.removeEvent(row);
                        MyFileHandler.writeToBinaryFile("events.bin", list);
                        update();
                        output.setText("Event deleted successfully");

                    } catch (FileNotFoundException ex) {
                        System.out.println("Error opening file ");
                    } catch (IOException ex) {
                        System.out.println("IO Error writing to file ");
                    }
                }
                if (rName.isSelected() || rLocation.isSelected() || rAllEvents.isSelected()) {
                    date.setVisible(false);
                    time.setVisible(false);
                    fSearch.setVisible(true);
                }
                // search by name
                if (e.getSource() == search && rName.isSelected()) {
                    EventList tempList = list.getEventsByName(
                            fSearch.getText().toLowerCase());
                    updateList(tempList);
                }
                if (e.getSource() == search && rLocation.isSelected()) {
                    EventList tempList = list.getEventsByLocation(
                            fSearch.getText().toLowerCase());
                    updateList(tempList);
                }
                if (rAllEvents.isSelected()) {
                    EventList tempList = list;
                    updateList(tempList);
                }
                if (rTime.isSelected()) {
                    fSearch.setVisible(false);
                    time.setVisible(true);
                    date.setVisible(true);
                }

                if (e.getSource() == search && rTime.isSelected()) {
                    String b = Integer.toString(date.getValue().getDayOfMonth()) + "/"
                            + Integer.toString(date.getValue().getMonthValue()) + "/" +
                            Integer.toString(date.getValue().getYear());
                    EventList tempList = list.getEventsByTime(MyDate.stringToDate(b, time.getText()));
                    updateList(tempList);
                }
            }


        public void tableAction(MouseEvent event){
          Event row = events.getSelectionModel().getSelectedItem();
          if (event.getClickCount() == 2 && !(row == null)) {
              viewHandler.getEditEventController().editEvent(row);
              viewHandler.openView("EditEvent");
          }

      }}