package GUI;

import Model.BoardGameManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class MenuController
{
  private int value;
  private ViewHandler viewHandler;
  private BoardGameManager clubManager;
  private Scene scene;

  @FXML Button addBoardGame;
  @FXML Button manageBoardGame;
  @FXML Button addMember;
  @FXML Button manageMember;
  @FXML Button addEvent;
  @FXML Button manageEvent;
  @FXML Button vote;
  @FXML Button reservation;
  @FXML Button borrow;
  @FXML Button returnGame;
  public void init(ViewHandler viewHandler, Scene scene, BoardGameManager clubManager)
  {
    this.viewHandler = viewHandler;
    this.scene = scene;
    this.clubManager = clubManager;
  }

  public Scene getScene(){
    return scene;
  }

  public int getValue()
  {
    return value;
  }
  public void setValue(int value)
  {
    this.value = value;
  }

  public void actionHandler(ActionEvent e){
    if (e.getSource()==addBoardGame) {
      viewHandler.openView("addBoardGame");
      viewHandler.getAddBoardGameController().updateComboBox();
    }
    if (e.getSource()==manageBoardGame){
      viewHandler.getManageBoardGamesController().initialize();
      viewHandler.getManageBoardGamesController().setAllGames(true);
      viewHandler.openView("manageBoardGame");
      value=0;
    }
    if (e.getSource()==addEvent) viewHandler.openView("addEvent");
    if (e.getSource()==manageEvent) viewHandler.openView("manageEvent");
    if (e.getSource()==addMember){
      viewHandler.getAddMemberController().setCheck(false);
      viewHandler.getAddMemberController().clear();
      viewHandler.openView("addMember");
    }
    if (e.getSource()==manageMember) viewHandler.openView("manageMember");
    if (e.getSource()==vote) {
      viewHandler.openView("vote");
      viewHandler.getVoteController().update();
    }
    if (e.getSource()==reservation) {
      viewHandler.openView("manageBoardGame");
      value=1;
      viewHandler.getManageBoardGamesController().setAvailable(true);
      viewHandler.getManageBoardGamesController().sorting();
    }
    if (e.getSource()==borrow) {
      viewHandler.openView("manageBoardGame");
      value=2;
      viewHandler.getManageBoardGamesController().setAvailable(true);
      viewHandler.getManageBoardGamesController().sorting();

    }
    if (e.getSource()==returnGame) {
      viewHandler.openView("manageBoardGame");
      value=3;
      viewHandler.getManageBoardGamesController().setBorrowed(true);
      viewHandler.getManageBoardGamesController().sorting();
    }
  }
}
