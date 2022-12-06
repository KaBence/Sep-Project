package GUI;

import Model.BoardGameManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ReturnGameController
{
  private ViewHandler viewHandler;
  private BoardGameManager boardGameManager;
  private Scene scene;

  @FXML Button back,returnB,search;
  @FXML TextField textField;
  @FXML RadioButton RBName,RBType,RBNoP,RBBorrowed,RBAvl,RBReserved,RBAllGames;

  @FXML Button cancel,submit;
  @FXML RadioButton RB1,RB2,RB3,RB4,RB5;
  @FXML TextArea feedbackTextArea;
  @FXML Label lblGameName;
private String SelectedGame;
  public void init(ViewHandler viewHandler, Scene scene, BoardGameManager boardGameManager)
  {
    this.viewHandler = viewHandler;
    this.scene = scene;
    this.boardGameManager = boardGameManager;
  }

  public Scene getScene(){
    return scene;
  }

  /**
   * The function sets the selected game to the selected game and sets the label to the selected game
   *
   * @param selectedGame The name of the game that was selected.
   */
  public void setSelectedGame(String selectedGame){
    SelectedGame = selectedGame;
    lblGameName.setText(selectedGame);
  }

  public void actionHandler(ActionEvent e)
  {
    if (e.getSource() == cancel)
      viewHandler.openView("Menu");
    if (e.getSource() == submit)
    {

      BoardGame selectedGame = viewHandler.getShowBoardGameController().getShowBoardGame();
      System.out.println(selectedGame.getName());

      int rating = 0;
      if (RB1.isSelected())
      {
        rating = 1;
      }
      else if (RB2.isSelected())
      {
        rating = 2;
      }
      else if (RB3.isSelected())
      {
        rating = 3;
      }
      else if (RB4.isSelected())
      {
        rating = 4;
      }
      else if (RB5.isSelected())
      {
        rating = 5;
      }

      String remarks = feedbackTextArea.getText();
      if (!selectedGame.isReserved())
      {
        selectedGame.createRankList();
      }
      selectedGame.getRankList().addRank(new Rank(remarks, rating));
      BoardGameList boardGameList=boardGameManager.getAllBoardGames();
      for (int i = 0; i < boardGameList.size(); i++)
      {
        if (boardGameList.get(i).equals(selectedGame)){
          boardGameList.removeBoardGame(boardGameList.get(i));
          break;
        }
      }
      boardGameList.addBoardGame(selectedGame);
      boardGameManager.saveAllBoardGames(boardGameList);
      Alert alert=new Alert(Alert.AlertType.INFORMATION,"Review added successfully",ButtonType.OK);
      alert.setHeaderText(null);
      alert.setTitle("Good Job");
      alert.showAndWait();
      viewHandler.openView("Menu");

    }
  }
}
