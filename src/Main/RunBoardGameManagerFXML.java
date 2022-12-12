package Main;

import GUI.StartGUI;
import javafx.application.Application;

/**
 * This class starts the GUI
 * @author Bence Kabaly
 */
public class RunBoardGameManagerFXML
{
  /**
   *  The main method that starts when this is started
   * @param args We can give arguments when the program starts
   */
  public static void main(String[] args)
  {
    Application.launch(StartGUI.class);
    Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
      /**
       *  Method to save changes to xml on program exit
       */
      public void run() {
        LoadXML.main(new String[]{"123"});
      }
    }, "Shutdown-thread"));
  }
}
