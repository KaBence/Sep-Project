package Model;

public class Voting
{
  private int vote;

  public int getVote()
  {
    return vote;
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
    return copy;
  }
}
