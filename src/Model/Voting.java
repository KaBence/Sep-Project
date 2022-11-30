package Model;

import java.io.Serializable;

public class Voting implements Serializable
{
  private int vote;

  public int getVote()
  {
    return vote;
  }

  public void setVote(int vote)
  {
    this.vote = vote;
  }

  public void addVote()
  {
    vote++;
  }

  public void removeVote()
  {
    vote--;
  }

  public String toString()
  {
    return "" + vote;
  }

  public Voting copy(){
    Voting copy = new Voting();
    copy.setVote(vote);
    return copy;
  }
}
