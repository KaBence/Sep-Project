package Model;

import java.io.Serializable;

public class Rank implements Serializable
{
  private String review;
  private int rank;

  private String gameName;

  public String getGameName() {
    return gameName;
  }

  public void setGameName(String gameName) {
    this.gameName = gameName;
  }

  public Rank(String review, int rank, String gameName)
  {
    this.rank = rank;
    this.review = review;
    this.gameName = gameName;
  }

  public Rank()
  {
  }

  public void setRank(int rank)
  {
    this.rank = rank;
  }

  public int getRank()
  {
    return rank;
  }

  public void setReview(String review)
  {
    this.review = review;
  }

  public String getReview()
  {
    return review;
  }

  public String toString()
  {
    return "RANK:" + rank + "REVIEW:" + review;
  }

  public boolean equals(Object obj)
  {
    if (obj == null || getClass() != obj.getClass())
    {
      return false;
    }
    Rank other = (Rank) obj;
    return rank==other.rank && review== other.review;
  }
}
