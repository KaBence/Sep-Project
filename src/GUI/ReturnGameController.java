package GUI;

import Model.BoardGame;
import Model.BoardGameList;
import Model.BoardGameManager;
import Model.Rank;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;

/**
 * class for returning of the game, setting the game from borrowed to available
 *
 * @author Michaela Veselovska
 */
public class ReturnGameController
{
  private ViewHandler viewHandler;
  private BoardGameManager boardGameManager;
  private Scene scene;

  @FXML Button back, returnB, search;
  @FXML TextField textField;
  @FXML RadioButton RBName, RBType, RBNoP, RBBorrowed, RBAvl, RBReserved, RBAllGames;

  @FXML Button cancel, submit;
  @FXML RadioButton RB1, RB2, RB3, RB4, RB5;
  @FXML TextArea feedbackTextArea;
  @FXML Label lblGameName;
  private String SelectedGame;

  /**
   * method to be ablo to connect with other classes
   *
   * @param viewHandler
   * @param scene
   * @param boardGameManager
   */
  public void init(ViewHandler viewHandler, Scene scene,
      BoardGameManager boardGameManager)
  {
    this.viewHandler = viewHandler;
    this.scene = scene;
    this.boardGameManager = boardGameManager;
  }

  /**
   * method for setting the scene
   *
   * @return scene
   */
  public Scene getScene()
  {
    return scene;
  }

  /**
   * The function sets the selected game to the selected game and sets the label to the selected game
   *
   * @param selectedGame The name of the game that was selected.
   */
  public void setSelectedGame(String selectedGame)
  {
    SelectedGame = selectedGame;
  }

  /**
   * method for setting the name of removed board game and rank set on 5 at the beginning
   */
  public void update()
  {

    lblGameName.setText(
        viewHandler.getShowBoardGameController().getShowBoardGame().getName());
    RB1.setSelected(false);
    RB2.setSelected(false);
    RB3.setSelected(false);
    RB4.setSelected(false);
    RB5.setSelected(true);
    feedbackTextArea.clear();
  }

  /**
   * methods for button functionality
   *
   * @param e is called when and action occurs
   */
  public void actionHandler(ActionEvent e)
  {
    //cancel brings you to menu
    if (e.getSource() == cancel)
      viewHandler.openView("Menu");
    //submit
    if (e.getSource() == submit)
    {
      //if there is no review  alert
      if (feedbackTextArea.getText().isEmpty())
      {
        Alert alert = new Alert(Alert.AlertType.WARNING,
            "Please fill every field before submitting", ButtonType.OK);
        alert.setHeaderText(null);
        alert.setTitle("Good Job");
        alert.showAndWait();
        return;
      }
      // setting the selected game
      BoardGame selectedGame = viewHandler.getShowBoardGameController()
          .getShowBoardGame();
      System.out.println(selectedGame.getName());
      // rating selection
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
      // getting the review
      String remarks = feedbackTextArea.getText();
      //creating the rank list
      if (!selectedGame.rankListExist())
      {
        selectedGame.createRankList();
      }
      //adding new rank
      selectedGame.getRankList().addRank(new Rank(remarks, rating));
      //return the game
      selectedGame.setBorrow(null);
      //removing the same game from file
      BoardGameList boardGameList = boardGameManager.getAllBoardGames();
      for (int i = 0; i < boardGameList.size(); i++)
      {
        if (boardGameList.get(i).equals(selectedGame))
        {
          boardGameList.removeBoardGame(boardGameList.get(i));
          break;
        }
      }
      //adding the edited one
      boardGameList.addBoardGame(selectedGame);
      //saving
      boardGameManager.saveAllBoardGames(boardGameList);
      //alert
      Alert alert = new Alert(Alert.AlertType.INFORMATION,
          "Review added successfully", ButtonType.OK);
      alert.setHeaderText(null);
      alert.setTitle("Good Job");
      alert.showAndWait();
      viewHandler.openView("Menu");

    }
  }
}
