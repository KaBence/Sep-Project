package Model;

import java.io.Serializable;

public class Borrow implements Serializable
{
  private Member borrower;
  private MyDate returnDate;
  private MyDate pickUpDate;

  public Borrow(Member borrower, MyDate returnDate, MyDate pickUpDate)
  {
    this.borrower = borrower;
    this.returnDate = returnDate;
    this.pickUpDate=pickUpDate;

  }

  public Borrow(){
    borrower=null;
    returnDate=null;
    pickUpDate=null;
  }

  public void setReturnDate(MyDate returnDate)
  {
    this.returnDate = returnDate;
  }

  public void setBorrower(Member borrower)
  {
    this.borrower = borrower;
  }

  public MyDate getReturnDate()
  {
    return returnDate;
  }

  public Member getBorrower()
  {
    return borrower;
  }

  public MyDate getPickUpDate()
  {
    return pickUpDate;
  }

  public void setPickUpDate(MyDate pickUpDate)
  {
    this.pickUpDate = pickUpDate;
  }

  public boolean equals(Object obj)
  {
    if (obj == null || getClass() != obj.getClass())
    {
      return false;
    }
    Borrow other = (Borrow) obj;
    return borrower.equals(other.borrower) && returnDate.equals(other.returnDate);
  }

  public String toString()
  {
    return "MEMBER:" + borrower + "RETURN DATE" + returnDate + "PICKUP DATE";
  }
}
