package GUI;

import Model.BoardGameManager;
import Model.Member;
import Model.MemberList;
import Util.MyFileHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import jdk.jfr.Label;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * A class for adding a new Member into the system and file
 *
 * @author Michaela Veselvoska
 */
public class AddMemberController
{

  private ViewHandler viewHandler;
  private BoardGameManager boardGameManager;
  private Scene scene;
  private Member member;
  private boolean check = false;
  private MemberList memberList;

  @FXML TextField txtFirstName;

  @FXML TextField lastName;
  @FXML TextField phone;
  @FXML TextField email;

  @FXML Button back;
  @FXML Button btnAddTheMember;
  @FXML RadioButton yesButton, noButton;

  /**
   * method for setting the scene
   *
   * @param viewHandler
   * @param scene
   * @param boardGameManager
   */
  public void init(ViewHandler viewHandler, Scene scene,
      BoardGameManager boardGameManager)
  {
    this.viewHandler = viewHandler;
    this.scene = scene;
    this.boardGameManager = boardGameManager;
  }

  /**
   * returns the scene
   *
   * @return scene
   */
  public Scene getScene()
  {
    return scene;
  }

  /**
   * returns the member
   *
   * @return member
   */
  public Member getMember()
  {
    return member;
  }

  /**
   * method for setting a member
   *
   * @param member
   */
  public void setMember(Member member)
  {
    this.member = member;
  }

  /**
   * method for updating the member informaiton
   */
  public void update()
  {
    txtFirstName.setText(member.getFirstName());
    lastName.setText(member.getLastName());
    phone.setText(member.getPhoneNumber());
    email.setText(member.getEmail());
  }

  /**
   * method for clearing the textfields
   */
  public void clear()
  {
    txtFirstName.clear();
    lastName.clear();
    phone.clear();
    email.clear();
  }

  /**
   * methods for functionality of the buttons
   *
   * @param e the event that is called when something happens
   *          when pressing back, the scene returns to the menu scene
   *          when pressing button add member, new member is created and added to the file
   *          the text fields are cleared and alert shows with a messege of success
   */
  public void actionHandler(ActionEvent e)
  {

    if (e.getSource() == back)
      viewHandler.openView("Menu");

    if (e.getSource() == btnAddTheMember)
    {
      //if text fields empty ...warning
      if (txtFirstName.getText().isEmpty() || lastName.getText().isEmpty()
          || email.getText().isEmpty() || phone.getText().isEmpty())
      {
        Alert alert = new Alert(Alert.AlertType.WARNING,
            "Fill every field before adding a Member", ButtonType.OK);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.showAndWait();
        return;
      }
      //new member created
      MemberList memberList = boardGameManager.getAllMembers();
      Member member1 = new Member(txtFirstName.getText(), lastName.getText(),
          phone.getText(), email.getText());

      //checking phone
      if (!(phone.getText().length() == 8))
      {
        Alert wrongPhoneFormat = new Alert(Alert.AlertType.ERROR,
            "This phone number is not legit", ButtonType.OK);
        wrongPhoneFormat.setTitle("stupid");
        wrongPhoneFormat.setHeaderText(null);
        wrongPhoneFormat.showAndWait();
        return;
      }
      //checking email

      if (!(email.getText().contains("@")))
      {
        Alert wrongEmailFormat = new Alert(Alert.AlertType.ERROR,
            "This email should look like: name@example.domain", ButtonType.OK);
        wrongEmailFormat.setTitle("stupid");
        wrongEmailFormat.setHeaderText(null);
        wrongEmailFormat.showAndWait();
        return;
      }
      //two members cannot be equal
      for (int i = 0; i < memberList.size(); i++)
      {
        if (member1.equals(memberList.get(i)))
        {
          Alert error = new Alert(Alert.AlertType.ERROR,
              "This member already exist", ButtonType.OK);
          error.setTitle("Wrong move buddy");
          error.setHeaderText(null);
          error.showAndWait();
          return;
        }
      }
      //adding a member to the list
      memberList.addMember(member1);
      boardGameManager.saveAllMembers(memberList);
      //just test
      System.out.println("members done");
      //clearing fields
      clear();
      //alert success
      Alert alert = new Alert(Alert.AlertType.INFORMATION,
          "Member added successfully", ButtonType.OK);
      alert.setTitle("Good job");
      alert.setHeaderText(null);
      alert.showAndWait();
      // opening add board game and alert
      if (yesButton.isSelected())
      {
        viewHandler.openView("addBoardGame");
        Alert addGame = new Alert(Alert.AlertType.INFORMATION,
            "Please add your game now", ButtonType.OK);
        addGame.setTitle("Good job");
        addGame.setHeaderText(null);
        addGame.showAndWait();
        viewHandler.getAddBoardGameController().updateComboBox();
        viewHandler.getAddBoardGameController().setOwner(member1);
      }
      else
        viewHandler.openView("Menu");
    }
  }
}




