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
    if (name.getText().isEmpty()||type.getText().isEmpty()||min.getText().isEmpty()||max.getText().isEmpty()||(owner.getValue()==null&& avl)){//5 checks in the if still O(1)
      Alert alert=new Alert(Alert.AlertType.WARNING,"Fill every field before adding a boardGame",ButtonType.OK);//O(1)
      alert.setTitle("Warning");//O(1)
      alert.setHeaderText(null);//O(1)
      alert.showAndWait();//O(1)
      return;
    }
    try
    {
      Integer.parseInt(min.getText());//O(1)
      Integer.parseInt(max.getText());//O(1)
    }
    catch (NumberFormatException e){
      Alert alert=new Alert(Alert.AlertType.WARNING,"Number of players must be numbers",ButtonType.OK);//O(1)
      alert.setTitle("Warning");//O(1)
      alert.setHeaderText(null);//O(1)
      alert.showAndWait();//O(1)
      return;
    }
    BoardGame temp=new BoardGame(name.getText(),type.getText(), Integer.parseInt(min.getText()),Integer.parseInt(max.getText()),owner.getValue(),avl);//5 checks still O(1)
    BoardGameList boardGameList=boardGameManager.getAllBoardGames();
    for (int i = 0; i < boardGameList.size(); i++)//2n+2
    {
      if (temp.equals(boardGameList.get(i))){//n checks
        Alert alert = new Alert(Alert.AlertType.WARNING,
            "Game already exists in the system",
            ButtonType.OK);//O(1)
        alert.setTitle("Warning");//O(1)
        alert.setHeaderText(null);//O(1)
        alert.showAndWait();//O(1)
        return;
      }
      //If the loop finds a boardgame that is identical with the current one it will show an error ending the loop there,but if the loop doesn't find anything it will run n times.
    }
    if (Integer.parseInt(min.getText())<0||Integer.parseInt(max.getText())<0){// 2 checks
      Alert alert = new Alert(Alert.AlertType.WARNING,
          "Number of Players cannot be under zero",
          ButtonType.OK);//O(1) initialization
      alert.setTitle("Warning");//O(1)
      alert.setHeaderText(null);//O(1)
      alert.showAndWait();//O(1)
      return;
    }
    if (Integer.parseInt(min.getText())>Integer.parseInt(max.getText())){//1 check
      Alert alert = new Alert(Alert.AlertType.WARNING,
          "Minimum number of players cannot be bigger than the maximum number of players",
          ButtonType.OK);// O(1) initialization
      alert.setTitle("Warning");//O(1)
      alert.setHeaderText(null);//O(1)
      alert.showAndWait();//O(1)
      return;
    }
    Alert alert = new Alert(Alert.AlertType.INFORMATION,
        "Game added Successfully",
        ButtonType.OK);//1 initialization
    alert.setTitle("Good Job");//O(1)
    alert.setHeaderText(null);//O(1)
    alert.showAndWait();//O(1)
    if (!avl){//1 check
      temp.setOwner(null);//O(1)
    }
    boardGameList.addBoardGame(temp);//O(1)
    boardGameManager.saveAllBoardGames(boardGameList);
    clear();//O(1)
    //This method has a lot of setting and inicializing that takes O(1), The most complex part of this method is checking if the game already exists in the system. It is checked by a for loop
    //that takes O(n). This whole method because of this takes O(n)
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
