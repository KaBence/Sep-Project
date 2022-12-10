package GUI;

import Model.*;
import Util.MyFileHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.util.Optional;
import java.io.FileNotFoundException;
import java.io.IOException;
/**
 * A class for managing the list of events
 * @author Igor Cretu
 */
public class ManageEventsController {

    private ViewHandler viewHandler;
    private BoardGameManager boardGameManager;
    private Scene scene;
    private EventList eventList;

    @FXML
    Button back;
    @FXML
    ToggleGroup toggle;
    @FXML
    Button edit;
    @FXML
    Button delete;
    @FXML
    Button search;
    @FXML
    RadioButton rName;
    @FXML
    RadioButton rLocation;
    @FXML
    RadioButton rTime;
    @FXML
    RadioButton rAllEvents;
    @FXML
    TextField fSearch;
    @FXML
    TextField time;
    @FXML
    DatePicker date;
    @FXML
    TableView<Event> events;
    @FXML
    TableColumn<Event, String> tableName;
    @FXML
    TableColumn<Event, String> tableLocation;
    @FXML
    TableColumn<Event, String> tableCapacity;
    @FXML
    TableColumn<Event, String> tableDate;
    /**
     * A method for initializing the tables
     */
    public void initialize() {
        tableName.setCellValueFactory(
                new PropertyValueFactory<Event, String>("eventName"));
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
    /**
     * A method for setting the parameters
     * @param viewHandler sets the viewHandler
     * @param scene sets The scene
     * @param boardGameManager sets the BoardGameManager
     */
    public void init(ViewHandler viewHandler, Scene scene,
                     BoardGameManager boardGameManager) {
        this.viewHandler = viewHandler;
        this.scene = scene;
        this.boardGameManager = boardGameManager;
    }
    /**
     * A method for updating the window
     */
    public void update() {
        updateList(boardGameManager.getAllEvents());
        date.setValue(null);
        time.clear();
    }
    /**
     * A method for updating the table with available events
     */
    public void updateList(EventList list) {
        events.getItems().clear();
        eventList = list;
        for (int i = 0; i < eventList.size(); i++) {
            events.getItems().add(eventList.get(i));
        }
    }
    /**
     * Returns the manageEvent scene
     * @return manageEvent scene
     */
    public Scene getScene() {
        return scene;
    }
    /**
     * A method for handling the button clicking
     * @param e the event that is called when something happens
     */
    public void actionHandler(ActionEvent e) {
        Event row = events.getSelectionModel().getSelectedItem();
        EventList list = boardGameManager.getAllEvents();
        // back to the menu
        if (e.getSource() == back) viewHandler.openView("Menu");
//    edit selected event
        if (e.getSource() == edit && !(row == null)) {
            viewHandler.getEditEventController().editEvent(row);
            System.out.println(list.getIndexOf(row));
            viewHandler.openView("EditEvent");
        }
        // delete selected event and updates the list
        if (e.getSource() == delete && !(row == null)) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Are you  sure you want to delete this event?");
            alert.setContentText(row.toString());
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                try {
                    System.out.println(row);
                    list.removeEvent(row);
                    MyFileHandler.writeToBinaryFile("events.bin", list);
                    System.out.println(list);
                    update();
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Event list updated");
                    alert1.setHeaderText("Event deleted successfully!");
                    alert1.setContentText(null);
                    alert1.showAndWait();
                } catch (FileNotFoundException ex) {
                    System.out.println("Error opening file ");
                } catch (IOException ex) {
                    System.out.println("IO Error writing to file ");
                }
            } else {
                // ... user chose CANCEL or closed the dialog
            }

        }
        if (rName.isSelected() || rLocation.isSelected() || rAllEvents.isSelected()) {
            date.setValue(null);
            time.clear();
            date.setVisible(false);
            time.setVisible(false);
            fSearch.setVisible(true);
        }
        // search by name
        if (e.getSource() == search && rName.isSelected()) {

            if (!fSearch.getText().isBlank()) {
                EventList tempList = list.getEventsByName(
                        fSearch.getText().toLowerCase());
                updateList(tempList);

            } else {
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setTitle("Error");
                alert1.setHeaderText("The search bar has to be filled in order to search");
                alert1.setContentText(null);
                alert1.showAndWait();
            }
        }
        if (e.getSource() == search && rLocation.isSelected()) {
            if (!fSearch.getText().isBlank()) {
                EventList tempList = list.getEventsByLocation(fSearch.getText().toLowerCase());
                updateList(tempList);

            } else {
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setTitle("Error");
                alert1.setHeaderText("The search bar has to be filled in order to search");
                alert1.setContentText(null);
                alert1.showAndWait();
            }
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
            if (!(date.getValue() == null)) {
                if (!time.getText().isEmpty()) {
                    EventList tempList;
                    if (MyDate.timeFormat(time.getText())){
                        String b = Integer.toString(date.getValue().getDayOfMonth()) + "/"
                                + Integer.toString(date.getValue().getMonthValue()) + "/" +
                                Integer.toString(date.getValue().getYear());
                        MyDate s = MyDate.stringToDate(b, time.getText());
                        if(s.getHour() <00 || s.getHour() > 24 || s.getMin() < 0 || s.getMin() > 59){
                            Alert alert1 = new Alert(Alert.AlertType.ERROR);
                            alert1.setTitle("Error");
                            alert1.setHeaderText("Hours can't be bigger than 24 or smaller than 00\nMinutes can't be bigger than 59 or smaller than 00");
                            alert1.setContentText("Correct time format is hour:minutes");
                            alert1.showAndWait();
                            time.clear();
                        } else {

                            tempList = list.getEventsByTime(MyDate.stringToDate(b, time.getText()));
                            updateList(tempList);
                        }

                    }else {
                        Alert alert1 = new Alert(Alert.AlertType.ERROR);
                        System.out.println(time.getText());
                        alert1.setTitle("Error");
                        alert1.setHeaderText("Invalid time format");
                        alert1.setContentText("Correct time format is hour:minutes");
                        alert1.showAndWait();
                    }
                } else {
                    EventList tempList;
                    tempList = list.getEventsByDate(new MyDate(date.getValue().getDayOfMonth(),
                            date.getValue().getMonthValue(),
                            date.getValue().getYear()));
                    updateList(tempList);
                }

            } else {
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setTitle("Error");
                alert1.setHeaderText("There is has to be at least a date in order to search by it");
                alert1.setContentText(null);
                alert1.showAndWait();
            }

        }
    }
    /**
     * A method for handling the mouse clicking
     * @param event the event that is called when something happens
     */
    public void tableAction(MouseEvent event) {
        Event row = events.getSelectionModel().getSelectedItem();
        if (event.getClickCount() == 2 && !(row == null)) {
            viewHandler.getEditEventController().editEvent(row);
            viewHandler.openView("EditEvent");
        }

    }
}