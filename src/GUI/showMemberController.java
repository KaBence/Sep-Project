package GUI;

import Model.BoardGameManager;
import Model.Member;
import Model.MemberList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

public class showMemberController
{

  @FXML TextField firstName;
  @FXML TextField lastName;
  @FXML TextField email;
  @FXML TextField phone;
  @FXML Button back;
  @FXML Button save;
  @FXML Button delete;
  private Member member;
  private ViewHandler viewHandler;
  private BoardGameManager boardGameManager;
  private Scene scene;

  public void init(ViewHandler viewHandler, Scene scene,
      BoardGameManager boardGameManager)
  {
    this.viewHandler = viewHandler;
    this.scene = scene;
    this.boardGameManager = boardGameManager;
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
    firstName.setText(member.getFirstName());
    lastName.setText(member.getLastName());
    phone.setText(member.getPhoneNumber());
    email.setText(member.getEmail());
  }

  public void clear()
  {
    firstName.clear();
    lastName.clear();
    phone.clear();
    email.clear();
  }

  public void actionHandler(ActionEvent e)
  {
    if (e.getSource() == back)
    {
      viewHandler.openView("Menu");
    }
    if (e.getSource() == delete)
    {
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
          "Are you sure you want to do this?", ButtonType.YES, ButtonType.NO);
      alert.setTitle("You are killing someone");
      alert.setHeaderText(null);
      alert.showAndWait();
      if (alert.getResult() == ButtonType.YES)
      {
        MemberList memberList = boardGameManager.getAllMembers();
        memberList.removeMember(member);
        System.out.println("removing done");
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION,
            "Member removed successfully", ButtonType.OK);
        alert1.setTitle("You ruined someone but whatever");
        alert1.setHeaderText(null);
        alert1.showAndWait();
        boardGameManager.saveAllMembers(memberList);
        viewHandler.openView("manageMember");
      }
    }
    if (e.getSource() == save)
    {
      MemberList memberList = boardGameManager.getAllMembers();
      memberList.removeMember(member);
      Member member1 = new Member(firstName.getText(), lastName.getText(),
          phone.getText(), email.getText());
      memberList.addMember(member1);

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
      Alert alert1 = new Alert(Alert.AlertType.INFORMATION,
          "Member edited successfully", ButtonType.OK);
      alert1.setTitle("Good job");
      alert1.setHeaderText(null);
      alert1.showAndWait();
      boardGameManager.saveAllMembers(memberList);
      viewHandler.getManageMemberController().update();
      viewHandler.openView("manageMember");
      System.out.println("editing done");

    }

  }
}
