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

  public void actionHandler(ActionEvent e){
    if (e.getSource()==back) viewHandler.openView("Menu");

    // when add member button is clicked
    /*if(e.getSource() == btnAddTheMember){
      // adding member to file by calling add member to club in club manager
      clubmanager.addMemberToClub(newMember());
      clearFields();
      // displaying new alert box when member is added. display method is static method
      AlertBox.display("Member has been added");
    }
*/
  }

  /**
   * This function clears all text field
   */
  private void clearFields() {

    txtFirstName.clear();
    lastName.clear();
    phone.clear();
    email.clear();
  }

  /**
   * The function creates a new member object and returns it
   *
   * @return A new member object is being returned.
   */
 /* public Member newMember() {
    // getting values of all text field
    String firstName = txtFirstName.getText();
    String familyName = lastName.getText();
    String phoneNumber = phone.getText();
    String emailAdd = email.getText();
    String selection = null;
    // set value of selection as yes or no
    if(yesButton.isSelected()){
      selection = yesButton.getText();
    }else if(noButton.isSelected())  {
      selection = noButton.getText();
    }
    // creating new member object
    Member member = new Member(firstName,familyName,phoneNumber,emailAdd,selection);
    return member;
  }*/
}




