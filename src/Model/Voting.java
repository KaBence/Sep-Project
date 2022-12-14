package Model;

import java.io.Serializable;


/**
 * The class Voting is a serializable class for vote.
 */
public class Voting implements Serializable
{
  private int vote;

  /**
   * This is a default constructor.
   */
  public Voting(){
    vote=0;
  }

  /**
   * This function returns the value of the vote variable.
   * @return The value of the variable vote.
   */
  public int getVote()
  {
    return vote;
  }

  /**
   * This function sets the vote variable to the value of the vote parameter.
   * @param vote The number of votes the member has.
   */
  public void setVote(int vote)
  {
    this.vote = vote;
  }

  /**
   * This function adds one to the vote variable.
   */
  public void addVote()
  {
    vote++;
  }

  /**
   * This function removes a vote from the vote count.
   */
  public void removeVote()
  {
    vote--;
  }

  /**
   * This function returns a string representation of the vote.
   * @return The vote.
   */
  public String toString()
  {
    return "" + vote;
  }


}
