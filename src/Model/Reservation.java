package Model;

import java.io.Serializable;

public class Reservation implements Serializable
{
    private Member borrower;
    private MyDate pickUpDate;
    private MyDate returnDate;

    public Reservation(){
    }
    public Reservation( Member memberName, MyDate pickUpDate, MyDate returnDate){

        this.borrower = memberName;
        this.pickUpDate = pickUpDate;
        this.returnDate = returnDate;
    }



    public Member getBorrower() {
        return borrower;
    }

    public MyDate getPickUpDate() {
        return pickUpDate;
    }

    public MyDate getReturnDate() {
        return returnDate;
    }


    public void setBorrower(Member borrower) {
        this.borrower = borrower;
    }
    public void setPickUpDate(MyDate pickUpDate) {
        this.pickUpDate = pickUpDate;
    }
    public void setReturnDate(MyDate returnDate) {
        this.returnDate = returnDate;
    }

    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        Reservation other = (Reservation) obj;
        return returnDate.equals(other.returnDate) && pickUpDate.equals(other.pickUpDate) && borrower.equals(other.borrower);
    }

//    public String toString() {
//        return "Reservation of "+boardGame.getName()+" by "+memberName.getMemberName()+" on " + rentDate.toString()+" till "+returnDate.toString();
//    }
}
