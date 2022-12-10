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
   * This function sets the rank of the card to the given rank.
   *
   * @param rank The rank of the card.
   */
  public void setRank(int rank)
  {
    this.rank = rank;
  }

  /**
   * This function returns the rank of the card.
   *
   * @return The rank of the card.
   */
  public int getRank()
  {
    return rank;
  }

  /**
   * This function sets the review of the book
   *
   * @param review The review text
   */
  public void setReview(String review)
  {
    this.review = review;
  }

  /**
   * This function returns the review of the book
   *
   * @return The review is being returned.
   */
  public String getReview()
  {
    return review;
  }

  /**
   * The toString() function returns a string representation of the object
   *
   * @return The rank and review of the movie.
   */
  public String toString()
  {
    return "RANK:" + rank + "REVIEW:" + review;
  }

  /**
   * If the object is not of the same class or is null, return false. Otherwise, return true if the rank and review are
   * equal
   *
   * @param obj The object to compare with.
   * @return The hashcode of the object.
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
