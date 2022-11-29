package GUI;

import Model.BoardGameClub;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler
{
  private BoardGameClub ClubManager;
  private MenuController menuController;
  private AddBoardGameController addBoardGameController;
  private Stage stage;
  public ViewHandler(Stage stage, BoardGameClub ClubManager)
  {
    this.stage = stage;
    this.ClubManager = ClubManager;
  }

  public void start(){
    loadViewMenu();
    openView("Menu");
  }

  public void openView(String id){
    switch (id){
      case "Menu":
        stage.setScene(menuController.getScene());
        break;
      case "addBoardGame":
        stage.setScene(addBoardGameController.getScene());
    }
    stage.setTitle("Test");
    stage.show();
  }

  private void loadViewMenu()
  {
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("Menu.fxml"));
      Region root = loader.load();
      menuController = loader.getController();
      menuController.init(this, new Scene(root), ClubManager);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
