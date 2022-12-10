package GUI;

import Model.BoardGame;
import Model.BoardGameList;
import Model.BoardGameManager;
import Model.MemberList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;

/**
 * A controller class for the showBoardGame fxml file
 * @author Bence Kabaly
 */
public class ShowBoardGameController
{
  @FXML TextField nameOfGame;
  @FXML TextField min;
  @FXML TextField max;
  @FXML TextField typeOfBoardGame;
  @FXML TextField avlField;
  @FXML TextField ownerField;
  @FXML ComboBox owner;
  @FXML RadioButton available2, nonAvailable2;
  @FXML Button edit;
  @FXML Button remove;
  @FXML Button seeReviews;
  @FXML Button back;

  private ViewHandler viewHandler;
  private BoardGameManager boardGameManager;
  private Scene scene;
  private BoardGame showBoardGame;

  /**
   * A method for setting the parameters
   * @param viewHandler sets the viewHandler
   * @param scene sets The scene
   * @param boardGameManager sets the BoardGameManager
   */


  public void init(ViewHandler viewHandler, Scene scene, BoardGameManager boardGameManager)
  {
    this.viewHandler = viewHandler;
    this.scene = scene;
    this.boardGameManager = boardGameManager;
  }

  /**
   * Returns the controllers scene
   * @return scene
   */

  public Scene getScene(){
    return scene;
  }

  /**
   * Returns the BoardGame object that is shown in this window
   * @return BoardGame object
   */

  public BoardGame getShowBoardGame()
  {
    return showBoardGame;
  }

  /**
   * sets the BoardGame object that is shown in this window
   * @param showBoardGame This object gets set
   */
  public void setShowBoardGame(BoardGame showBoardGame)
  {
    this.showBoardGame = showBoardGame;
  }

  /**
   * After everything is loaded properlu this method is automaticallu called and sets the field to not editable
   */

  public void initialize(){
    nameOfGame.setEditable(false);
    owner.setVisible(false);
    typeOfBoardGame.setEditable(false);
    min.setEditable(false);
    max.setEditable(false);
    avlField.setEditable(false);
    available2.setVisible(false);
    nonAvailable2.setVisible(false);
    ownerField.setEditable(false);
    ownerField.setVisible(true);
    edit.setText("Edit");
  }

  /**
   * Sets the textfield's values from the selectedBoardGame object
   */

  public void update(){
    clear();
    owner.getItems().add("Nobody");
    owner.getSelectionModel().selectFirst();
    nameOfGame.setText(showBoardGame.getName());
    typeOfBoardGame.setText(showBoardGame.getType());
    min.setText(String.valueOf(showBoardGame.getMinNoP()));
    max.setText(String.valueOf(showBoardGame.getMaxNoP()));
    int temp=0;
    MemberList members=boardGameManager.getAllMembers();
    for (int i = 0; i < members.size(); i++)
    {
      if (showBoardGame.getOwner()!=null&&showBoardGame.getOwner().equals(members.get(i)))temp=i;
      owner.getItems().add(members.get(i));
    }
    if (showBoardGame.isAvailable()) ownerField.setText(showBoardGame.getOwner().toString());
    if (showBoardGame.isAvailable()){
      avlField.setText("Available");
    }
    if (showBoardGame.isReserved()){
      avlField.setText("Reserved");
    }
    if (showBoardGame.isBorrowed()){
      avlField.setText("Borrowed until "+showBoardGame.getBorrow().getReturnDate()+" in possession of "+showBoardGame.getBorrow().getBorrower());
    }
    if (!showBoardGame.isAvailable()){
      avlField.setText("Not Available");
    }
  }

  /**
   * Clears the textField's values
   */

  public void clear(){
    owner.getItems().clear();
    nameOfGame.clear();
    typeOfBoardGame.clear();
    min.clear();
    max.clear();
    ownerField.clear();
  }

  /**
   * A method for handling the actions with the buttons
   * @param e  the event that is called when something happens
   */

  public void actionHandler(ActionEvent e){
    if (e.getSource()==back){
      viewHandler.getManageBoardGamesController().initialize();
      initialize();
      clear();
      viewHandler.openView("manageBoardGame");
    }
    //Shows the selected boardGame reviews if the game is available
    if (e.getSource()==seeReviews){
      if (!showBoardGame.isAvailable()){
        Alert alert=new Alert(Alert.AlertType.ERROR,"You can see reviews of an available Game",ButtonType.OK);
        alert.setHeaderText(null);
        alert.setTitle("Warning");
        alert.showAndWait();
        return;
      }
      viewHandler.getSeeReviewController().setSelectedBoardGame(showBoardGame);
      viewHandler.getSeeReviewController().update();
      viewHandler.openView("seeReviews");
    }
    if (e.getSource()==remove){
      Alert alert;
      if (showBoardGame.isBorrowed()){
        alert = new Alert(Alert.AlertType.WARNING, "Do you really want to remove this game from the system even though the game is borrowed?", ButtonType.YES,ButtonType.NO);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.showAndWait();
      }
      else {
        alert = new Alert(Alert.AlertType.WARNING, "Do you really want to remove this game from the system?", ButtonType.YES,ButtonType.NO);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.showAndWait();
      }
      if (alert.getResult()==ButtonType.YES){
        BoardGameList temp=boardGameManager.getAllBoardGames();
        temp.removeBoardGame(showBoardGame);
        boardGameManager.saveAllBoardGames(temp);
        viewHandler.getManageBoardGamesController().initialize();
        viewHandler.openView("manageBoardGame");
      }
    }
    //if the edit button's text is edit it sets all textField to editable and when the text is save it actuallu save's the changes to file
    if (e.getSource()==edit){
      boolean temp=false;
      if (edit.getText().equals("Save"))temp=true;
      if (!temp){
        nameOfGame.setEditable(true);
        ownerField.setVisible(false);
        owner.setVisible(true);
        typeOfBoardGame.setEditable(true);
        min.setEditable(true);
        max.setEditable(true);
        available2.setVisible(true);
        nonAvailable2.setVisible(true);
        edit.setText("Save");
      }
      if (temp){
        try
        {
          Integer.parseInt(min.getText());
          Integer.parseInt(max.getText());
        }
        catch (NumberFormatException d){
          Alert alert=new Alert(Alert.AlertType.WARNING,"Number of players must be numbers",ButtonType.OK);
          alert.setTitle("Warning");
          alert.setHeaderText(null);
          alert.showAndWait();
          return;
        }
        boolean radioAvl=false;
        if (available2.isSelected())radioAvl=true;
        MemberList memberList=boardGameManager.getAllMembers();
        BoardGame edited=new BoardGame(nameOfGame.getText(),typeOfBoardGame.getText(),Integer.parseInt(min.getText()),Integer.parseInt(max.getText()),memberList.getMemberByName(owner.getValue().toString()),radioAvl);
        edited.setLists(showBoardGame);
        BoardGameList boardGameList=boardGameManager.getAllBoardGames();
        for (int i = 0; i < boardGameList.size(); i++)
        {
          if (boardGameList.get(i).equals(showBoardGame)){
            boardGameList.removeBoardGame(boardGameList.get(i));
            break;
          }
        }
        if (available2.isSelected()&&owner.getValue().toString().equals("Nobody")){
          Alert error=new Alert(Alert.AlertType.ERROR,"If the game should be available, It has to have an owner",ButtonType.OK);
          error.setTitle("Warning");
          error.setHeaderText(null);
          error.showAndWait();
          return;
        }
        if (nonAvailable2.isSelected()&&!owner.getValue().toString().equals("Nobody")){
          Alert error=new Alert(Alert.AlertType.ERROR,"If the game should be non-available, The owner has to be Nobody",ButtonType.OK);
          error.setTitle("Warning");
          error.setHeaderText(null);
          error.showAndWait();
          return;
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
          Alert error = new Alert(Alert.AlertType.ERROR,
              "Minimum number of players cannot be bigger than the maximum number of players",
              ButtonType.OK);
          error.setTitle("Warning");
          error.setHeaderText(null);
          error.showAndWait();
          return;
        }
        boardGameList.addBoardGame(edited);
        boardGameManager.saveAllBoardGames(boardGameList);
        viewHandler.getManageBoardGamesController().update(boardGameList);
        Alert alert=new Alert(Alert.AlertType.INFORMATION,"Edit Successful",ButtonType.OK);
        alert.setTitle("Good Job");
        alert.setHeaderText(null);
        alert.showAndWait();
        initialize();
        edit.setText("Edit");

        viewHandler.openView("manageBoardGame");

      }
    }

  }
}
