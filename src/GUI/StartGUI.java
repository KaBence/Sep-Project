package GUI;

import Model.BoardGameManager;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * class for opening the GUI window
 */
public class StartGUI extends Application
{
  public void start(Stage window)
  {
    BoardGameManager boardGameManager = new BoardGameManager("Boardgames.bin","members.bin","events.bin");
    ViewHandler viewHandler = new ViewHandler(window, boardGameManager);
    viewHandler.start();
  }
}
