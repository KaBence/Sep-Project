package GUI;

import Model.BoardGameManager;
import javafx.event.ActionEvent;
import javafx.scene.Scene;

public class ShowBoardGameController
{

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

  public void actionHandler(ActionEvent e){

  }
}
