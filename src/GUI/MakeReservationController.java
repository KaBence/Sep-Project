package GUI;

import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class MakeReservationController
{

  @FXML Button save;
  @FXML ComboBox borrower;
  @FXML DatePicker pickUpDate1;
  @FXML DatePicker returnDate1;
  @FXML TableView reservations;
  @FXML TableColumn<Reservation, String> borrowerTable;
  @FXML TableColumn<Reservation, String> pickUpDate;
  @FXML TableColumn<Reservation, String> returnDate;
  @FXML Button home;
  @FXML Button back;
  @FXML Button remove;

  private ViewHandler viewHandler;
  private BoardGameManager boardGameManager;
  private Scene scene;
  private BoardGame selectedGame;

  public void init(ViewHandler viewHandler, Scene scene,
      BoardGameManager boardGameManager)
  {
    this.viewHandler = viewHandler;
    this.scene = scene;
    this.boardGameManager = boardGameManager;
  }

  public Scene getScene()
  {
    return scene;
  }

  public void setSelectedGame(BoardGame selectedGame)
  {
    this.selectedGame = selectedGame;
  }

  public void initialize()
  {
    borrowerTable.setCellValueFactory(
        new PropertyValueFactory<Reservation, String>("borrower"));
    pickUpDate.setCellValueFactory(
        new PropertyValueFactory<Reservation, String>("pickUpDate"));
    returnDate.setCellValueFactory(
        new PropertyValueFactory<Reservation, String>("returnDate"));
  }

  public void update()
  {
    reservations.getItems().clear();
    if (selectedGame.isReserved())
    {
      for (int i = 0; i < selectedGame.getReservationList().size(); i++)
      {
        reservations.getItems().add(selectedGame.getReservationList().get(i));
      }
    }
    borrower.getItems().clear();
    MemberList members = boardGameManager.getAllMembers();
    for (int i = 0; i < members.size(); i++)
    {
      borrower.getItems().add(members.get(i));
    }
  }

  public void actionHandler(ActionEvent e)
  {
    if (e.getSource() == back)
      viewHandler.openView("manageBoardGame");
    if (e.getSource() == home)
      viewHandler.openView("Menu");
    if (e.getSource() == save)
    {
      if (!selectedGame.isReserved())
        selectedGame.createReservationList();
      MyDate pd = new MyDate(pickUpDate1.getValue().getDayOfMonth(),
          pickUpDate1.getValue().getMonthValue(),
          pickUpDate1.getValue().getYear());
      MyDate rd = new MyDate(returnDate1.getValue().getDayOfMonth(),
          returnDate1.getValue().getMonthValue(),
          returnDate1.getValue().getYear());
      if (rd.isBefore(pd))
      {
        Alert alert = new Alert(Alert.AlertType.ERROR,
            "PickupDate cannot be after ReturnDate", ButtonType.OK);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.showAndWait();
        return;
      }
      MemberList members = boardGameManager.getAllMembers();
      selectedGame.getReservationList().addReservation(new Reservation(
          members.getMemberByName(borrower.getValue().toString()), pd, rd));
      BoardGameList boardgames = boardGameManager.getAllBoardGames();
      for (int i = 0; i < boardgames.size(); i++)
      {
        if (boardgames.get(i).equals(selectedGame))
          boardgames.removeBoardGame(boardgames.get(i));
      }
      boardgames.addBoardGame(selectedGame);
      boardGameManager.saveAllBoardGames(boardgames);
      Alert alert = new Alert(Alert.AlertType.INFORMATION,
          "Reservation was created successfullu", ButtonType.OK);
      alert.setHeaderText(null);
      alert.setTitle("Good Job");
      alert.showAndWait();
      update();
    }
  }
}
