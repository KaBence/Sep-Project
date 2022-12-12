package GUI;

import Model.BoardGameList;
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

/**
 * class for showing information about selected member
 * @author Michaela Veselovska
 */
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

  /**
   * method to be ablo to connect with other classes
   * @param viewHandler seta the viewHandler
   * @param scene sets the scene
   * @param boardGameManager sets the boardGameManager
   */
  public void init(ViewHandler viewHandler, Scene scene,
      BoardGameManager boardGameManager)
  {
    this.viewHandler = viewHandler;
    this.scene = scene;
    this.boardGameManager = boardGameManager;
  }

  /**
   * method for setting the scene
   * @return scene
   */
  public Scene getScene()
  {
    return scene;
  }

  /**
   * method for getting the member
   * @return member
   */
  public Member getMember()
  {
    return member;
  }

  /**
   * method for setting the member
   * @param member Member
   */
  public void setMember(Member member)
  {
    this.member = member;
  }

  /**
   * method for getting the information from text fields
   */
  public void update()
  {
    firstName.setText(member.getFirstName());
    lastName.setText(member.getLastName());
    phone.setText(member.getPhoneNumber());
    email.setText(member.getEmail());
  }

  /**
   * method for clearing the text fields
   */
  public void clear()
  {
    firstName.clear();
    lastName.clear();
    phone.clear();
    email.clear();
  }

  /**
   * methods for button functionality
   * @param e is called when an action happens
   */
  public void actionHandler(ActionEvent e)
  {
    //goin back to the menu
    if (e.getSource() == back)
    {
      viewHandler.openView("Menu");
    }
    //delete and alert for it
    if (e.getSource() == delete)
    {
      Alert alert=null;
      BoardGameList boardGameList=boardGameManager.getAllBoardGames();
      boolean borr=false;
      for (int i = 0; i < boardGameList.size(); i++)
      {
        if (boardGameList.get(i).isBorrowed()&&boardGameList.get(i).getBorrow().getBorrower().equals(member)){
          alert = new Alert(Alert.AlertType.CONFIRMATION,
              "Do you really want to remove this member even though he/she has a game rented?", ButtonType.YES, ButtonType.NO);
          alert.setTitle("Confirmation");
          alert.setHeaderText(null);
          alert.showAndWait();
          borr=true;
          break;
        }
      }
      if (!borr){
        alert = new Alert(Alert.AlertType.CONFIRMATION,
            "Are you sure you want to do this?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("You are killing someone");
        alert.setHeaderText(null);
        alert.showAndWait();
      }
      //you say ...YES please do delete me
      if (alert.getResult() == ButtonType.YES)
      {
        //getting all members from file
        //removing member from a file
        MemberList memberList = boardGameManager.getAllMembers();
        memberList.removeMember(member);
        System.out.println("removing done");
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION,
            "Member removed successfully", ButtonType.OK);
        alert1.setTitle("You ruined someone but whatever");
        alert1.setHeaderText(null);
        alert1.showAndWait();
        //updating of file after deleting
        boardGameManager.saveAllMembers(memberList);
        viewHandler.openView("manageMember");
      }
    }
    //after editing ..button save
    if (e.getSource() == save)
    {

      //getting all members
      //removing selected member
      //creating new member with the edited values
      //adding new member to member list and saving

      MemberList memberList = boardGameManager.getAllMembers();
      memberList.removeMember(member);
      Member member1 = new Member(firstName.getText(), lastName.getText(),
          phone.getText(), email.getText());
      memberList.addMember(member1);
//alert for incorrect number
      if (!(phone.getText().length() == 8))
      {
        Alert wrongPhoneFormat = new Alert(Alert.AlertType.ERROR,
            "This phone number is not legit", ButtonType.OK);
        wrongPhoneFormat.setTitle("stupid");
        wrongPhoneFormat.setHeaderText(null);
        wrongPhoneFormat.showAndWait();
        return;
      }
      for (int i = 0; i < memberList.size(); i++)
      {
        if (phone.getText().equals(memberList.get(i).getPhoneNumber())){
          Alert wrongPhone = new Alert(Alert.AlertType.ERROR,
              "This phone number is already in the system", ButtonType.OK);
          wrongPhone.setTitle("stupid");
          wrongPhone.setHeaderText(null);
          wrongPhone.showAndWait();
          return;
        }
      }
      //alert for incorect email format
      if(!(email.getText().contains("@"))){
        Alert wrongEmailFormat = new Alert(Alert.AlertType.ERROR,
            "This email should look like: name@example.domain", ButtonType.OK);
        wrongEmailFormat.setTitle("stupid");
        wrongEmailFormat.setHeaderText(null);
        wrongEmailFormat.showAndWait();
        return;
      }
      //alert for successful edit
      Alert alert1 = new Alert(Alert.AlertType.INFORMATION,
          "Member edited successfully", ButtonType.OK);
      alert1.setTitle("Good job");
      alert1.setHeaderText(null);
      alert1.showAndWait();
      //saving to file and updating
      boardGameManager.saveAllMembers(memberList);
      viewHandler.getManageMemberController().update();
      viewHandler.openView("manageMember");
      System.out.println("editing done");

    }

  }
}
