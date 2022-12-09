package GUI;

import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;

/**
 * The controller for the addBoardGame fxl file
 * @author Bence Kabaly
 */

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

  /**
   * A method for setting the parameters
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
   * Returns the addBoardgame scene
   * @return addBoardgame scene
   */

  public Scene getScene()
  {
    return scene;
  }

  /**
   * A method for adding a boardgame to the file with the given parameter
   * @param avl sets the boardGame either available or not available
   */
  public void addBoardGame(boolean avl){
    if (name.getText().isEmpty()||type.getText().isEmpty()||min.getText().isEmpty()||max.getText().isEmpty()||(owner.getValue()==null&& avl)){
      Alert alert=new Alert(Alert.AlertType.WARNING,"Fill every field before adding a boardGame",ButtonType.OK);
      alert.setTitle("Warning");
      alert.setHeaderText(null);
      alert.showAndWait();
      return;
    }
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
    if (Integer.parseInt(min.getText())<0||Integer.parseInt(max.getText())<0){
      Alert alert = new Alert(Alert.AlertType.WARNING,
          "Number of Players cannot be under zero",
          ButtonType.OK);
      alert.setTitle("Warning");
      alert.setHeaderText(null);
      alert.showAndWait();
      return;
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
    if (!avl){
      temp.setOwner(null);
    }
    boardGameList.addBoardGame(temp);
    boardGameManager.saveAllBoardGames(boardGameList);
    clear();
  }

  /**
   * Method for clearing the textFields after adding the boardGame
   */

  public void clear(){
    name.clear();
    type.clear();
    min.clear();
    max.clear();
    owner.getSelectionModel().selectFirst();
  }

  /**
   * Method for updating the combobox with the members
   */

  public void updateComboBox(){
    owner.getItems().clear();
    owner.getItems().add(null);
    MemberList members=boardGameManager.getAllMembers();
    for (int i = 0; i < members.size(); i++)
    {
      owner.getItems().add(members.get(i));
    }

  }

  /**
   * Method for selecting an exact owner from the comboBox
   * @param temp this member gets selected from the comboBox
   */

  public void setOwner(Member temp){
    owner.getSelectionModel().select(temp);
  }

  /**
   * A method for handling the button clicking
   * @param e the event that is called when something happens
   */

  public void actionHandler(ActionEvent e)
  {
    if (e.getSource()==back) viewHandler.openView("Menu");
    if (e.getSource()==addToSystem) addBoardGame(true);
    if (e.getSource()==addForVoting){
      if (owner.getValue()!=null){
        Alert alert=new Alert(Alert.AlertType.ERROR,"No owner is allowed when adding for voting",ButtonType.OK);
        alert.setHeaderText(null);
        alert.setTitle("Warning");
        alert.showAndWait();
        return;
      }
      addBoardGame(false);
    }
  }
}
