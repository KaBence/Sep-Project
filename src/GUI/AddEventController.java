package GUI;

import Model.BoardGameManager;
import Model.Event;
import Model.EventList;
import Model.MyDate;
import Util.MyFileHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.FileNotFoundException;
import java.io.IOException;

public class AddEventController
{
  @FXML Button back;
  @FXML Button addEvent;
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

  public void init(ViewHandler viewHandler, Scene scene, BoardGameManager boardGameManager)
  {
    this.viewHandler = viewHandler;
    this.scene = scene;
    this.boardGameManager = boardGameManager;
  }

  public Scene getScene(){
    return scene;
  }

  public void actionHandler(ActionEvent e)
  {
    if (e.getSource()==back) viewHandler.openView("Menu");
    if (e.getSource()==addEvent) {
      try {
        int a = Integer.parseInt(maxCapacity.getText());
        EventList list = boardGameManager.getAllEvents();
        Event event = new Event(new MyDate(date.getValue().getDayOfMonth(),date.getValue().getMonthValue(),date.getValue().getYear()),
                fLocation.getText(),name.getText(), guests.getText(),a);
        list.addEvent(event);
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
  };
}

