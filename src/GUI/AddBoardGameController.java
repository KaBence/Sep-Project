package GUI;

import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;

public class AddBoardGameController
{
  @FXML TextField name;
  @FXML TextField min;
  @FXML TextField max;
  @FXML TextField type;
  @FXML ComboBox<Member> owner;
  @FXML Button back;
  @FXML Button addForVoting;
  @FXML Button addToSystem;
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

  public void addBoardGame(boolean avl){
    BoardGame temp=new BoardGame(name.getText(),type.getText(), Integer.parseInt(min.getText()),Integer.parseInt(max.getText()),owner.getValue(),avl);
    BoardGameList boardGameList=boardGameManager.getAllBoardGames();
    for (int i = 0; i < boardGameList.size(); i++)
    {
      if (temp.equals(boardGameList.get(i))){
        Alert alert = new Alert(Alert.AlertType.WARNING,
            "Game already exists in the system",
            ButtonType.OK);
        alert.setTitle("Warning");
        alert.setHeaderText(null);

        alert.showAndWait();
        return;
      }
    }
    if (Integer.parseInt(min.getText())>Integer.parseInt(max.getText())){
      Alert alert = new Alert(Alert.AlertType.WARNING,
          "Minimum number of players cannot be bigger than the maximum number of players",
          ButtonType.OK);
      alert.setTitle("Warning");
      alert.setHeaderText(null);

      alert.showAndWait();
      return;
    }
    Alert alert = new Alert(Alert.AlertType.INFORMATION,
        "Game added Successfully",
        ButtonType.OK);
    alert.setTitle("Good Job");
    alert.setHeaderText(null);
    alert.showAndWait();
    boardGameList.addBoardGame(temp);
    boardGameManager.saveAllBoardGames(boardGameList);
    clear();
  }

  public void clear(){
    name.clear();
    type.clear();
    min.clear();
    max.clear();
  }

  public void updateComboBox(){
    MemberList members=boardGameManager.getAllMembers();
    for (int i = 0; i < members.size(); i++)
    {
      owner.getItems().add(members.get(i));
    }
  }

  public void actionHandler(ActionEvent e)
  {
    if (e.getSource()==back) viewHandler.openView("Menu");
    if (e.getSource()==addToSystem) addBoardGame(true);
    if (e.getSource()==addForVoting) addBoardGame(false);
  }
}
