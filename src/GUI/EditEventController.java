package GUI;

import Model.*;
import Util.MyFileHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;

import java.time.LocalDate;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class EditEventController {
    @FXML
    Button back;
    Button addMember;
    @FXML
    Button addGuest;
    @FXML
    Button removeMember;
    @FXML
    Button removeGuest;
    @FXML
    Button addGame;
    @FXML
    Button removeGame;
    @FXML
    Button save;
    @FXML
    TextField name;
    @FXML
    TextField time;
    @FXML
    TextField fLocation;
    @FXML
    TextField maxCapacity;
    @FXML
    TextField guests;
    @FXML
    ComboBox<String> chooseGame;
    @FXML
    ComboBox<String> chooseMember;
    @FXML
    DatePicker date;
    @FXML
    TableView<BoardGame> gamesTable;
    @FXML
    TableView<Member> memberTable;
    @FXML
    TableView<guestClass1> guestTable1;

    @FXML
    TableColumn<guestClass1, String> guestsCol;
    @FXML
    TableColumn<Member, String> membersCol;

    @FXML
    TableColumn<BoardGame, String> tableColName;
    @FXML
    TableColumn<BoardGame, String> tableColType;
    @FXML
    TableColumn<BoardGame, Integer> tableColMinNoP;
    @FXML
    TableColumn<BoardGame, Integer> tableColMaxNoP;
    private ViewHandler viewHandler;
    private BoardGameManager boardGameManager;
    private Scene scene;
    public int eventIndex = 0;
    public MemberList memberList = new MemberList();
    public ArrayList<String> tempGuests = new ArrayList<String>();

    public void initialize() {
        tableColName.setCellValueFactory(
                new PropertyValueFactory<BoardGame, String>("name"));
        tableColType.setCellValueFactory(
                new PropertyValueFactory<BoardGame, String>("type"));
        tableColMinNoP.setCellValueFactory(
                new PropertyValueFactory<BoardGame, Integer>("minNoP"));
        tableColMaxNoP.setCellValueFactory(
                new PropertyValueFactory<BoardGame, Integer>("maxNoP"));

        guestsCol.setCellValueFactory(new PropertyValueFactory<guestClass1, String>("str1"));
        membersCol.setCellValueFactory(new PropertyValueFactory<Member, String>("fullName"));


    }

    public void init(ViewHandler viewHandler, Scene scene, BoardGameManager boardGameManager) {
        this.viewHandler = viewHandler;
        this.scene = scene;
        this.boardGameManager = boardGameManager;
    }

    public void update() {
        MemberList allMember = boardGameManager.getAllMembers();
        for (int i = 0; i < allMember.size(); i++) {
            chooseMember.getItems().add(allMember.get(i).getFirstName() + " " + allMember.get(i).getLastName());
        }
        chooseMember.getSelectionModel().selectFirst();
        BoardGameList list = boardGameManager.getAllBoardGames();
        for (int i = 0; i < list.size(); i++) {
            chooseGame.getItems().add(list.get(i).getName());
        }
        chooseGame.getSelectionModel().selectFirst();
    }

    public Scene getScene() {
        return scene;
    }
    public class guestClass1 {
        public String str1 = "";

        public guestClass1(String str1) {
            this.str1 = str1;
        }
        public String getStr1() {
            return str1;
        }
        public String toString() {
            return str1;
        }
    }
    public void editEvent(Event event) {
        BoardGameList eventgames = event.getGames();
        EventList list = boardGameManager.getAllEvents();
        ArrayList<String> tempGuest = event.getGuestsArr();
        MemberList tempMem = event.getMembers();
        name.setText(event.getName());
        fLocation.setText(event.getLocation());
        maxCapacity.setText(Integer.toString(event.getCapacity()));
        eventIndex = list.getIndexOf(event);
        date.setValue(LocalDate.of(event.getDate().getYear(), event.getDate().getMonth(), event.getDate().getDay()));
        time.setText(event.getDate().getStringTime());
        gamesTable.getItems().clear();
        for (int i = 0; i < eventgames.size(); i++) {
            gamesTable.getItems().add(eventgames.get(i));
        }
        guestTable1.getItems().clear();
        for (int i = 0; i<tempGuest.size();i++){
            guestClass1 ska = new guestClass1(tempGuest.get(i).toString());
            guestTable1.getItems().add(ska);
        }
        memberTable.getItems().clear();
        System.out.println(tempMem.toString());
        for (int i = 0; i<tempMem.size();i++){
            memberTable.getItems().add(tempMem.get(i));
        }

    }


    public void actionHandler(ActionEvent e) {
        BoardGameList allgameList = boardGameManager.getAllBoardGames();
        BoardGameList gameList = new BoardGameList();
        ObservableList<BoardGame> data = gamesTable.getItems();
        for (BoardGame yd : data) {
            gameList.addBoardGame(yd);
        }
        BoardGame row = gamesTable.getSelectionModel().getSelectedItem();
        if (e.getSource() == addGame && chooseGame.getValue() != null) {
            gamesTable.getItems().add(allgameList.get(chooseGame.getSelectionModel().getSelectedIndex()));
            gameList.addBoardGame(allgameList.get(chooseGame.getSelectionModel().getSelectedIndex()));
        }
        if (e.getSource() == removeGame && row != null) {
            gamesTable.getItems().remove(row);
            gameList.removeBoardGame(row);
        }
        if (e.getSource() == back) viewHandler.openView("manageEvent");
        if (e.getSource() == save) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Are you  sure you want to save the changes?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                try {
                    int a = Integer.parseInt(maxCapacity.getText());
                    String b = Integer.toString(date.getValue().getDayOfMonth()) + "/"
                            + Integer.toString(date.getValue().getMonthValue()) + "/" +
                            Integer.toString(date.getValue().getYear());
                    EventList list = boardGameManager.getAllEvents();
                    Event event = new Event(MyDate.stringToDate(b, time.getText()),
                            fLocation.getText(), name.getText(), guests.getText(), a, gameList , new MemberList());
                    ;
                    list.setEvent(event, eventIndex);
                    MyFileHandler.writeToBinaryFile("events.bin", list);
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Event list updated");
                    alert1.setHeaderText("Event updated successfully!");
                    alert1.setContentText(null);
                    alert1.showAndWait();
                    viewHandler.openView("manageEvent");
                } catch (FileNotFoundException ex) {
                    System.out.println("Error opening file ");
                } catch (IOException ex) {
                    System.out.println("IO Error writing to file ");
                }
            } else {
                // ... user chose CANCEL or closed the dialog
            }
        }
    }


}