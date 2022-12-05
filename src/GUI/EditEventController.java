package GUI;

import Model.*;
import Util.MyFileHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import java.time.LocalDate;

import java.io.FileNotFoundException;
import java.io.IOException;

public class EditEventController {
    @FXML Button back;
    @FXML Button save;
    @FXML TextField name;
    @FXML TextField fLocation;
    @FXML TextField maxCapacity;
    @FXML TextField guests;
    @FXML ComboBox chooseGame;
    @FXML DatePicker date;
    @FXML Label output;
    private ViewHandler viewHandler;
    private BoardGameManager boardGameManager;
    private Scene scene;
    private int eventIndex = 0;

    public void init(ViewHandler viewHandler, Scene scene, BoardGameManager boardGameManager) {
        this.viewHandler = viewHandler;
        this.scene = scene;
        this.boardGameManager = boardGameManager;
    }
    public void update(){
        ;
    }
    public Scene getScene() {
        return scene;
    }
    public void actionHandler(ActionEvent e) {
    if(e.getSource()== back)viewHandler.openView("manageEvent");
    if (e.getSource()==save){
        try {
                int a = Integer.parseInt(maxCapacity.getText());
                EventList list = boardGameManager.getAllEvents();
                Event event = new Event(new MyDate(date.getValue().getDayOfMonth(),date.getValue().getMonthValue(),date.getValue().getYear()),
                        fLocation.getText(),name.getText(), guests.getText(),a);
                list.setEvent(event, eventIndex);
                MyFileHandler.writeToBinaryFile("events.bin", list );
                output.setText(event.toString());
                System.out.println("Events done");
            }
            catch (FileNotFoundException ex)
            {
                System.out.println("Error opening file ");
            }
            catch (IOException ex)
            {
                System.out.println("IO Error writing to file ");
            }
        }
    }
    public void editEvent(Event event){
        output.setText("");
        EventList list = boardGameManager.getAllEvents();
    name.setText(event.getName());
    fLocation.setText(event.getLocation());
    maxCapacity.setText(Integer.toString(event.getCapacity()));
    guests.setText(event.getGuests());
    eventIndex = list.getIndexOf(event);
    date.setValue(LocalDate.of(event.getDate().getYear(),event.getDate().getMonth(),event.getDate().getDay()));
//    chooseGame.setText?s
    }
}