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

/**
 * A class for editing an events and saving the changes
 *
 * @author Igor Cretu
 */
public class EditEventController {
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
    @FXML
    TableColumn<BoardGame, Member> tableColOwner;

    private ViewHandler viewHandler;
    private BoardGameManager boardGameManager;
    private Scene scene;
    private int eventIndex;
    public ArrayList<String> tempGuests = new ArrayList<String>();
    public MemberList memberList = new MemberList();

    /**
     * A method for initializing the tables
     */
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

    /**
     * A method for setting the parameters
     *
     * @param viewHandler      sets the viewHandler
     * @param scene            sets The scene
     * @param boardGameManager sets the BoardGameManager
     */
    public void init(ViewHandler viewHandler, Scene scene, BoardGameManager boardGameManager) {
        this.viewHandler = viewHandler;
        this.scene = scene;
        this.boardGameManager = boardGameManager;
    }

    /**
     * A method for updating the Combo boxes with members and games
     */
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

    /**
     * Returns the editEvent scene
     *
     * @return editEvent scene
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * A nested class for creating a guest String object
     */
    public class guestClass1 {

        public String str1 = "";

        /**
         * One argument constructor for initializing the guestClass object
         *
         * @param str1 the string to create object with
         */
        public guestClass1(String str1) {
            this.str1 = str1;
        }

        /**
         * A method that returns the string
         *
         * @return str the string used to create object with
         */
        public String getStr1() {
            return str1;
        }

        /**
         * A method that returns the string
         *
         * @return str the string used to create object with
         */

        public String toString() {
            return str1;
        }
    }
    /**
     * A method for accessing the event's foelds
     * @param event the event that has to be edited
     */
    public void editEvent(Event event) {
        BoardGameList eventgames = event.getGames();
        EventList list = boardGameManager.getAllEvents();
        name.setText(event.getEventName());
        ArrayList<String> tempGuest = event.getGuestsArr();
        MemberList tempMem = event.getMemberList();
        memberList = tempMem;
        name.setText(event.getEventName());
        fLocation.setText(event.getLocation());
        maxCapacity.setText(Integer.toString(event.getCapacity()));
        eventIndex = list.getIndexOf(event);
        System.out.println(eventIndex);
        date.setValue(LocalDate.of(event.getDate().getYear(), event.getDate().getMonth(), event.getDate().getDay()));
        time.setText(event.getDate().getStringTime());
        gamesTable.getItems().clear();
        for (int i = 0; i < eventgames.size(); i++) {
            gamesTable.getItems().add(eventgames.get(i));
        }
        guestTable1.getItems().clear();
        for (int i = 0; i < tempGuest.size(); i++) {
            guestClass1 ska = new guestClass1(tempGuest.get(i).toString());
            guestTable1.getItems().add(ska);
        }
        memberTable.getItems().clear();
        for (int i = 0; i < tempMem.size(); i++) {
            memberTable.getItems().add(tempMem.get(i));
        }

    }
    /**
     * A method for handling the button clicking
     * @param e the event that is called when something happens
     */
    public void actionHandler(ActionEvent e) {
        BoardGameList allgameList = boardGameManager.getAllBoardGames();
        BoardGameList gameList = new BoardGameList();
        MemberList allmember = boardGameManager.getAllMembers();
        Member row1 = memberTable.getSelectionModel().getSelectedItem();
        guestClass1 guest = guestTable1.getSelectionModel().getSelectedItem();
        ObservableList<BoardGame> data = gamesTable.getItems();
        for (BoardGame yd : data) {
            gameList.addBoardGame(yd);
        }
        if (e.getSource() == addMember && chooseMember.getValue() != null) {
            if (memberList.size() == 0) {
                memberTable.getItems().add(allmember.get(chooseMember.getSelectionModel().getSelectedIndex()));
                memberList.addMember(allmember.get(chooseMember.getSelectionModel().getSelectedIndex()));
            } else {
                for (int i = 0; i < memberList.size(); i++) {
                    if (memberList.get(i).equals(allmember.get(chooseMember.getSelectionModel().getSelectedIndex()))) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("This member already exists in the list");
                        alert.setContentText(null);
                        alert.showAndWait();
                        return;
                    }
                }
                memberTable.getItems().add(allmember.get(chooseMember.getSelectionModel().getSelectedIndex()));
                memberList.addMember(allmember.get(chooseMember.getSelectionModel().getSelectedIndex()));
            }
        }
        if (e.getSource() == removeMember && row1 != null) {
            memberTable.getItems().remove(row1);
            memberList.removeMember(row1);
        }
        if (e.getSource() == addGuest && !guests.getText().isEmpty()) {
            String[] tempArr = guests.getText().split(",");
            if (tempGuests.size() == 0) {
                for (int i = 0; i < allmember.size(); i++) {
                    for (String j : tempArr) {
                        if (j.toLowerCase().equals(allmember.get(i).getFullName().toLowerCase())) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText("One or more guests already exist in the list as members");
                            alert.setContentText(null);
                            alert.showAndWait();
                            return;
                        }
                    }
                }
                for (String i : tempArr) {
                    if (i.charAt(0) == ' ') i = i.replaceFirst(" ", "");
                    guestClass1 ska = new guestClass1(i);
                    guestTable1.getItems().add(ska);
                    tempGuests.add(i);
                }
            } else {
                for (int i = 0; i < tempGuests.size(); i++) {
                    for (String j : tempArr) {
                        if (j.toLowerCase().equals(tempGuests.get(i).toLowerCase())) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText("One of more of the entered guests already exist in the list");
                            alert.setContentText(null);
                            alert.showAndWait();
                            return;
                        }
                    }
                }
                for (int i = 0; i < allmember.size(); i++) {
                    for (String j : tempArr) {
                        if (j.toLowerCase().equals(allmember.get(i).getFullName().toLowerCase())) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText("One or more guests already exist in the list as members");
                            alert.setContentText(null);
                            alert.showAndWait();
                            return;
                        }
                    }
                }
                for (String i : tempArr) {
                    if (i.charAt(0) == ' ') i = i.replaceFirst(" ", "");
                    guestClass1 ska = new guestClass1(i);
                    guestTable1.getItems().add(ska);
                    tempGuests.add(i);
                }
            }
            guests.clear();
        }
        if (e.getSource() == removeGuest && guest != null) {
            guestTable1.getItems().remove(guest);
            tempGuests.remove(guest);
        }
        BoardGame row = gamesTable.getSelectionModel().getSelectedItem();
        if (e.getSource() == addGame && chooseGame.getValue() != null) {
            if (gameList.size() == 0) {
                gamesTable.getItems().add(allgameList.get(chooseGame.getSelectionModel().getSelectedIndex()));
                gameList.addBoardGame(allgameList.get(chooseGame.getSelectionModel().getSelectedIndex()));
            } else {
                for (int i = 0; i < gameList.size(); i++) {
                    if (gameList.get(i).equals(allgameList.get(chooseGame.getSelectionModel().getSelectedIndex()))) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("This game already exists in the list");
                        alert.setContentText(null);
                        alert.showAndWait();
                        return;
                    }
                }
                gamesTable.getItems().add(allgameList.get(chooseGame.getSelectionModel().getSelectedIndex()));
                gameList.addBoardGame(allgameList.get(chooseGame.getSelectionModel().getSelectedIndex()));
            }
        }
        if (e.getSource() == removeGame && row != null) {
            gamesTable.getItems().remove(row);
            gameList.removeBoardGame(row);
        }
        if (e.getSource() == back) {
            if (!time.getText().isBlank() || !fLocation.getText().isBlank() ||
                    !name.getText().isBlank() || !guests.getText().isBlank() || !guestTable1.getItems().isEmpty() ||
                    !gamesTable.getItems().isEmpty() || !memberTable.getItems().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Dialog");
                alert.setHeaderText("Are you  sure you want to leave without saving changes?");
                alert.setContentText(null);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    viewHandler.openView("manageEvent");
                } else {
                    // ... user chose CANCEL or closed the dialog
                }
            } else {
                viewHandler.openView("manageEvent");
            }
        }

        if (e.getSource() == save) {
            int a;
            System.out.println(eventIndex);
            if (date.getValue() != null && !fLocation.getText().isBlank() && !name.getText().isBlank() && !gamesTable.getItems().isEmpty()) {
                if (!date.getValue().isBefore(LocalDate.now())) {
                    if (!maxCapacity.getText().isBlank()) {
                        a = Integer.parseInt(maxCapacity.getText());
                    } else {
                        a = 0;
                    }
                    MyDate c;
                    if (!time.getText().isBlank()) {
                        if (MyDate.timeFormat(time.getText())) {
                            MyDate s = MyDate.stringToDate("00/00/00", time.getText());
                            if (s.getHour() < 00 || s.getHour() > 24 || s.getMin() < 0 || s.getMin() > 59) {
                                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                                alert1.setTitle("Error");
                                alert1.setHeaderText("Hours can't be bigger than 24 or smaller than 00\nMinutes can't be bigger than 59 or smaller than 00");
                                alert1.setContentText("Correct time format is hour:minutes");
                                alert1.showAndWait();
                                time.clear();
                                return;
                            } else {

                                c = MyDate.stringToDate(Integer.toString(date.getValue().getDayOfMonth()) + "/" + Integer.toString(date.getValue().getMonthValue()) + "/" + Integer.toString(date.getValue().getYear()), time.getText());
                            }

                        } else {
                            Alert alert1 = new Alert(Alert.AlertType.ERROR);
                            alert1.setTitle("Error");
                            alert1.setHeaderText("Invalid time format");
                            alert1.setContentText("Correct time format is hour:minutes");
                            alert1.showAndWait();
                            return;
                        }
                    } else {
                        c = new MyDate(date.getValue().getDayOfMonth(), date.getValue().getMonthValue(), date.getValue().getYear());
                    }
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Dialog");
                    alert.setHeaderText("Are you  sure you want to save the changes?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        try {
                            EventList list = boardGameManager.getAllEvents();
                            Event event = new Event(c,
                                    fLocation.getText(), name.getText(), tempGuests.toString().replace("[", "").replace("]", ""), a, gameList, memberList);
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

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Warning");
                    alert.setHeaderText("The date cannot be earlier than now");
                    alert.setContentText(null);
                    alert.showAndWait();
                    date.setValue(null);
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Warning");
                alert.setHeaderText("All the fields marked with * have to be filled");
                alert.setContentText(null);
                alert.showAndWait();
            }

        }
    }
}

