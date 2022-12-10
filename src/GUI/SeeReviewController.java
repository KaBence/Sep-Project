package GUI;

import Model.BoardGame;
import Model.BoardGameList;
import Model.BoardGameManager;
import Model.Rank;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
/**
 * A class for seeing the reviews of a game
 * @author It's a secret
 */
public class SeeReviewController
{
  @FXML TextField nameOfBoardGame,rank;
  @FXML TableView<Rank> reviews;
  @FXML TableColumn<Rank,String> tableText;
  @FXML Button back,remove;
  private ViewHandler viewHandler;
  private BoardGameManager boardGameManager;
  private Scene scene;
  private BoardGame selectedBoardGame;
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
   * A method for initializing the data
   */
  public void initialize(){
    nameOfBoardGame.setEditable(false);
    rank.setEditable(false);

  }
  /**
   * A method updating the windows and fields
   */
  public void update(){
    reviews.getItems().clear();
    if (selectedBoardGame.getRankList()==null) tableText.setCellValueFactory(null);
    else tableText.setCellValueFactory(new PropertyValueFactory<Rank,String>("review"));
    if (selectedBoardGame.getRankList()==null){
      rank.setText(String.valueOf(0));
    }
    else {
      for (int i = 0; i < selectedBoardGame.getRankList().size(); i++)
      {
        reviews.getItems().add(selectedBoardGame.getRankList().get(i));
        rank.setText(String.valueOf(selectedBoardGame.getRankList().getAverage()));
      }
    }
    nameOfBoardGame.setText(selectedBoardGame.getName());
  }

  /**
   * A method to select a game
   * @param selectedBoardGame the selected game
   */
  public void setSelectedBoardGame(BoardGame selectedBoardGame) {
    this.selectedBoardGame = selectedBoardGame;
  }
  /**
   * Returns the seeReview scene
   * @return seeReview scene
   */
  public Scene getScene(){
    return scene;
  }
  /**
   * A method for handling the button clicking
   * @param e the event that is called when something happens
   */
  public void actionHandler(ActionEvent e){
    if (e.getSource()==back) viewHandler.openView("manageBoardGame");
    if (e.getSource()==remove){
      if (reviews.getSelectionModel().getSelectedItem()==null){
        Alert alert=new Alert(Alert.AlertType.ERROR,"Select a Rank first",ButtonType.OK);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.showAndWait();
        return;
      }
      Rank selected=reviews.getSelectionModel().getSelectedItem();
      selectedBoardGame.getRankList().removeRank(selected);
      BoardGameList boardGameList=boardGameManager.getAllBoardGames();
      for (int i = 0; i < boardGameList.size(); i++)
      {
        if (boardGameList.get(i).equals(selectedBoardGame)){
          boardGameList.removeBoardGame(boardGameList.get(i));
          break;
        }
      }
      boardGameList.addBoardGame(selectedBoardGame);
      boardGameManager.saveAllBoardGames(boardGameList);

      Alert alert=new Alert(Alert.AlertType.INFORMATION,"Remove Successful",ButtonType.OK);
      alert.setTitle("Information");
      alert.setHeaderText(null);
      alert.showAndWait();
      update();
    }
  }
}
