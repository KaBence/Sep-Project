package Model;

import java.io.Serializable;


/**
 * The Rank class is a simple class that contains a review and a rank
 */
public class Rank implements Serializable
{
  private String review;
  private int rank;


  /**
   * A constructor that takes in a review and a rank and sets the rank and review of the object to the given rank and review.
   */

  public Rank(String review, int rank)
  {
    this.rank = rank;
    this.review = review;
  }

  /**
   * A default constructor.
   */
  public Rank()
  {
  }

  /**
   * This function sets the rank to the given rank.
   * @param rank The rank of the game.
   */
  public void setRank(int rank)
  {
    this.rank = rank;
  }

  /**
   * This function returns the rank of the game.
   * @return The rank of the game.
   */
  public int getRank()
  {
    return rank;
  }

  /**
   * This function sets the review of the game.
   * @param review The review of the game.
   */
  public void setReview(String review)
  {
    this.review = review;
  }

  /**
   * This function returns the review of the game.
   * @return The review is being returned.
   */
  public String getReview()
  {
    return review;
  }

  /**
   * The toString() function returns a string representation of the object
   * @return The rank and review of the game.
   */
  public String toString()
  {
    return "RANK:" + rank + "REVIEW:" + review;
  }

  /**
   * This method checks whether provided parameter is equals to rank or not.
   * @param obj The object to compare with.
   * @return true if obj is equals to the current rank otherwise false.
   */
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
