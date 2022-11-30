package GUI;

import Model.BoardGameManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;

public class ManageBoardGamesController
{
  @FXML RadioButton name, type, numOfPlayers;
  @FXML RadioButton available, reserved, borrowed, allGames;
  @FXML TextField searchField;
  @FXML Button search;
  @FXML TableView games;
  @FXML Button back, temporary;
  @FXML TextField nameOfGame;
  @FXML TextField min;
  @FXML TextField max;
  @FXML TextField typeOfBoardGame;
  @FXML ComboBox owner;
  @FXML RadioButton available2, nonAvailable2, reserved2, borrowed2;
  @FXML Button edit;
  @FXML Button remove;
  @FXML Button seeReviews;
  @FXML Button reserve;
  @FXML Button borrow;
  @FXML Button save3;
  @FXML ComboBox borrower3;
  @FXML DatePicker pickupdate3;
  @FXML DatePicker returnDate3;
  @FXML TableView reservations3;
  @FXML Button home;
  @FXML Button edit3;
  @FXML Button remove3;
  @FXML TextField name4;
  @FXML TextField rank;
  @FXML ListView reviews;







  private ViewHandler viewHandler;
  private BoardGameManager clubManager;
  private Scene scene;

  public void init(ViewHandler viewHandler, Scene scene,
      BoardGameManager clubManager)
  {
    this.viewHandler = viewHandler;
    this.scene = scene;
    this.clubManager = clubManager;
  }

  public Scene getScene()
  {
    return scene;
  }

  public void actionHandler(ActionEvent e)
  {
    if (e.getSource() == back)
      viewHandler.start();
    if (e.getSource() == temporary)
    {
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
      Stage temp = new Stage();
      temp.setTitle("Test");
      temp.setScene(getScene());
      temp.show();
    }
  }
}
