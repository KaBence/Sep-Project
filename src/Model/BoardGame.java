package Model;

import java.io.Serializable;

/**
 * It's a class that represents a board game
 */
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


  /**
   * It's a constructor that initializes the variables of the class.
   * @param name name
   * @param availability is or is not available
   * @param maxNoP maximum number of players
   * @param minNoP minimum numebr of players
   * @param type  type of boardGame
   * @param owner owner or borrower of the game
   */
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

  /**
   * This function is used to set the lists of a board game to the lists of another board game
   * @param previous the previous version of the board game
   */
  public void setLists(BoardGame previous){
    if (previous.getRankList()!=null)setRankList(previous.getRankList());
    if (previous.getVoteList()!=null) setVoteList(previous.getVoteList());
    if (previous.getReservationList()!=null)setReservationList(previous.getReservationList());
    if (previous.getBorrow()!=null)setBorrow(previous.borrow);
  }

  /**
   * This function returns the owner of the current object.
   * @return The owner of the group.
   */

  public Member getOwner(){
    return owner;
  }
  /**
   * This function sets the owner of the current object to the owner passed in as a parameter.
   */
  public void setOwner(Member owner)
  {
    this.owner = owner;
  }
  /**
   * This function returns the maximum number of players allowed in the game
   * @return The maxNoP is being returned.
   */
  public int getMaxNoP()
  {
    return maxNoP;
  }
  /**
   * This function returns the minimum number of players for the game
   * @return The minimum number of players.
   */
  public int getMinNoP()
  {
    return minNoP;
  }

  /**
   * This function returns the name of the person.
   * @return The name of the person.
   */

  public String getName()
  {
    return name;
  }
  /**
   * This function sets the name of the object to the value of the parameter.
   * @param name The name of the parameter.
   */
  public void setName(String name)
  {
    this.name = name;
  }
  /**
   * This function sets the type of the object to the type passed in.
   *
   * @param type The type of the object.
   */
  public void setType(String type)
  {
    this.type = type;
  }
  /**
   * This function returns the type of the current object.
   *
   * @return The type of the object.
   */
  public String getType()
  {
    return type;
  }

  /**
   * It creates a new RankList object.
   */
  public void createRankList(){
    rankList=new RankList();
  }

  /**
   * This function creates a new reservation list
   */
  public void createReservationList(){
    reservationList=new ReservationList();
  }
  /**
   * It creates a new instance of the Borrow class.
   */
  public void createBorrowList(){
    borrow=new Borrow();
  }

  /**
   * It checks if the rankList is null or not.
   *
   * @return The method is returning a boolean value.
   */
  public boolean rankListExist(){
    return rankList!=null;
  }

  public boolean isAvailable()
  {
    return availability;
  }
  /**
   * If the borrow is not null, then the book is borrowed.
   *
   * @return The method isBorrowed returns a boolean value.
   */
  public boolean isBorrowed(){
    return borrow!=null;
  }
  /**
   * If the reservation list is not null, then the room is reserved.
   *
   * @return The method isReserved() returns a boolean value.
   */
  public boolean isReserved(){return reservationList!=null;}

  /**
   * // Java
   * public boolean contains(String name){
   *     return toString().contains(name);
   *   }
   *
   * @param name The name of the item you want to check for.
   * @return A boolean value.
   */
  public boolean contains(String name){
    return toString().contains(name);
  }

  /**
   * This function sets the voteList variable to the voteList parameter
   *
   * @param voteList This is the list of votes that will be displayed in the list.
   */
  public void setVoteList(Voting voteList)
  {
    this.voteList = voteList;
  }

  /**
   * This function returns the voteList variable
   *
   * @return The voteList is being returned.
   */
  public Voting getVoteList()
  {
    return voteList;
  }

  /**
   * > This function sets the borrow variable to the borrow object passed in
   *
   * @param borrow The Borrow object that is being created.
   */
  public void setBorrow(Borrow borrow)
  {
    this.borrow = borrow;
  }
  /**
   * This function sets the rankList variable to the rankList parameter
   *
   * @param rankList The list of ranks to be displayed.
   */
  public void setRankList(RankList rankList)
  {
    this.rankList = rankList;
  }

  /**
   * This function sets the value of the reservationList variable to the value of the reservationList parameter
   *
   * @param reservationList The list of reservations that will be displayed in the table.
   */
  public void setReservationList(ReservationList reservationList) {
    this.reservationList = reservationList;
  }

  /**
   * This function returns the vote of the voter
   *
   * @return The number of votes for the candidate.
   */
  public int getVote(){
    return voteList.getVote();
  }
  /**
   * If the rankList exists, return the average of the rankList, otherwise return a blank space
   *
   * @return The average of the rankList.
   */
  public String  getRankValue(){
    if (rankListExist()) return String.valueOf(rankList.getAverage());
    return " ";
  }
  /**
   * This function returns the reservationList
   *
   * @return The reservationList is being returned.
   */
  public ReservationList getReservationList(){
    return reservationList;
  }

  /**
   * This function returns the rankList variable.
   *
   * @return The rankList is being returned.
   */
  public RankList getRankList()
  {
    return rankList;
  }

  /**
   * This function returns the borrow object.
   *
   * @return The borrow object.
   */
  public Borrow getBorrow()
  {
    return borrow;
  }
  /**
   * It returns the borrower of the borrow object.
   *
   * @return The borrower of the book.
   */
  public Member getBorrower(){
    return borrow.getBorrower();
  }

  /**
   * If the object is not null and is of the same class as the current object, then check if the owner is null, if it is,
   * then check if the other object's owner is null, if it is, then check if the other object's type, name, availability,
   * minNoP, and maxNoP are equal to the current object's type, name, availability, minNoP, and maxNoP, if they are, then
   * return true, if not, then return false, if the owner is not null, then check if the other object's type, name,
   * availability, minNoP, and maxNoP are equal to the current object's type, name, availability, minNoP, and maxNoP, and
   * if the other object's owner is equal to the current object's owner, if they are, then return true, if not, then return
   * false
   *
   * @param obj the object to compare to
   * @return The method returns a boolean value.
   */
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

  /**
   * The toString() method returns a string representation of the object
   *
   * @return The name of the object.
   */
  public String toString(){
    return name;
  }

}
