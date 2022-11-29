package GUI;

import Model.BoardGameClub;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class MenuController
{
  private ViewHandler viewHandler;
  private BoardGameClub clubManager;
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
  public void init(ViewHandler viewHandler, Scene scene, BoardGameClub clubManager)
  {
    this.viewHandler = viewHandler;
    this.scene = scene;
    this.clubManager = clubManager;
  }

  public Scene getScene(){
    return scene;
  }

  public void actionHandler(ActionEvent e){
    if (e.getSource()==addBoardGame) viewHandler.openView("addBoardGame");
    if (e.getSource()==manageBoardGame) viewHandler.openView("manageBoardGame");
    if (e.getSource()==addEvent) viewHandler.openView("addEvent");
    if (e.getSource()==manageEvent) viewHandler.openView("manageEvent");
    if (e.getSource()==addMember) viewHandler.openView("addMember");
    if (e.getSource()==manageMember) viewHandler.openView("manageMember");
    if (e.getSource()==vote) viewHandler.openView("vote");
    if (e.getSource()==reservation) viewHandler.openView("reservation");
    if (e.getSource()==borrow) viewHandler.openView("borrow");
    if (e.getSource()==returnGame) viewHandler.openView("returnGame");
  }
}
