package Model;

import java.io.Serializable;


/**
 * A Reservation object represents a reservation of a game by a member
 */
public class Reservation implements Serializable
{
    private Member borrower;
    private MyDate pickUpDate;
    private MyDate returnDate;

    /** A default constructor.
     */
    public Reservation(){
    }

    /**
     * This is a constructor with parameters memberName, pickUpDate and returnDate.
     */
    public Reservation( Member memberName, MyDate pickUpDate, MyDate returnDate){

        this.borrower = memberName;
        this.pickUpDate = pickUpDate;
        this.returnDate = returnDate;
    }



    /**
     * This function returns the borrower of the reservation.
     * @return The borrower of the reservation.
     */
    public Member getBorrower() {
        return borrower;
    }

    /**
     * This function returns the pickUpDate
     * @return The pickUpDate variable is being returned.
     */
    public MyDate getPickUpDate() {
        return pickUpDate;
    }

    /**
     * This function returns the returnDate variable.
     * @return The returnDate variable is being returned.
     */
    public MyDate getReturnDate() {
        return returnDate;
    }


    /**
     * The function setBorrower() sets the borrower of the reservation
     * @param borrower The borrower of the reservation.
     */
    public void setBorrower(Member borrower) {
        this.borrower = borrower;
    }

    /**
     * This function sets the pickUpDate variable to the value of the pickUpDate parameter.
     * @param pickUpDate The date the game was picked up.
     */
    public void setPickUpDate(MyDate pickUpDate) {
        this.pickUpDate = pickUpDate;
    }
    /**
     * This function sets the return date of the reservation
     * @param returnDate The date of the reservation.
     */
    public void setReturnDate(MyDate returnDate) {
        this.returnDate = returnDate;
    }

    /**
     * This method checks whether provided parameter is equals to reservation or not.
     * @param obj The object to compare this instance with.
     * @return true if obj is equals to the current reservation otherwise false.
     */
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        Reservation other = (Reservation) obj;
        return returnDate.equals(other.returnDate) && pickUpDate.equals(other.pickUpDate) && borrower.equals(other.borrower);
    }


}
