package Model;

public class Borrow
{
  private Member borrower;
  private MyDate returnDate;
  public Borrow(Member borrower,MyDate returnDate){
    this.borrower=borrower;
    this.returnDate=returnDate;
  }
}
