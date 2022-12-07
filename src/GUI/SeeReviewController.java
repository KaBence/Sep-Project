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
  public void init(ViewHandler viewHandler, Scene scene, BoardGameManager boardGameManager)
  {
    this.viewHandler = viewHandler;
    this.scene = scene;
    this.boardGameManager = boardGameManager;
  }

  public void initialize(){
    nameOfBoardGame.setEditable(false);
    rank.setEditable(false);

  }

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


  public void setSelectedBoardGame(BoardGame selectedBoardGame) {
    this.selectedBoardGame = selectedBoardGame;
  }

  public Scene getScene(){
    return scene;
  }

  public void actionHandler(ActionEvent e){
    if (e.getSource()==back) viewHandler.openView("manageBoardGame");
    if (e.getSource()==remove){
      //remove doesn't work yet no idea whats his problem.....
      if (reviews.getSelectionModel().getSelectedItem()==null){
        Alert alert=new Alert(Alert.AlertType.ERROR,"Select a Rank first",ButtonType.OK);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.showAndWait();
        return;
      }
      Rank selected=reviews.getSelectionModel().getSelectedItem();
      BoardGameList boardGameList=boardGameManager.getAllBoardGames();
      for (int i = 0; i < boardGameList.size(); i++)
      {
        if (boardGameList.get(i).equals(selectedBoardGame)){
          boardGameList.get(i).getRankList().removeRank(selected);
          selectedBoardGame=boardGameList.get(i);
          System.out.println("test");
          break;
        }
      }
      boardGameManager.saveAllBoardGames(boardGameList);

      Alert alert=new Alert(Alert.AlertType.INFORMATION,"Remove Successful",ButtonType.OK);
      alert.setTitle("Information");
      alert.setHeaderText(null);
      alert.showAndWait();
      update();
    }
  }
}
