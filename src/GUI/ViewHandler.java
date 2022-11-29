package GUI;

import Model.BoardGameClub;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;

public class ViewHandler
{
  private BoardGameClub ClubManager;
  private MenuController menuController;
  private AddBoardGameController addBoardGameController;
  private AddEventController addEventController;
  private AddMemberController addMemberController;
  private ManageBoardGamesController manageBoardGamesController;
  private ManageEventsController manageEventsController;
  private ManageMemberController manageMemberController;
  private MakeReservationController makeReservationController;
  private VoteController voteController;
  private BorrowGameController borrowGameController;
  private ReturnGameController returnGameController;

  private Stage stage;
  public ViewHandler(Stage stage, BoardGameClub ClubManager)
  {
    this.stage = stage;
    this.ClubManager = ClubManager;
  }

  public void start(){
    loadViewMenu();
    loadViewAddBoardGame();
    loadViewManageBoardGame();
    loadViewAddEvent();
    loadViewManageEvent();
    loadViewAddMember();
    loadViewManageMember();
    loadViewVote();
    loadViewMakeReservation();
    loadViewReturnGame();
    loadViewBorrowGame();
    loadViewBorrowGame2();
    loadViewTemp();
    openView("Menu");
  }

  public void openView(String id){
    switch (id){
      case "Menu":
        stage.setScene(menuController.getScene());
        break;
      case "addBoardGame":
        stage.setScene(addBoardGameController.getScene());
        break;
      case "manageBoardGame":
        stage.setScene(manageBoardGamesController.getScene());
        break;
      case "addMember":
        stage.setScene(addMemberController.getScene());
        break;
      case "manageMember":
        stage.setScene(manageMemberController.getScene());
        break;
      case "addEvent":
        stage.setScene(addEventController.getScene());
        break;
      case "manageEvent":
        stage.setScene(manageEventsController.getScene());
        break;
      case "borrow":
        stage.setScene(borrowGameController.getScene());
        break;
      case "return":
        stage.setScene(returnGameController.getScene());
        break;
      case "reservation":
        stage.setScene(makeReservationController.getScene());
        break;
      case "vote":
        stage.setScene(voteController.getScene());
        break;
    }
    stage.setTitle("Test");
    stage.show();
  }

  private void loadViewTemp() {
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("ManageBoardGames2.fxml"));
      Region root = loader.load();
      manageBoardGamesController = loader.getController();
      manageBoardGamesController.init(this, new Scene(root), ClubManager);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  private void loadViewMenu() {
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
  private void loadViewAddBoardGame() {
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("AddBoardGame.fxml"));
      Region root = loader.load();
      addBoardGameController = loader.getController();
      addBoardGameController.init(this, new Scene(root), ClubManager);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  private void loadViewManageBoardGame() {
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("ManageBoardGames.fxml"));
      Region root = loader.load();
      manageBoardGamesController = loader.getController();
      manageBoardGamesController.init(this, new Scene(root), ClubManager);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  private void loadViewAddMember() {
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("AddMember.fxml"));
      Region root = loader.load();
      addMemberController = loader.getController();
      addMemberController.init(this, new Scene(root), ClubManager);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  private void loadViewManageMember() {
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("ManageMember.fxml"));
      Region root = loader.load();
      manageMemberController = loader.getController();
      manageMemberController.init(this, new Scene(root), ClubManager);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  private void loadViewAddEvent() {
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("AddEvent.fxml"));
      Region root = loader.load();
      addEventController = loader.getController();
      addEventController.init(this, new Scene(root), ClubManager);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  private void loadViewManageEvent() {
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("ManageEvents.fxml"));
      Region root = loader.load();
      manageEventsController = loader.getController();
      manageEventsController.init(this, new Scene(root), ClubManager);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  private void loadViewBorrowGame() {
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("BorrowGame.fxml"));
      Region root = loader.load();
      borrowGameController = loader.getController();
      borrowGameController.init(this, new Scene(root), ClubManager);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  private void loadViewBorrowGame2() {
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("BorrowGame2.fxml"));
      Region root = loader.load();
      borrowGameController = loader.getController();
      borrowGameController.init(this, new Scene(root), ClubManager);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  private void loadViewReturnGame() {
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("ReturnGame.fxml"));
      Region root = loader.load();
      returnGameController = loader.getController();
      returnGameController.init(this, new Scene(root), ClubManager);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  private void loadViewVote() {
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("Vote.fxml"));
      Region root = loader.load();
      voteController = loader.getController();
      voteController.init(this, new Scene(root), ClubManager);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  private void loadViewMakeReservation() {
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("MakeReservation.fxml"));
      Region root = loader.load();
      makeReservationController = loader.getController();
      makeReservationController.init(this, new Scene(root), ClubManager);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

}
