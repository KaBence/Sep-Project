package GUI;

import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class MakeReservationController
{

  @FXML Button save;
  @FXML ComboBox borrower;
  @FXML DatePicker pickUpDate1;
  @FXML DatePicker returnDate1;
  @FXML TableView<Reservation> reservations;
  @FXML TableColumn<Reservation,String> borrowerTable;
  @FXML TableColumn<Reservation,String> pickUpDate;
  @FXML TableColumn<Reservation,String> returnDate;
  @FXML Button home;
  @FXML Button back;
  @FXML Button remove;

  private ViewHandler viewHandler;
  private BoardGameManager boardGameManager;
  private Scene scene;
  private BoardGame selectedGame;


  public void init(ViewHandler viewHandler, Scene scene, BoardGameManager boardGameManager)
  {
    this.viewHandler = viewHandler;
    this.scene = scene;
    this.boardGameManager = boardGameManager;
  }

  public Scene getScene(){
    return scene;
  }

  public void setSelectedGame(BoardGame selectedGame) {
    this.selectedGame = selectedGame;
  }

  public void initialize(){
    borrowerTable.setCellValueFactory(new PropertyValueFactory<Reservation, String>("borrower"));
    pickUpDate.setCellValueFactory(new PropertyValueFactory<Reservation, String>("pickUpDate"));
    returnDate.setCellValueFactory(new PropertyValueFactory<Reservation, String>("returnDate"));
  }

  public void update(){
    pickUpDate1.getEditor().clear();
    returnDate1.getEditor().clear();
    reservations.getItems().clear();
    if (selectedGame.isReserved()){
      for (int i = 0; i < selectedGame.getReservationList().size(); i++)
      {
        reservations.getItems().add(selectedGame.getReservationList().get(i));
      }
    }
    borrower.getItems().clear();
    MemberList members=boardGameManager.getAllMembers();
    for (int i = 0; i < members.size(); i++)
    {
      borrower.getItems().add(members.get(i));
    }
  }

  public void actionHandler(ActionEvent e){
    if (e.getSource()==back) viewHandler.openView("manageBoardGame");
    if (e.getSource()==home) viewHandler.openView("Menu");
    if (e.getSource()==save){
      if (!selectedGame.isReserved())selectedGame.createReservationList();
      MyDate pd=new MyDate(pickUpDate1.getValue().getDayOfMonth(),pickUpDate1.getValue().getMonthValue(),pickUpDate1.getValue().getYear());
      MyDate rd=new MyDate(returnDate1.getValue().getDayOfMonth(),returnDate1.getValue().getMonthValue(),returnDate1.getValue().getYear());
      if (rd.isBefore(pd)){
        Alert alert=new Alert(Alert.AlertType.ERROR,"PickupDate cannot be after ReturnDate",ButtonType.OK);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.showAndWait();
        return;
      }
      if (rd.isBefore(MyDate.today())||pd.isBefore(MyDate.today())){
        Alert alert=new Alert(Alert.AlertType.ERROR,"PickupDate or ReturnDate cannot be before today",ButtonType.OK);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.showAndWait();
        return;
      }
      if (pd.equals(MyDate.today())){
        Alert alert=new Alert(Alert.AlertType.ERROR,"If the pickup date is today, make a borrow instead",ButtonType.OK);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.showAndWait();
        return;
      }
      for (int i = 0; i < selectedGame.getReservationList().size(); i++)
      {
        //the dates are outside of a reservation
        if (pd.isBefore(selectedGame.getReservationList().get(i).getPickUpDate())&&selectedGame.getReservationList().get(i).getReturnDate().isBefore(rd)){
          Alert alert=new Alert(Alert.AlertType.ERROR,"1There is a collision in reservations, please select another date",ButtonType.OK);
          alert.setTitle("Warning");
          alert.setHeaderText(null);
          alert.showAndWait();
          return;
        }
        //the pickup date and return date is the same with a reservation
        if (pd.equals(selectedGame.getReservationList().get(i).getPickUpDate())&&rd.equals(selectedGame.getReservationList().get(i).getReturnDate())){
          Alert alert=new Alert(Alert.AlertType.ERROR,"Reservation for these days already exists",ButtonType.OK);
          alert.setTitle("Warning");
          alert.setHeaderText(null);
          alert.showAndWait();
          return;
        }
        //pickup date in between and return date is after
        if (selectedGame.getReservationList().get(i).getPickUpDate().isBefore(pd)&&selectedGame.getReservationList().get(i).getReturnDate().isBefore(rd)&&pd.isBefore(selectedGame.getReservationList().get(i).getReturnDate())){
          Alert alert=new Alert(Alert.AlertType.ERROR,"2There is a collision in reservations, please select another date",ButtonType.OK);
          alert.setTitle("Warning");
          alert.setHeaderText(null);
          alert.showAndWait();
          return;
        }
        //Both are in between
        if (selectedGame.getReservationList().get(i).getPickUpDate().isBefore(pd)&& rd.isBefore(selectedGame.getReservationList().get(i).getReturnDate())){
          Alert alert=new Alert(Alert.AlertType.ERROR,"3There is a collision in reservations, please select another date",ButtonType.OK);
          alert.setTitle("Warning");
          alert.setHeaderText(null);
          alert.showAndWait();
          return;
        }
        //pickupdate is before a reservation and return date is in between
        if (pd.isBefore(selectedGame.getReservationList().get(i).getPickUpDate())&& rd.isBefore(selectedGame.getReservationList().get(i).getReturnDate())&& !rd.isBefore(selectedGame.getReservationList().get(i).getPickUpDate())){
          Alert alert=new Alert(Alert.AlertType.ERROR,"4There is a collision in reservations, please select another date",ButtonType.OK);
          alert.setTitle("Warning");
          alert.setHeaderText(null);
          alert.showAndWait();
          return;
        }
      }
      if (borrower.getValue()==null){
        Alert alert=new Alert(Alert.AlertType.ERROR,"Select a Borrower first",ButtonType.OK);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.showAndWait();
        return;
      }
      MemberList members=boardGameManager.getAllMembers();
      selectedGame.getReservationList().addReservation(new Reservation(members.getMemberByName(borrower.getValue().toString()),pd,rd));
      BoardGameList boardgames=boardGameManager.getAllBoardGames();
      for (int i = 0; i < boardgames.size(); i++)
      {
        if (boardgames.get(i).equals(selectedGame))boardgames.removeBoardGame(boardgames.get(i));
      }
      boardgames.addBoardGame(selectedGame);
      boardGameManager.saveAllBoardGames(boardgames);
      Alert alert=new Alert(Alert.AlertType.INFORMATION,"Reservation was created successfully",ButtonType.OK);
      alert.setHeaderText(null);
      alert.setTitle("Good Job");
      alert.showAndWait();
      update();
    }
    if (e.getSource()==remove){
      Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Do you really want to delete this reservation?",ButtonType.YES,ButtonType.NO);
      alert.setTitle("Confirmation");
      alert.setHeaderText(null);
      alert.showAndWait();
      if (alert.getResult()==ButtonType.YES){
        Reservation toBeDeleted=reservations.getSelectionModel().getSelectedItem();
        for (int i = 0; i < selectedGame.getReservationList().size(); i++)
        {
          if (selectedGame.getReservationList().get(i).equals(toBeDeleted)){
            selectedGame.getReservationList().removeReservation(selectedGame.getReservationList().get(i));
            break;
          }
        }
        BoardGameList boardgames=boardGameManager.getAllBoardGames();
        for (int i = 0; i < boardgames.size(); i++)
        {
          if (boardgames.get(i).equals(selectedGame))boardgames.removeBoardGame(boardgames.get(i));
        }
        if (selectedGame.getReservationList().size()==0){
          selectedGame.setReservationList(null);
        }
        boardgames.addBoardGame(selectedGame);
        boardGameManager.saveAllBoardGames(boardgames);
        Alert alert1=new Alert(Alert.AlertType.INFORMATION,"Reservation was removed successfully",ButtonType.OK);
        alert1.setHeaderText(null);
        alert1.setTitle("Good Job");
        alert1.showAndWait();
        update();
      }


    }
  }
}
