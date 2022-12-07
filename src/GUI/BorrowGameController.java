package GUI;

import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class BorrowGameController
{

  @FXML TableView reservations;
  @FXML TableColumn borrower1;
  @FXML TableColumn pickUpDate1;
  @FXML TableColumn returnDate1;
  @FXML DatePicker pickUpDate;
  @FXML DatePicker returnDate;
  @FXML ComboBox<Member> borrower;
  @FXML Button back, home, save;

  private ViewHandler viewHandler;
  private BoardGameManager boardGameManager;
  private Scene scene;
  private BoardGame selectedBoardGame;

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

  public void initialize(){
    borrower1.setCellValueFactory(new PropertyValueFactory<Reservation, String>("borrower"));
    pickUpDate1.setCellValueFactory(new PropertyValueFactory<Reservation, String>("pickUpDate"));
    returnDate1.setCellValueFactory(new PropertyValueFactory<Reservation, String>("returnDate"));
  }

  public void update()
  {
    returnDate.getEditor().clear();
    reservations.getItems().clear();
    if (selectedBoardGame.isReserved()){
      for (int i = 0; i < selectedBoardGame.getReservationList().size(); i++)
      {
        reservations.getItems().add(selectedBoardGame.getReservationList().get(i));
      }
    }
    clean();
    updateComboBox();
  }

  public void clean()
  {
    borrower.getItems().clear();
  }

  public void updateComboBox()
  {
    borrower.getItems().clear();
    borrower.getItems().add(null);
    MemberList members = boardGameManager.getAllMembers();
    for (int i = 0; i < members.size(); i++)
    {
      borrower.getItems().add(members.get(i));
    }
  }

  public void setPickUpDate()
  {
    pickUpDate.setValue(LocalDate.now());
  }

  public void setSelectedBoardGame(BoardGame selectedBoardGame)
  {
    this.selectedBoardGame = selectedBoardGame;
  }

  public void actionHandler(ActionEvent e)
  {
    if (e.getSource() == home)
      viewHandler.openView("Menu");
    if (e.getSource() == back)
      viewHandler.openView("manageBoardGame");
    if (e.getSource() == save)
    {
      if (!selectedBoardGame.isBorrowed())
      {
        selectedBoardGame.createBorrowList();
        MyDate pd = new MyDate(pickUpDate.getValue().getDayOfMonth(),
            pickUpDate.getValue().getMonthValue(),
            pickUpDate.getValue().getDayOfYear());
        MyDate rd =new MyDate(returnDate.getValue().getDayOfMonth(),
            returnDate.getValue().getMonthValue(),
            returnDate.getValue().getYear());
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
         Member borrowerT =members.getMemberByName(borrower.getValue().toString());
        selectedBoardGame.getBorrow().setBorrower(borrowerT);
        selectedBoardGame.getBorrow().setPickUpDate(pd);
        selectedBoardGame.getBorrow().setReturnDate(rd);
        BoardGameList boardgames = boardGameManager.getAllBoardGames();
        for (int i = 0; i < boardgames.size(); i++)
        {
          if (boardgames.get(i).equals(selectedBoardGame))
            boardgames.removeBoardGame(boardgames.get(i));
        }
        boardgames.addBoardGame(selectedBoardGame);
        boardGameManager.saveAllBoardGames(boardgames);
        Alert alert = new Alert(Alert.AlertType.INFORMATION,
            "Borrowing was created successfully", ButtonType.OK);
        alert.setHeaderText(null);
        alert.setTitle("Good Job");
        alert.showAndWait();
        update();
      }
      }
    }
  }
