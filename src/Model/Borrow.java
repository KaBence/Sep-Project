package Model;

public class Borrow
{
  private Member borrower;
  private MyDate returnDate;
  private MyDate pickUpDate;

  public Borrow(Member borrower, MyDate returnDate, MyDate pickUpDate)
  {
    this.borrower = borrower;
    this.returnDate = returnDate;
    this.pickUpDate = pickUpDate;
  }

  public void setReturnDate(MyDate returnDate)
  {
    this.returnDate = returnDate;
  }

  public void setBorrower(Member borrower)
  {
    this.borrower = borrower;
  }

  public void setPickUpDate(MyDate pickUpDate)
  {
    this.pickUpDate = pickUpDate;
  }

  public MyDate getReturnDate()
  {
    return returnDate;
  }

  public MyDate getPickUpDate()
  {
    return pickUpDate;
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
    return borrower.equals(borrower) && returnDate.equals(other.returnDate)
        && pickUpDate.equals(other.pickUpDate);
  }

  public String toString()
  {
    return "MEMBER:" + borrower + "RETURN DATE" + returnDate + "PICKUP DATE"
        + pickUpDate;
  }
}
