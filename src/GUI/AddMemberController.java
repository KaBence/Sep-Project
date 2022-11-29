package GUI;

import Model.BoardGameClub;
import Model.Member;
import Util.MyFileHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.util.logging.FileHandler;


public class AddMemberController {

  private ViewHandler viewHandler;
  private BoardGameClub clubmanager;
  private Scene scene;

  @FXML
  TextField txtFirstName;

  @FXML
  TextField lastName;
  @FXML
  TextField phone;
  @FXML
  TextField email;

  @FXML
  Button back;
  @FXML
  Button btnAddTheMember;
  @FXML
  RadioButton yesButton, noButton;


  public void init(ViewHandler viewHandler, Scene scene, BoardGameClub clubManager) {
    this.viewHandler = viewHandler;
    this.scene = scene;
    clubmanager = clubManager;
  }

  public Scene getScene() {
    return scene;
  }

  public void actionHandler(ActionEvent e){
    if (e.getSource()==back) viewHandler.openView("Menu");
    if(e.getSource() == btnAddTheMember){
      clubmanager.addMemberToClub(newMember());
    }

  }

  public Member newMember() {
    String firstName = txtFirstName.getText();
    String familyName = lastName.getText();
    String phoneNumber = phone.getText();
    String emailAdd = email.getText();
    String selection = null;
    if(yesButton.isSelected()){
      selection = yesButton.getText();
    }else if(noButton.isSelected())  {
      selection = noButton.getText();
    }
    Member member = new Member(firstName,familyName,phoneNumber,emailAdd,selection);
    System.out.println(member);
    return member;
  }
}




