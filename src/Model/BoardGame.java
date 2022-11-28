package Model;

public class BoardGame
{
  private String type,name;
  private boolean availability;
  private int minNoP,maxNoP;
  private Member owner;
  private ReservationList reservationList;
  private Borrow borrow;
  private RankList rankList;
  private VoteList voteList;

  public BoardGame(String name,String type,boolean availability,int minNoP,int maxNoP,Member owner){
    this.name=name;
    this.availability=availability;
    this.type=type;
    this.maxNoP=maxNoP;
    this.minNoP=minNoP;
    this.owner=owner;
    reservationList=new ReservationList();
    borrow=new Borrow();
    rankList=new RankList();
    voteList=new VoteList();
  }

}
