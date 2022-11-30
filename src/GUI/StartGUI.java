package GUI;

import Model.BoardGameManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class StartGUI extends Application
{
  public void start(Stage window)
  {
    BoardGameManager ClubManager = new BoardGameManager("boardGameClub.bin");
    ViewHandler viewHandler = new ViewHandler(window, ClubManager);
    viewHandler.start();
  }
}
