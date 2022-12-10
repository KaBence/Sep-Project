package GUI;

import Model.BoardGameManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.io.IOException;

/**
 * A class that handles what scene is the program using.
 * @author Bence Kabaly
 * @version 1.0
 */

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

  /**
   * Constructor with 2 arguments initializing the viewHandler
   * @param stage sets the stage
   * @param boardGameManager set the boardGameManager
   */
  public ViewHandler(Stage stage, BoardGameManager boardGameManager)
  {
    this.stage = stage;
    this.boardGameManager = boardGameManager;
  }

  /**
   * Start void that loads all the fxml files
   */
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

  /**
   * This method changes the scenes for the stage
   * @param id sets which scene should the viewHandle load
   */
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
        addEventController.update();
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
        editEventController.update();
    }

    stage.setResizable(false);
    stage.show();
  }

  /**
   * Returns the borrowGameController
   * @return borrowGameController
   */
  public BorrowGameController getBorrowGameController()
  {
    return borrowGameController;
  }

  /**
   * Returns the showMemberController
   * @return showMemberController
   */
  public showMemberController getShowMemberController(){
    return showMemberController;
}

  /**
   * Returns the showBoardGameController
   * @return showBoardGameController
   */
  public ShowBoardGameController getShowBoardGameController(){
    return showBoardGameController;
  }

  /**
   * Returns the ReturnGameController
   * @return ReturnGameController
   */
  public ReturnGameController getReturnGameController(){
    return returnGameController;
  }

  /**
   * returns the ReturnGameController
   * @return ReturnGameController
   */
  public EditEventController getEditEventController(){
    return editEventController;
  }

  /**
   * Returns the addMemberController
   * @return addMemberController
   */
  public AddMemberController getAddMemberController(){
    return addMemberController;

  }

  /**
   * returns the addBoardGameController
   * @return addBoardGameController
   */
  public AddBoardGameController getAddBoardGameController() {
    return addBoardGameController;
  }

  /**
   * Returns the MakeReservationController
   * @return MakeReservationController
   */
  public MakeReservationController getMakeReservationController() {
    return makeReservationController;
  }

  /**
   * Returns the ManageBoardGamesController
   * @return ManageBoardGamesController
   */
  public ManageBoardGamesController getManageBoardGamesController() {
    return manageBoardGamesController;
  }

  /**
   * Returns the SeeReviewController
   * @return SeeReviewController
   */

  public SeeReviewController getSeeReviewController()
  {
    return seeReviewController;
  }

  /**
   * Returns the VoteController
   * @return VoteController
   */
  public VoteController getVoteController()
  {
    return voteController;
  }

  /**
   * method for getting the AddEventController
   * @return addEventController
   */
  public AddEventController getAddEventController()
  {
    return addEventController;
  }

  /**
   * Returns the MenuController
   * @return MenuController
   */

  public MenuController getMenuController(){
    return menuController;
  }

  /**
   * Returns the ManageMemberController
   * @return ManageMemberController
   */
  public ManageMemberController getManageMemberController()
  {
    return manageMemberController;
  }

  /**
   * Method to load the menu
   */

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

  /**
   * Method to load the AddBoardGame
   */
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

  /**
   * Method to load the ManageBoardGame
   */
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

  /**
   * Method to load the AddMember
   */
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

  /**
   * Method to load the ManageMember
   */
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

  /**
   * Method to load the AddEvent
   */
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

  /**
   * Method to load the ManageEvent
   */
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

  /**
   * Method to load the BorrowGame
   */
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

  /**
   * Method to load the ReturnGame
   */
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

  /**
   * Method to load the Vote
   */
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

  /**
   * Method to load the MakeReservation
   */
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

  /**
   * Method to load the ShowBoardGame
   */
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

  /**
   * Method to load the SeeReview
   */
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

  /**
   * Method to load the EditEvent
   */
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

  /**
   * Method to load the ShowMember
   */
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
