package Model;

public class Borrow
{
  private Member borrower;
  private MyDate returnDate;

  public Borrow(Member borrower, MyDate returnDate)
  {
    this.borrower = borrower;
    this.returnDate = returnDate;

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
    return borrower.equals(borrower) && returnDate.equals(other.returnDate);
  }

  public String toString()
  {
    return "MEMBER:" + borrower + "RETURN DATE" + returnDate + "PICKUP DATE";
  }
}
