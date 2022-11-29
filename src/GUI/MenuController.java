package GUI;

import Model.BoardGameClub;
import javafx.scene.Scene;

public class MenuController
{
  private ViewHandler viewHandler;
  private BoardGameClub clubmanager;
  private Scene scene;
  public void init(ViewHandler viewHandler, Scene scene, BoardGameClub clubManager)
  {
    this.viewHandler = viewHandler;
    this.scene = scene;
    this.clubmanager = clubmanager;
  }

  public Scene getScene(){
    return scene;
  }
}
