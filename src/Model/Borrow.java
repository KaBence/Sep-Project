package Model;

import java.io.Serializable;

public class Borrow implements Serializable
{
  private Member borrower;
  private MyDate returnDate;

  public Borrow(Member borrower, MyDate returnDate)
  {
    this.borrower = borrower;
    this.returnDate = returnDate;

  }

  public Borrow(){
    borrower=null;
    returnDate=null;
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
