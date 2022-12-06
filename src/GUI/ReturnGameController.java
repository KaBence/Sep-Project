package GUI;

import Model.*;
import Util.MyFileHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ReturnGameController
{
  private ViewHandler viewHandler;
  private BoardGameManager clubmanager;
  private Scene scene;

  @FXML Button back,returnB,search;
  @FXML TextField textField;
  @FXML RadioButton RBName,RBType,RBNoP,RBBorrowed,RBAvl,RBReserved,RBAllGames;

  @FXML Button cancel,submit;
  @FXML RadioButton RB1,RB2,RB3,RB4,RB5;
  @FXML TextArea ratingTextArea;
  @FXML Label lblGameName;
private String SelectedGame;
  public void init(ViewHandler viewHandler, Scene scene, BoardGameManager clubManager)
  {
    this.viewHandler = viewHandler;
    this.scene = scene;
    this.clubmanager = clubmanager;
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

  public void actionHandler(ActionEvent e){
    if (e.getSource()==cancel) viewHandler.openView("Menu");
    if(e.getSource() == submit)
      {

        BoardGame selctedGame = viewHandler.getShowBoardGameController().getShowBoardGame();
        System.out.println(selctedGame.getName());

        try {
          int rating = 0;
          if (RB1.isSelected()) {
            rating = 1;
          } else if (RB2.isSelected()) {
            rating = 2;
          } else if (RB3.isSelected()) {
            rating = 3;
          } else if (RB4.isSelected()) {
            rating = 4;
          } else if (RB5.isSelected()) {
            rating = 5;
          }

          String remarks = ratingTextArea.getText();
          Rank myRank = new Rank(remarks, rating, selctedGame.getName());
          RankList oldRankList = RankList.getAllRanks();
          oldRankList.addRank(myRank);
          MyFileHandler.writeToBinaryFile("rank.bin", oldRankList);
          System.out.println(oldRankList.getTotal());
        } catch (FileNotFoundException ex) {
          System.out.println("Error opening file ");
        } catch (IOException ex) {
          System.out.println("IO Error writing to file ");
        }

        viewHandler.openView("Menu");

      }

  }

}
