package Model;

import java.util.ArrayList;

public class RankList
{
  private ArrayList<Rank> ranks;
  public RankList(){
    ranks =new ArrayList<>();
  };
  public void addRank(Rank rank){
    ranks.add(rank);
  }
  public void removeRank(int index){
    ranks.remove(index);
  }


  int counter=0;
  public int getTotal(int index){

    for(int i=0; i<ranks.size();i++){
      ranks.get(index).getRank();
      counter+=counter;
    }
 return counter;
  }
  public double getAverage(){
    return counter/ranks.size();
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
}
