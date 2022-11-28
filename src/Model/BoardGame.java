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
  public boolean isReserved(){
    return reservationList!=null;
  }



  public boolean equals(Object obj){
    if (obj==null||getClass()!=obj.getClass()) return false;
    BoardGame temp=(BoardGame) obj;
    return type.equals(temp.type)&&name.equals(temp.name)&&availability==temp.availability&&minNoP==temp.minNoP&&maxNoP==temp.maxNoP&&owner.equals(temp.owner)&&reservationList.equals(temp.reservationList)&&borrow.equals(temp.borrow)&&rankList.equals(temp.rankList)&&voteList.equals(temp.voteList);
  }
//toString is not done because we don't know if we need it
  public String toString(){
    return name+" "+type+" "+minNoP+"-"+maxNoP+" "+owner;
  }
}
