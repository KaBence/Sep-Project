package GUI;

import Model.BoardGameManager;
import Model.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;




public class AddMemberController {

  private ViewHandler viewHandler;
  private BoardGameManager clubmanager;
  private Scene scene;
  private Member member;

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


  public void init(ViewHandler viewHandler, Scene scene, BoardGameManager clubManager) {
    this.viewHandler = viewHandler;
    this.scene = scene;
    clubmanager = clubManager;
  }

  public Scene getScene() {
    return scene;
  }
  public Member getMember(){
    return member;
  }
  public void setMember(Member member){
    this.member = member;
  }
  public void update(){
    txtFirstName.setText(member.getFirstName());

  }

  public void actionHandler(ActionEvent e){
    if (e.getSource()==back) viewHandler.openView("Menu");


  }



}




