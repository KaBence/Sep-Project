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

  public void initialize()
  {
    TableColMemberFirstName.setCellValueFactory(
        new PropertyValueFactory<Member, String>("firstName"));
    TableColMemberLastName.setCellValueFactory(
        new PropertyValueFactory<Member, String>("lastName"));
    TableColMemberPhone.setCellValueFactory(
        new PropertyValueFactory<Member, String>("phoneNumber"));
    TableColMemberEmail.setCellValueFactory(
        new PropertyValueFactory<Member, String>("email"));

  }

  public void init(ViewHandler viewHandler, Scene scene,
      BoardGameManager boardGameManager)
  {
    this.viewHandler = viewHandler;
    this.scene = scene;
    this.boardGameManager = boardGameManager;

  }

  public void update()
  {
    tableView.getItems().clear();
    MemberList members = boardGameManager.getAllMembers();
    for (int i = 0; i < members.size(); i++)
    {
      tableView.getItems().add(members.get(i));
    }
  }

  public void updateList(MemberList list)
  {
    tableView.getItems().clear();
    memberList = list;
    for (int i = 0; i < memberList.size(); i++)
    {
      tableView.getItems().add(memberList.get(i));
    }
  }

  public Scene getScene()
  {
    return scene;
  }

  public void actionHandler(ActionEvent event)
  {
    MemberList memberList = boardGameManager.getAllMembers();
    if (event.getSource() == back)
    {
      viewHandler.openView("Menu");
    }


    //kinda does work ...:+)..or not
    if (name.isSelected() && event.getSource() == searchButton)
    {
      if (search.getText().isEmpty())
      {
        updateList(memberList);
      }
      else
      {
        MemberList memberList1 = memberList.getMembersByName(search.getText());
        updateList(memberList1);
      }

    }
    if (phone.isSelected() && event.getSource() == searchButton)
    {
      if (search.getText().isEmpty())
      {
        updateList(memberList);
      }
      else
      {
        MemberList memberList2 = memberList.getMembersByPhoneNumber(search.getText());
        updateList(memberList2);
      }

    }
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
  public void tableAction(MouseEvent e)
  {

    Member row = tableView.getSelectionModel().getSelectedItem();
    if (e.getClickCount() == 2 && !(row == null))
    {

      viewHandler.getShowMemberController().setMember(row);
      viewHandler.openView("showMember");
    }
  }
}
