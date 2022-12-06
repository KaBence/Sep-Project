package GUI;

import Model.BoardGameManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.io.IOException;

public class ViewHandler
{
  private BoardGameManager boardGameManager;
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
  private ShowBoardGameController showBoardGameController;
  private SeeReviewController seeReviewController;
  private EditEventController editEventController;
  private showMemberController showMemberController;

  private Stage stage;
  public ViewHandler(Stage stage, BoardGameManager boardGameManager)
  {
    this.stage = stage;
    this.boardGameManager = boardGameManager;
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
    loadEditEvent();
    loadViewSeeReview();
    loadViewShowBoardGame();
    loadShowMember();
    openView("Menu");
  }

  public void openView(String id){
    stage.getIcons().add(new Image("logo3.png"));
    switch (id){
      case "Menu":
        stage.setScene(menuController.getScene());
        stage.setTitle("Menu");
        break;
      case "addBoardGame":
        stage.setScene(addBoardGameController.getScene());
        stage.setTitle("Add a board game");
        break;
      case "manageBoardGame":
        stage.setScene(manageBoardGamesController.getScene());
        stage.setTitle("Manage board games");
        manageBoardGamesController.update(boardGameManager.getAllBoardGames());
        break;
      case "showBoardGame":
        stage.setScene(showBoardGameController.getScene());
        stage.setTitle(showBoardGameController.getShowBoardGame().getName());
        showBoardGameController.update();
        break;
      case "addMember":
        stage.setScene(addMemberController.getScene());
        stage.setTitle("Add member");
        if (addMemberController.isCheck()){
          addMemberController.update();
        }
        break;
      case "manageMember":
        stage.setScene(manageMemberController.getScene());
        stage.setTitle("Manage members");
        manageMemberController.update();
        break;
      case "showMember":
        stage.setScene(showMemberController.getScene());
        stage.setTitle("Show member");
       showMemberController.update();
        break;
      case "addEvent":
        stage.setScene(addEventController.getScene());
        stage.setTitle("Add events");
        //addEventController.update();
        break;
      case "manageEvent":
        stage.setScene(manageEventsController.getScene());
        stage.setTitle("Manage events");
        manageEventsController.update();
        break;
      case "borrow":
        stage.setScene(borrowGameController.getScene());
        stage.setTitle("Borrow a game");
        borrowGameController.setPickUpDate();
        borrowGameController.update();
        break;
      case "returnGame":
        stage.setScene(returnGameController.getScene());
        stage.setTitle("Return a game");
        returnGameController.update();
        break;
      case "reservation":
        stage.setScene(makeReservationController.getScene());
        stage.setTitle("Make a reservation");
        makeReservationController.update();
        break;
      case "vote":
        stage.setScene(voteController.getScene());
        stage.setTitle("Vote for the next game");
        break;
      case "seeReviews":
        stage.setScene(seeReviewController.getScene());
        stage.setTitle("Reviews");
        break;
      case "EditEvent":
        stage.setScene(editEventController.getScene());
        stage.setTitle("Edit event");
    }

    stage.setResizable(false);
    stage.show();
  }

  public BorrowGameController getBorrowGameController()
  {
    return borrowGameController;
  }

  public showMemberController getShowMemberController(){
    return showMemberController;
}
  public ShowBoardGameController getShowBoardGameController(){
    return showBoardGameController;
  }
  public ReturnGameController getReturnGameController(){
    return returnGameController;
  }
  public EditEventController getEditEventController(){
    return editEventController;
  }
  public AddMemberController getAddMemberController(){
    return addMemberController;

  }
  public AddBoardGameController getAddBoardGameController() {
    return addBoardGameController;
  }

  public MakeReservationController getMakeReservationController() {
    return makeReservationController;
  }

  public SeeReviewController getSeeReviewController()
  {
    return seeReviewController;
  }

  public VoteController getVoteController()
  {
    return voteController;
  }

  public AddEventController getAddEventController()
  {
    return addEventController;
  }

  public MenuController getMenuController(){
    return menuController;
  }

  public ManageMemberController getManageMemberController()
  {
    return manageMemberController;
  }

  private void loadViewMenu() {
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("Menu.fxml"));
      Region root = loader.load();
      menuController = loader.getController();
      menuController.init(this, new Scene(root), boardGameManager);
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
      addBoardGameController.init(this, new Scene(root), boardGameManager);
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
      manageBoardGamesController.init(this, new Scene(root), boardGameManager);
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
      addMemberController.init(this, new Scene(root), boardGameManager);
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
      manageMemberController.init(this, new Scene(root), boardGameManager);
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
      addEventController.init(this, new Scene(root), boardGameManager);
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
      manageEventsController.init(this, new Scene(root), boardGameManager);
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
      borrowGameController.init(this, new Scene(root), boardGameManager);
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
      returnGameController.init(this, new Scene(root), boardGameManager);
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
      voteController.init(this, new Scene(root), boardGameManager);
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
      makeReservationController.init(this, new Scene(root), boardGameManager);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  private void loadViewShowBoardGame() {
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("ShowBoardGame.fxml"));
      Region root = loader.load();
      showBoardGameController= loader.getController();
      showBoardGameController.init(this, new Scene(root), boardGameManager);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  private void loadViewSeeReview(){
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("SeeReview.fxml"));
      Region root = loader.load();
      seeReviewController = loader.getController();
      seeReviewController.init(this, new Scene(root), boardGameManager);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  private void loadEditEvent(){
    try
    {
      FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("EditEvent.fxml"));
    Region root = loader.load();
    editEventController = loader.getController();
    editEventController.init(this, new Scene(root), boardGameManager);
  }
    catch (IOException e)
  {
    e.printStackTrace();
  }
  }
  private void loadShowMember(){
    try{
       FXMLLoader loader=new FXMLLoader();
       loader.setLocation(getClass().getResource("showMember.fxml"));
       Region root = loader.load();
      showMemberController = loader.getController();
      showMemberController.init(this,new Scene(root), boardGameManager);
    }
    catch (IOException e){
      e.printStackTrace();
    }
  }

}
