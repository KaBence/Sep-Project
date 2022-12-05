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

  public void init(ViewHandler viewHandler, Scene scene,
      BoardGameManager clubManager)
  {
    this.viewHandler = viewHandler;
    this.scene = scene;
    boardGameManager = clubManager;
  }

  public Scene getScene()
  {
    return scene;
  }

  public Member getMember()
  {
    return member;
  }

  public void setMember(Member member)
  {
    this.member = member;
  }

  public void update()
  {
    txtFirstName.setText(member.getFirstName());
    lastName.setText(member.getLastName());
    phone.setText(member.getPhoneNumber());
    email.setText(member.getEmail());
  }

  public boolean isCheck()
  {
    return check;
  }

  public void setCheck(boolean check)
  {
    this.check = check;
  }

  public void clear()
  {
    txtFirstName.clear();
    lastName.clear();
    phone.clear();
    email.clear();
  }

  public void actionHandler(ActionEvent e)
  {
    if (e.getSource() == back)
      viewHandler.openView("Menu");

    if (e.getSource() == btnAddTheMember)
    {
      MemberList memberList = boardGameManager.getAllMembers();
      Member member1 = new Member(txtFirstName.getText(), lastName.getText(),
          phone.getText(), email.getText());
      if (!(phone.getText().length() == 6))
      {
        Alert wrongPhoneFormat = new Alert(Alert.AlertType.ERROR,
            "This phone number is not legit", ButtonType.OK);
        wrongPhoneFormat.setTitle("stupid");
        wrongPhoneFormat.setHeaderText(null);
        wrongPhoneFormat.showAndWait();
        return;
      }
      if(!(email.getText().contains("@"))){
        Alert wrongEmailFormat = new Alert(Alert.AlertType.ERROR,
            "This email should look like: name@example.domain", ButtonType.OK);
        wrongEmailFormat.setTitle("stupid");
        wrongEmailFormat.setHeaderText(null);
        wrongEmailFormat.showAndWait();
        return;
      }
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
      memberList.addMember(member1);
      boardGameManager.saveAllMembers(memberList);
      System.out.println("members done");
      clear();

      Alert alert = new Alert(Alert.AlertType.INFORMATION,
          "Member added successfully", ButtonType.OK);
      alert.setTitle("Good job");
      alert.setHeaderText(null);
      alert.showAndWait();
      if(yesButton.isSelected()){
        viewHandler.openView("addBoardGame");
        Alert addGame = new Alert(Alert.AlertType.INFORMATION,
            "Please add your game now", ButtonType.OK);
        addGame.setTitle("Good job");
        addGame.setHeaderText(null);
        addGame.showAndWait();
      }
      else
      viewHandler.openView("Menu");
    }
  }
}




