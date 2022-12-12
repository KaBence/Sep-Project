package Model;

import java.io.Serializable;

/**
 * A Borrow object represents a member borrowing a book
 */
public class Borrow implements Serializable
{
  private Member borrower;
  private MyDate returnDate;
  private MyDate pickUpDate;

  /**
   * A constructor that takes in a member, a return date and a pickup date and sets the instance variables to those values.
   */
  public Borrow(Member borrower, MyDate returnDate, MyDate pickUpDate)
  {
    this.borrower = borrower;
    this.returnDate = returnDate;
    this.pickUpDate=pickUpDate;

  }

  /**
   * A no-argument constructor that sets the instance variables to null.
   */
  public Borrow(){
    borrower=null;
    returnDate=null;
    pickUpDate=null;
  }

  /**
   * This function sets the return date from the MyDate class.
   * @param returnDate The date to return the borrowed game.
   */
  public void setReturnDate(MyDate returnDate)
  {
    this.returnDate = returnDate;
  }

  /**
   * This function sets the borrower of the game from the Member class.
   * @param borrower The member who borrowed the game.
   */
  public void setBorrower(Member borrower)
  {
    this.borrower = borrower;
  }

  /**
   * This function checks the date to return the borrowed game from the MyDate class.
   * @return The returnDate variable is being returned.
   */
  public MyDate getReturnDate()
  {
    return returnDate;
  }

  /**
   * This function returns the borrower of the game from the Member class.
   * @return The borrower of the game.
   */
  public Member getBorrower()
  {
    return borrower;
  }

  /**
   * This function returns the pickUpDate from the MyDate class for the borrowing.
   * @return The pickUpDate variable is being returned.
   */
  public MyDate getPickUpDate()
  {
    return pickUpDate;
  }

  /**
   * This function sets the pickUpDate variable to the value of the pickUpDate parameter.
   * @param pickUpDate The date the game was picked up.
   */
  public void setPickUpDate(MyDate pickUpDate)
  {
    this.pickUpDate = pickUpDate;
  }

  /**
   * If the object is not null and is of the same class as the object being compared, then return true if the borrower and
   * return date are the same
   * @param obj The object to compare this Borrow to.
   * @return it returns a boolean.
   */
  public boolean equals(Object obj)
  {
    if (obj == null || getClass() != obj.getClass())
    {
      return false;
    }
    Borrow other = (Borrow) obj;
    return borrower.equals(other.borrower) && returnDate.equals(other.returnDate);
  }

  /**
   * The toString() function returns a string that contains the borrower's name, the return date, and the pickup date
   * @return The borrower and the pickup and return dates.
   */
  public String toString()
  {
    return "MEMBER:" + borrower + "RETURN DATE" + returnDate + "PICKUP DATE";
  }
}
