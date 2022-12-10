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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
/**
 * A class for adding an event to the list of Events
 * @author Igor Cretu
 */
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
    /**
     * creating new list of boardgames
     */
    public BoardGameList gameList = new BoardGameList();
    /**
     * creating new list of members
     */
    public MemberList memberList = new MemberList();
    /**
     * creating new Arraylist of guests
     */
    public ArrayList<String> tempGuests = new ArrayList<String>();

    /**
     * A method for setting the parameters
     * @param viewHandler sets the viewHandler
     * @param scene sets The scene
     * @param boardGameManager sets the BoardGameManager
     */
    public void init(ViewHandler viewHandler, Scene scene, BoardGameManager boardGameManager) {
        this.viewHandler = viewHandler;
        this.scene = scene;
        this.boardGameManager = boardGameManager;
    }
    /**
     * A method for initializing the tables
     */
    public void initialize() {

        tableColName.setCellValueFactory(new PropertyValueFactory<BoardGame, String>("name"));
        tableColType.setCellValueFactory(new PropertyValueFactory<BoardGame, String>("type"));
        tableColMinNoP.setCellValueFactory(new PropertyValueFactory<BoardGame, Integer>("minNoP"));
        tableColMaxNoP.setCellValueFactory(new PropertyValueFactory<BoardGame, Integer>("maxNoP"));
        guestsCol.setCellValueFactory(new PropertyValueFactory<guestClass, String>("str"));
        membersCol.setCellValueFactory(new PropertyValueFactory<Member, String>("fullName"));
        chooseGame.getSelectionModel().selectFirst();
    }
    /**
     * Returns the addEvent scene
     * @return addEvent scene
     */
    public Scene getScene() {
        return scene;
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

        BoardGameList temp= boardGameManager.getAllBoardGames();
        BoardGameList allgameList = temp.getBoardGamesByAvailability(temp,true);
        for (int i = 0; i < allgameList.size(); i++) {
            chooseGame.getItems().add(allgameList.get(i).getName());
        }
        chooseGame.getSelectionModel().selectFirst();
        clear();
    }
    /**
     * A method for clearing the fields
     */
    public void clear() {
        tempGuests = new ArrayList<String>();
        games.getItems().clear();
        guestTable.getItems().clear();
        memberTable.getItems().clear();
        name.clear();
        time.clear();
        fLocation.clear();
        maxCapacity.setText("0");
        guests.clear();
        date.setValue(null);
    }
    /**
     * A nested class for creating a guest String object
     */
    public class guestClass {
        /**
         * initialising the String of guests
         */
        public String str = "";
        /**
         *  One argument constructor for initializing the guestClass object
         * @param str the string to create object with
         */
        public guestClass(String str) {
            this.str = str;
        }
        /**
         *  A method that returns the string
         * @return  str the string used to create object with
         */
        public String getStr() {
            return str;
        }
        /**
         *  A method that returns the string
         * @return  str the string used to create object with
         */
        public String toString() {
            return str;
        }
    }
    /**
     * A method for handling the button clicking
     * @param e the event that is called when something happens
     */
    public void actionHandler(ActionEvent e) {
        EventList allevents = boardGameManager.getAllEvents();
        BoardGameList temp= boardGameManager.getAllBoardGames();
        BoardGameList allgameList = temp.getBoardGamesByAvailability(temp,true);
        BoardGame row = games.getSelectionModel().getSelectedItem();
        MemberList allmember = boardGameManager.getAllMembers();
        Member row1 = memberTable.getSelectionModel().getSelectedItem();
        guestClass guest = guestTable.getSelectionModel().getSelectedItem();
        if (e.getSource() == addGame && chooseGame.getValue() != null) {
            if (gameList.size() == 0) {
                games.getItems().add(allgameList.get(chooseGame.getSelectionModel().getSelectedIndex()));
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
                games.getItems().add(allgameList.get(chooseGame.getSelectionModel().getSelectedIndex()));
                gameList.addBoardGame(allgameList.get(chooseGame.getSelectionModel().getSelectedIndex()));
            }
        }

        if (e.getSource() == removeGame && row != null) {
            games.getItems().remove(row);
            gameList.removeBoardGame(row);
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
                    guestClass ska = new guestClass(i);
                    guestTable.getItems().add(ska);
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
                    guestClass ska = new guestClass(i);
                    guestTable.getItems().add(ska);
                    tempGuests.add(i);
                }
            }
            guests.clear();
        }
        if (e.getSource() == removeGuest && guest != null) {
            guestTable.getItems().remove(guest);
            tempGuests.remove(guest);
        }
        if (e.getSource() == back) {
            if (!time.getText().isBlank() || !fLocation.getText().isBlank() || !name.getText().isBlank() || !guests.getText().isBlank() || !guestTable.getItems().isEmpty() || !games.getItems().isEmpty() || !memberTable.getItems().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Dialog");
                alert.setHeaderText("Are you  sure you want to leave without saving changes?");
                alert.setContentText(null);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    viewHandler.openView("Menu");
                } else {
                    // ... user chose CANCEL or closed the dialog
                }
            } else {
                viewHandler.openView("Menu");
            }
        }

        if (e.getSource() == addEvent) {
            int a;
            if (date.getValue() != null && !fLocation.getText().isBlank() && !name.getText().isBlank() && !games.getItems().isEmpty()) {
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
                    } else{
                        c = new MyDate(date.getValue().getDayOfMonth(), date.getValue().getMonthValue(), date.getValue().getYear());}
                    try {
                        EventList list = boardGameManager.getAllEvents();
                        Event event = new Event(c, fLocation.getText(), name.getText(), tempGuests.toString().replace("[", "").replace("]", ""), a, gameList, memberList);
                        for (int i = 0; i < allevents.size(); i++) {
                            if (event.equals(allevents.get(i))) {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Error");
                                alert.setHeaderText("Such an event already exists");
                                alert.setContentText(null);
                                alert.showAndWait();
                                return;
                            }
                        }
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
