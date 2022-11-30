package GUI;

import Model.BoardGameManager;
import Model.Member;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class ManageMemberController {

    private ViewHandler viewHandler;
    private BoardGameManager clubManager;
    private Scene scene;

    @FXML
     RadioButton name;
    @FXML
    RadioButton phone;

    @FXML
    TableColumn<Member, String> memberName;

    @FXML
    TableColumn<Member, String> memberEmail;
    @FXML
    TableColumn<Member, String> memberPhone;


    @FXML
    RadioButton email;
    @FXML
    TextField search;
    @FXML
    Button searchButton;
    @FXML
    TableView<Member> tableView;
    @FXML
    Button back;


    ObservableList<Member> searchedMember;

    public void init(ViewHandler viewHandler, Scene scene, BoardGameManager clubManager) {
        this.viewHandler = viewHandler;
        this.scene = scene;
        this.clubManager = clubManager;

        // making groups of radio buttons
        initRadioGrp();

        // intializing table
        initializeTable();
    }

    /**
     * > The function initializes the radio buttons by setting the toggle group and selecting the first radio button
     */
    private void initRadioGrp() {
        ToggleGroup group = new ToggleGroup();
        name.setToggleGroup(group);
        name.setSelected(true);
        phone.setToggleGroup(group);
        email.setToggleGroup(group);
    }

    public Scene getScene() {
        return scene;
    }

    private void initializeTable() {
        // Creating an observable list of members (this list is needed when we want to show list to gui)
        searchedMember = FXCollections.observableArrayList();


        // Setting the value of the table columns to the values of the member object.
        memberName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        memberEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        memberPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        tableView.setItems(searchedMember);
    }


    public void actionHandler(ActionEvent event){

        if (event.getSource() == search){
            searchMember();
        }
        else if (event.getSource() ==back){
            viewHandler.openView("Menu");
        }

    }

//    @FXML
//    private void searchClicked(){
//        searchMember();
//    }
//
//    @FXML
//    private void backClicked()
//    {
//        viewHandler.openView("Menu");
//    }


    private void searchMember() {
        ArrayList<Member> searchedMemberFromDb = new ArrayList<>();
        if (name.isSelected()) {
            searchedMemberFromDb = clubManager.searchByName(search.getText().trim());
        } else if (phone.isSelected()) {
            searchedMemberFromDb = clubManager.searchByPhone(search.getText().trim());

        } else if (email.isSelected()) {
            searchedMemberFromDb = clubManager.searchByEmail(search.getText().trim());

        }

        searchedMember.setAll(searchedMemberFromDb);
    }
}
