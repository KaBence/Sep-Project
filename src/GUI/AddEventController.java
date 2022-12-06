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

public class AddEventController {
    @FXML
    Button back;
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
    DatePicker date;
    @FXML
    TableView<BoardGame> games;
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
    public BoardGameList gameList = new BoardGameList();
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
        tableColOwner.setCellValueFactory(
                new PropertyValueFactory<BoardGame, Member>("owner"));
        chooseGame.getSelectionModel().selectFirst();
    }

    public Scene getScene() {
        return scene;
    }
public void update(){
    BoardGameList allgameList = boardGameManager.getAllBoardGames();
    for (int i = 0; i < allgameList.size(); i++) {
        chooseGame.getItems().add(allgameList.get(i).getName());
    }
    chooseGame.getSelectionModel().selectFirst();
}
    public void actionHandler(ActionEvent e) {
        BoardGameList allgameList = boardGameManager.getAllBoardGames();
        BoardGame row = games.getSelectionModel().getSelectedItem();

        if (e.getSource() == addGame && chooseGame.getValue() != null) {
            games.getItems().add(allgameList.get(chooseGame.getSelectionModel().getSelectedIndex()));
            gameList.addBoardGame(allgameList.get(chooseGame.getSelectionModel().getSelectedIndex()));
            System.out.println(gameList);
        }

        if (e.getSource() == removeGame && row != null) {
            games.getItems().remove(row);
            gameList.removeBoardGame(row);
        }
        if (e.getSource() == back)
            viewHandler.openView("Menu");
        if (e.getSource() == addEvent) {
            try {
                int a = Integer.parseInt(maxCapacity.getText());
                String b = Integer.toString(date.getValue().getDayOfMonth()) + "/"
                        + Integer.toString(date.getValue().getMonthValue()) + "/" +
                        Integer.toString(date.getValue().getYear());
                EventList list = boardGameManager.getAllEvents();
                Event event = new Event(MyDate.stringToDate(b, time.getText()),
                        fLocation.getText(), name.getText(), guests.getText(), a, gameList);
                list.addEvent(event);
                MyFileHandler.writeToBinaryFile("events.bin", list);
                System.out.println("Events done");
            } catch (FileNotFoundException ex) {
                System.out.println("Error opening file ");
            } catch (IOException ex) {
                System.out.println("IO Error writing to file ");
            }
        }
    }
}

