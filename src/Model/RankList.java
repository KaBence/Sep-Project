package Model;

import Util.MyFileHandler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;


/**
 * The RankList class is a container for Rank objects
 */
public class RankList implements Serializable
{
  private ArrayList<Rank> ranks;
  // A constructor.
  public RankList(){
    ranks =new ArrayList<>();
  };
  /**
   * This function adds a rank to the ranks array
   *
   * @param rank The rank to add to the list of ranks.
   */
  public void addRank(Rank rank){
    ranks.add(rank);
  }
  /**
   * Remove the rank from the ranks list.
   *
   * @param rank The rank to remove.
   */
  public void removeRank(Rank rank){
    ranks.remove(rank);
  }



  /**
   * This function returns the rank at the given index.
   *
   * @param index The index of the rank you want to get.
   * @return The rank at the given index.
   */
  public Rank get(int index){
    return ranks.get(index);
  }

  /**
   * Return the number of elements in the ranks array.
   *
   * @return The size of the ranks arraylist.
   */
  public int size(){
    return ranks.size();
  }

  /**
   * This function returns the sum of all the ranks in the ranks array
   *
   * @return The sum of all the ranks in the ranks arraylist.
   */
  public double getTotal(){
    int sum=0;
    for(int i=0; i<ranks.size();i++){
     sum+= ranks.get(i).getRank();
    }
    return sum;
  }
  /**
   * It returns the average of the ranks
   *
   * @return The average of the ranks.
   */
  public double getAverage(){
    return getTotal()/ranks.size();
  }
  /**
   * It checks if the two objects are equal.
   *
   * @param obj The object to compare against.
   * @return The return value is a boolean.
   */
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

}
