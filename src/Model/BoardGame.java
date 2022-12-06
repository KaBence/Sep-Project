package Model;

import java.io.Serializable;

public class BoardGame implements Serializable
{
  private String type,name;
  private boolean availability;
  private int minNoP,maxNoP;
  private Member owner;
  private ReservationList reservationList;
  private Borrow borrow;
  private RankList rankList;
  private Voting voteList;

  public BoardGame(String name,String type,int minNoP,int maxNoP,Member owner,boolean availability){
    this.name=name;
    this.availability=availability;
    this.type=type;
    this.maxNoP=maxNoP;
    this.minNoP=minNoP;
    this.owner=owner;
    reservationList=null;
    borrow=null;
    rankList=null;
    voteList=new Voting();
  }

  public Member getOwner(){
    return owner;
  }
  public void setOwner(Member owner)
  {
    this.owner = owner;
  }
  public int getMaxNoP()
  {
    return maxNoP;
  }
  public int getMinNoP()
  {
    return minNoP;
  }
  public void setMaxNoP(int maxNoP) {
    this.maxNoP = maxNoP;
  }
  public void setMinNoP(int minNoP) {
    this.minNoP = minNoP;
  }
  public String getName()
  {
    return name;
  }
  public void setName(String name)
  {
    this.name = name;
  }
  public void setType(String type)
  {
    this.type = type;
  }
  public String getType()
  {
    return type;
  }

  public void createRankList(){
    rankList=new RankList();
  }
  public void setAvailability(boolean availability) {
    this.availability = availability;
  }
  public boolean isAvailable()
  {
    return availability;
  }
  public boolean isBorrowed(){
    return borrow!=null;
  }
  public boolean isReserved(){return reservationList!=null;}

  public Voting getVoteList()
  {
    return voteList;
  }
  public int getVote(){
    return voteList.getVote();
  }
  public ReservationList getReservationList(){
    return reservationList;
  }

  public RankList getRankList()
  {
    return rankList;
  }

  public Borrow getBorrow()
  {
    return borrow;
  }

  public boolean equals(Object obj){
    if (obj==null||getClass()!=obj.getClass()) return false;
    BoardGame temp=(BoardGame) obj;
    if (owner != null)
    {
      return type.equals(temp.type)&&name.equals(temp.name)&&availability==temp.availability&&minNoP==temp.minNoP&&maxNoP==temp.maxNoP&&owner.equals(temp.owner);
    }
    else{
      return type.equals(temp.type)&&name.equals(temp.name)&&availability==temp.availability&&minNoP==temp.minNoP&&maxNoP==temp.maxNoP&&temp.owner==null;


    }
  }
//toString is not done because we don't know if we need it
  public String toString(){
    return name+" "+type+" "+minNoP+"-"+maxNoP+" "+owner;
  }
}
