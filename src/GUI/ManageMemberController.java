package GUI;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

/**
 * a class for editing the existing members
 * @author Michaela Veselovska
 */
public class ManageMemberController
{

  @FXML RadioButton name;
  @FXML RadioButton phone;

  @FXML RadioButton email;

  @FXML TextField search;
  @FXML Button searchButton;
  @FXML TableView<Member> tableView;
  @FXML Button back;

  @FXML TableColumn<Member, String> TableColMemberFirstName;
  @FXML TableColumn<Member, String> TableColMemberLastName;
  @FXML TableColumn<Member, String> TableColMemberEmail;
  @FXML TableColumn<Member, String> TableColMemberPhone;

  private ViewHandler viewHandler;
  private BoardGameManager boardGameManager;
  private MemberList memberList;
  private Scene scene;

  /**
   * method that is automatically call when loading
   */
  public void initialize()
  {
    //setting the table
    TableColMemberFirstName.setCellValueFactory(
        new PropertyValueFactory<Member, String>("firstName"));
    TableColMemberLastName.setCellValueFactory(
        new PropertyValueFactory<Member, String>("lastName"));
    TableColMemberPhone.setCellValueFactory(
        new PropertyValueFactory<Member, String>("phoneNumber"));
    TableColMemberEmail.setCellValueFactory(
        new PropertyValueFactory<Member, String>("email"));
    search.clear();
  }

  /**
   * method for setting the scene
   *
   * @param viewHandler sets the viewHandler
   * @param scene sets The scene
   * @param boardGameManager sets the BoardGameManager
   */
  public void init(ViewHandler viewHandler, Scene scene,
      BoardGameManager boardGameManager)
  {
    this.viewHandler = viewHandler;
    this.scene = scene;
    this.boardGameManager = boardGameManager;

  }

  /**
   * method for adding member into list
   */
  public void update()
  {
    tableView.getItems().clear();
    MemberList members = boardGameManager.getAllMembers();
    for (int i = 0; i < members.size(); i++)
    {
      tableView.getItems().add(members.get(i));
    }
  }

  /**
   * method for adding a member with the given parameter
   *
   * @param list of members
   */
  public void updateList(MemberList list)
  {
    tableView.getItems().clear();
    memberList = list;
    for (int i = 0; i < memberList.size(); i++)
    {
      tableView.getItems().add(memberList.get(i));
    }
  }

  /**
   * setting the scene
   *
   * @return scene
   */
  public Scene getScene()
  {
    return scene;
  }

  /**
   * methods for functionality of the buttons
   *
   * @param event is called when something happens
   *              radioButtons for selection
   */
  public void actionHandler(ActionEvent event)
  {
    //create a list of all games
    MemberList memberList = boardGameManager.getAllMembers();
    //return to menu
    if (event.getSource() == back)
    {
      viewHandler.openView("Menu");
    }

    //if nothing is selected all games should show
    if (name.isSelected() && event.getSource() == searchButton)
    {
      if (search.getText().isEmpty())
      {
        updateList(memberList);
      }
      else
      {
        //searching by name
        MemberList memberList1 = memberList.getMembersByName(search.getText());
        updateList(memberList1);
      }

    }
    //searching by phone
    if (phone.isSelected() && event.getSource() == searchButton)
    {
      if (search.getText().isEmpty())
      {
        updateList(memberList);
      }
      else
      {
        MemberList memberList2 = memberList.getMembersByPhoneNumber(
            search.getText());
        updateList(memberList2);
      }

    }
    //searching by email
    if (email.isSelected() && event.getSource() == searchButton)
    {
      if (search.getText().isEmpty())
      {
        updateList(memberList);
      }
      else
      {
        MemberList memberList3 = memberList.getMembersByEmail(search.getText());
        updateList(memberList3);

      }

    }
  }

  /**
   * method for changing the scenes on double click
   *
   * @param e is called when an action happens
   */
  public void tableAction(MouseEvent e)
  {
    //selecting a row and opening show member
    Member row = tableView.getSelectionModel().getSelectedItem();
    if (e.getClickCount() == 2 && !(row == null))
    {

      viewHandler.getShowMemberController().setMember(row);
      viewHandler.openView("showMember");
    }
  }
}
