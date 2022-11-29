package GUI;

import Model.BoardGameClub;
import javafx.stage.Stage;

public class ViewHandler
{
  private BoardGameClub ClubManager;
  private Stage stage;
  public ViewHandler(Stage stage, BoardGameClub ClubManager)
  {
    this.stage = stage;
    this.ClubManager = ClubManager;
  }

  public void start(){

  }
}
