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
    updateUpperPart();
  }

  public void clean()
  {
    borrower.getItems().clear();
  }

  public void updateUpperPart()
  {
    borrower.getItems().clear();
    borrower.getItems().add(null);
    MemberList members = boardGameManager.getAllMembers();
    for (int i = 0; i < members.size(); i++)
    {
      borrower.getItems().add(members.get(i));
    }
    for (int i = 0; i < selectedBoardGame.getReservationList().size(); i++)
    {
      if (selectedBoardGame.getReservationList().get(i).getPickUpDate().equals(MyDate.today())){
        returnDate.setValue(selectedBoardGame.getReservationList().get(i).getReturnDate()
            .convertToLocalDate());
        returnDate.getEditor().setText(selectedBoardGame.getReservationList().get(i).getReturnDate().toString());
        break;
      }
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
        for (int i = 0; i < selectedBoardGame.getReservationList().size(); i++)
        {
          //the dates are outside of a reservation
          if (pd.isBefore(selectedBoardGame.getReservationList().get(i).getPickUpDate())&&selectedBoardGame.getReservationList().get(i).getReturnDate().isBefore(rd)){
            Alert alert=new Alert(Alert.AlertType.ERROR,"1There is a collision in reservations, please select another date",ButtonType.OK);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.showAndWait();
            return;
          }
          //the pickup date and return date is the same with a reservation
          if (pd.equals(selectedBoardGame.getReservationList().get(i).getPickUpDate())&&rd.equals(selectedBoardGame.getReservationList().get(i).getReturnDate())){
            Alert alert=new Alert(Alert.AlertType.ERROR,"Reservation for these days already exists",ButtonType.OK);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.showAndWait();
            return;
          }
          //pickup date in between and return date is after
          if (selectedBoardGame.getReservationList().get(i).getPickUpDate().isBefore(pd)&&selectedBoardGame.getReservationList().get(i).getReturnDate().isBefore(rd)&&pd.isBefore(selectedBoardGame.getReservationList().get(i).getReturnDate())){
            Alert alert=new Alert(Alert.AlertType.ERROR,"2There is a collision in reservations, please select another date",ButtonType.OK);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.showAndWait();
            return;
          }
          //Both are in between
          if (selectedBoardGame.getReservationList().get(i).getPickUpDate().isBefore(pd)&& rd.isBefore(selectedBoardGame.getReservationList().get(i).getReturnDate())){
            Alert alert=new Alert(Alert.AlertType.ERROR,"3There is a collision in reservations, please select another date",ButtonType.OK);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.showAndWait();
            return;
          }
          //pickupdate is before a reservation and return date is in between
          if (pd.isBefore(selectedBoardGame.getReservationList().get(i).getPickUpDate())&& rd.isBefore(selectedBoardGame.getReservationList().get(i).getReturnDate())&& !rd.isBefore(selectedBoardGame.getReservationList().get(i).getPickUpDate())){
            Alert alert=new Alert(Alert.AlertType.ERROR,"4There is a collision in reservations, please select another date",ButtonType.OK);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.showAndWait();
            return;
          }
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
