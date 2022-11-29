package GUI;

import Model.BoardGameClub;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class StartGUI extends Application
{
  public void start(Stage window)
  {
    BoardGameClub ClubManager = new BoardGameClub("Test2/students.bin");
    ViewHandler viewHandler = new ViewHandler(window, ClubManager);
    viewHandler.start();
  }
}
