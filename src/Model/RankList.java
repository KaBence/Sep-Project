package Model;

import Util.MyFileHandler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class RankList implements Serializable
{
  private ArrayList<Rank> ranks;
  public RankList(){
    ranks =new ArrayList<>();
  };
  public void addRank(Rank rank){
    ranks.add(rank);
  }
  public void removeRank(Rank rank){
    ranks.remove(rank);
  }
  public Rank showRank(int index){
    return ranks.get(index);
  }



  public int getTotal(){
    int sum=0;
    for(int i=0; i<ranks.size();i++){
     sum+= ranks.get(i).getRank();

    }
 return sum;
  }
  public double getAverage(){
    return getTotal()/ranks.size();
  }
  public boolean equals(Object obj)
  {
    if (obj == null || getClass() != obj.getClass())
    {
      return false;
    }
    RankList other= (RankList) obj;
    for(int i =0; i<ranks.size();i++){
      if(!(ranks.get(i).equals(other))){
        return false;
      }
    }
    return true;
  }

    public static RankList getAllRanks(){
      RankList myRankList = new RankList();

        try
        {
            myRankList = (RankList) MyFileHandler.readFromBinaryFile("rank.bin");
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        catch (IOException e){
            System.out.println("IO error when opening the file");
        }
        catch (ClassNotFoundException e){
            System.out.println("Wrong class in file");
        }
        return myRankList;
    }

}
