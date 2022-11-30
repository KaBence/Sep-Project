package GUI;

import Model.BoardGameClub;
import javafx.application.Application;
import javafx.stage.Stage;

public class StartGUI extends Application
{
  public void start(Stage window)
  {
    BoardGameClub ClubManager = new BoardGameClub("boardGameClub.bin");
    ViewHandler viewHandler = new ViewHandler(window, ClubManager);
    viewHandler.start();
  }
}
