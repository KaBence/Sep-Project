package GUI;

import Model.*;
import Util.MyFileHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class AddEventController {
    @FXML
    Button back;
    @FXML
    Button addMember;
    @FXML
    Button addGuest;
    @FXML
    Button removeMember;
    @FXML
    Button removeGuest;
    @FXML
    Button addEvent;
    @FXML
    Button addGame;
    @FXML
    Button removeGame;
    @FXML
    TextField name;
    @FXML
    TextField fLocation;
    @FXML
    TextField time;
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
    TableView<BoardGame> games;
    @FXML
    TableView<guestClass> guestTable;
    @FXML
    TableView<Member> memberTable;
    @FXML
    TableColumn<guestClass, String> guestsCol;
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
    public BoardGameList gameList = new BoardGameList();
    public MemberList memberList = new MemberList();
    public ArrayList<String> tempGuests = new ArrayList<String>();

    public void init(ViewHandler viewHandler, Scene scene,
                     BoardGameManager boardGameManager) {
        this.viewHandler = viewHandler;
        this.scene = scene;
        this.boardGameManager = boardGameManager;
    }

    public void initialize() {

        tableColName.setCellValueFactory(
                new PropertyValueFactory<BoardGame, String>("name"));
        tableColType.setCellValueFactory(
                new PropertyValueFactory<BoardGame, String>("type"));
        tableColMinNoP.setCellValueFactory(
                new PropertyValueFactory<BoardGame, Integer>("minNoP"));
        tableColMaxNoP.setCellValueFactory(
                new PropertyValueFactory<BoardGame, Integer>("maxNoP"));
        guestsCol.setCellValueFactory(new PropertyValueFactory<guestClass, String>("str"));
        membersCol.setCellValueFactory(new PropertyValueFactory<Member, String>("fullName"));
        chooseGame.getSelectionModel().selectFirst();
    }

    public Scene getScene() {
        return scene;
    }

    public void update() {
        MemberList allMember = boardGameManager.getAllMembers();
        for (int i = 0; i < allMember.size(); i++) {
            chooseMember.getItems().add(allMember.get(i).getFirstName() + " " + allMember.get(i).getLastName());
        }
        chooseMember.getSelectionModel().selectFirst();

        BoardGameList allgameList = boardGameManager.getAllBoardGames();
        for (int i = 0; i < allgameList.size(); i++) {
            chooseGame.getItems().add(allgameList.get(i).getName());
        }
        chooseGame.getSelectionModel().selectFirst();
    }

    public void clear() {
        tempGuests = new ArrayList<String>();
        games.getItems().clear();
        guestTable.getItems().clear();
        memberTable.getItems().clear();
        name.clear();
        time.clear();
        fLocation.clear();
        maxCapacity.clear();
        guests.clear();
        date.setValue(null);
    }

    public class guestClass {
        public String str = "";

        public guestClass(String str) {
            this.str = str;
        }
        public String getStr() {
            return str;
        }
        public String toString(){
            return str;
        }
    }

    public void actionHandler(ActionEvent e) {
        BoardGameList allgameList = boardGameManager.getAllBoardGames();
        BoardGame row = games.getSelectionModel().getSelectedItem();
        MemberList allmember = boardGameManager.getAllMembers();
        Member row1 = memberTable.getSelectionModel().getSelectedItem();
        guestClass guest = guestTable.getSelectionModel().getSelectedItem();
        if (e.getSource() == addGame && chooseGame.getValue() != null) {
            games.getItems().add(allgameList.get(chooseGame.getSelectionModel().getSelectedIndex()));
            gameList.addBoardGame(allgameList.get(chooseGame.getSelectionModel().getSelectedIndex()));
        }
        if (e.getSource() == removeGame && row != null) {
            games.getItems().remove(row);
            gameList.removeBoardGame(row);
        }
        if (e.getSource() == addMember && chooseMember.getValue() != null) {
            memberTable.getItems().add(allmember.get(chooseMember.getSelectionModel().getSelectedIndex()));
            memberList.addMember(allmember.get(chooseMember.getSelectionModel().getSelectedIndex()));
        }
        if (e.getSource() == removeMember && row1 != null) {
            memberTable.getItems().remove(row1);
            memberList.removeMember(row1);
        }
        if (e.getSource() == addGuest && guests.getText() != null) {
            String[] tempArr = guests.getText().split(",");
            for (String i : tempArr) {
                guestClass ska = new guestClass(i);
                guestTable.getItems().add(ska);
                tempGuests.add(i);
            }
        }
        if (e.getSource() == removeGuest && guest != null) {
            guestTable.getItems().remove(guest);
            tempGuests.remove(guest);
        }
        if (e.getSource() == back)
            viewHandler.openView("Menu");
        if (e.getSource() == addEvent) {
            try {
                System.out.println(memberList);
                int a = Integer.parseInt(maxCapacity.getText());
                String b = Integer.toString(date.getValue().getDayOfMonth()) + "/"
                        + Integer.toString(date.getValue().getMonthValue()) + "/" +
                        Integer.toString(date.getValue().getYear());
                EventList list = boardGameManager.getAllEvents();
                Event event = new Event(MyDate.stringToDate(b, time.getText()),
                        fLocation.getText(), name.getText(), tempGuests.toString(), a, gameList, memberList);
                list.addEvent(event);
                MyFileHandler.writeToBinaryFile("events.bin", list);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Event list updated");
                alert.setHeaderText("Event added successfully!");
                alert.setContentText(event.toString());
                alert.showAndWait();
                clear();
            } catch (FileNotFoundException ex) {
                System.out.println("Error opening file ");
            } catch (IOException ex) {
                System.out.println("IO Error writing to file ");
            }
        }
    }
}
