package GUI;

import Model.BoardGameClub;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;

public class ManageBoardGamesController
{

  private ViewHandler viewHandler;
  private BoardGameClub clubManager;
  private Scene scene;

  @FXML Button back,temporary;

  public void init(ViewHandler viewHandler, Scene scene, BoardGameClub clubManager)
  {
    this.viewHandler = viewHandler;
    this.scene = scene;
    this.clubManager = clubManager;
  }

  public Scene getScene(){
    return scene;
  }

  public void actionHandler(ActionEvent e){
    if (e.getSource()==back) viewHandler.start();
    if (e.getSource()==temporary){
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ManageBoardGames2.fxml"));
        Region root = loader.load();
        init(viewHandler, new Scene(root), clubManager);
      }
      catch (IOException ed)
      {
        ed.printStackTrace();
      }
      Stage temp=new Stage();
      temp.setTitle("Test");
      temp.setScene(getScene());
      temp.show();
    }
  }
}
