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
    if (availability) this.owner=owner;
    else this.owner=null;
    reservationList=null;
    borrow=null;
    rankList=null;
    voteList=new Voting();
  }

  public void setLists(BoardGame previous){
    if (previous.getRankList()!=null)setRankList(previous.getRankList());
    if (previous.getVoteList()!=null) setVoteList(previous.getVoteList());
    if (previous.getReservationList()!=null)setReservationList(previous.getReservationList());
    if (previous.getBorrow()!=null)setBorrow(previous.borrow);
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

  public void createReservationList(){
    reservationList=new ReservationList();
  }
  public void createBorrowList(){
    borrow=new Borrow();
  }

  public boolean rankListExist(){
    return rankList!=null;
  }

  public boolean isAvailable()
  {
    return availability;
  }
  public boolean isBorrowed(){
    return borrow!=null;
  }
  public boolean isReserved(){return reservationList!=null;}

  public boolean contains(String name){
    return toString().contains(name);
  }

  public void setVoteList(Voting voteList)
  {
    this.voteList = voteList;
  }

  public Voting getVoteList()
  {
    return voteList;
  }

  public void setBorrow(Borrow borrow)
  {
    this.borrow = borrow;
  }
  public void setRankList(RankList rankList)
  {
    this.rankList = rankList;
  }

  public void setReservationList(ReservationList reservationList) {
    this.reservationList = reservationList;
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
  public Member getBorrower(){
    return borrow.getBorrower();
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

  public String toString(){
    return name;
  }

}
