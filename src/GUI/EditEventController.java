package GUI;

import Model.*;
import Util.MyFileHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.collections.ObservableList;
import java.time.LocalDate;

import java.io.FileNotFoundException;
import java.io.IOException;

public class EditEventController {
    @FXML
    Button back;
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
    DatePicker date;
    @FXML
    TableView<BoardGame> gamesTable;
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
    public int eventIndex = 0;
    public void initialize() {
        tableColName.setCellValueFactory(
                new PropertyValueFactory<BoardGame, String>("name"));
        tableColType.setCellValueFactory(
                new PropertyValueFactory<BoardGame, String>("type"));
        tableColMinNoP.setCellValueFactory(
                new PropertyValueFactory<BoardGame, Integer>("minNoP"));
        tableColMaxNoP.setCellValueFactory(
                new PropertyValueFactory<BoardGame, Integer>("maxNoP"));
        tableColOwner.setCellValueFactory(
                new PropertyValueFactory<BoardGame, Member>("owner"));


    }
    public void init(ViewHandler viewHandler, Scene scene, BoardGameManager boardGameManager) {
        this.viewHandler = viewHandler;
        this.scene = scene;
        this.boardGameManager = boardGameManager;
    }
    public void update(){
        BoardGameList list = boardGameManager.getAllBoardGames();
        for (int i = 0; i < list.size(); i++) {
            chooseGame.getItems().add(list.get(i).getName());
        }
        chooseGame.getSelectionModel().selectFirst();
    }
    public Scene getScene() {
        return scene;
    }
    public void editEvent(Event event) {
        BoardGameList eventgames = event.getGames();
        EventList list = boardGameManager.getAllEvents();
        name.setText(event.getName());
        fLocation.setText(event.getLocation());
        maxCapacity.setText(Integer.toString(event.getCapacity()));
        guests.setText(event.getGuests());
        eventIndex = list.getIndexOf(event);
        date.setValue(LocalDate.of(event.getDate().getYear(), event.getDate().getMonth(), event.getDate().getDay()));
        time.setText(event.getDate().getStringTime());
        gamesTable.getItems().clear();
        for (int i = 0; i <eventgames.size(); i++)
        {
            gamesTable.getItems().add(eventgames.get(i));
        }

    }
    public void actionHandler(ActionEvent e) {
        BoardGameList allgameList = boardGameManager.getAllBoardGames();
        BoardGameList gameList = new BoardGameList();
        ObservableList<BoardGame> data =gamesTable.getItems();
        for(BoardGame yd : data) {
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
            try {
                int a = Integer.parseInt(maxCapacity.getText());
                String b = Integer.toString(date.getValue().getDayOfMonth()) + "/"
                        + Integer.toString(date.getValue().getMonthValue()) + "/" +
                        Integer.toString(date.getValue().getYear());
                EventList list = boardGameManager.getAllEvents();
                Event event = new Event(MyDate.stringToDate(b, time.getText()),
                        fLocation.getText(), name.getText(), guests.getText(), a, gameList);
                ;
                list.setEvent(event, eventIndex);
                MyFileHandler.writeToBinaryFile("events.bin", list);
//                output.setText(event.toString());
                System.out.println("Events done");
            } catch (FileNotFoundException ex) {
                System.out.println("Error opening file ");
            } catch (IOException ex) {
                System.out.println("IO Error writing to file ");
            }
        }
    }


}