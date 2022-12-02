package GUI;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class ManageMemberController {



    @FXML
     RadioButton name;
    @FXML
    RadioButton phone;

    @FXML
    RadioButton email;
    @FXML
    TableColumn<Member, String> memberName;

    @FXML
    TableColumn<Member, String> memberEmail;
    @FXML
    TableColumn<Member, String> memberPhone;



    @FXML
    TextField search;
    @FXML
    Button searchButton;
    @FXML
    TableView<Member> tableView;
    @FXML
    Button back;

    @FXML TableColumn<Member,String> TableColMemberName;
    //@FXML TableColumn<Member,String> TableColMemberName;
    @FXML TableColumn<Member,String> TableColMemberEmail;
    @FXML TableColumn<Member,String> TableColMemberPhone;

    private ViewHandler viewHandler;
    private BoardGameManager boardGameManager;
    private Scene scene;
    public void initialize()
    {
        TableColMemberName.setCellValueFactory(new PropertyValueFactory<Member, String>("firstName"));
        TableColMemberEmail.setCellValueFactory(new PropertyValueFactory<Member, String>("email"));
        TableColMemberPhone.setCellValueFactory(new PropertyValueFactory<Member, String>("phoneNumber"));

    }






    public void init(ViewHandler viewHandler, Scene scene, BoardGameManager boardGameManager) {
        this.viewHandler = viewHandler;
        this.scene = scene;
        this.boardGameManager = boardGameManager;

    }

    public void update(){

        MemberList members =boardGameManager.getAllMembers();
        for (int i = 0; i < members.size(); i++)
        {
            tableView.getItems().add(members.get(i));
        }
    }


    public Scene getScene() {
        return scene;
    }

    public void actionHandler(ActionEvent event){

        if (event.getSource() ==back){
            viewHandler.openView("Menu");
        }

    }




}
