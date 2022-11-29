package Model;

public class Rank
{
  private String review;
  private int rank;

  public Rank(String review, int rank)
  {
    this.rank = rank;
    this.review = review;
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
