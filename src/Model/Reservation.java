package Model;

import java.io.Serializable;


/**
 * A Reservation object represents a reservation of a book by a member
 */
public class Reservation implements Serializable
{
    private Member borrower;
    private MyDate pickUpDate;
    private MyDate returnDate;

    public Reservation(){
    }

    /**
     * This is a constructor. It is a method that is called when an object is created. It is used to initialize the object.
     */
    public Reservation( Member memberName, MyDate pickUpDate, MyDate returnDate){

        this.borrower = memberName;
        this.pickUpDate = pickUpDate;
        this.returnDate = returnDate;
    }



    /**
     * This function returns the borrower of the book
     *
     * @return The borrower of the book.
     */
    public Member getBorrower() {
        return borrower;
    }

    /**
     * This function returns the pickUpDate
     *
     * @return The pickUpDate variable is being returned.
     */
    public MyDate getPickUpDate() {
        return pickUpDate;
    }

    /**
     * This function returns the returnDate variable.
     *
     * @return The returnDate variable is being returned.
     */
    public MyDate getReturnDate() {
        return returnDate;
    }


    /**
     * The function setBorrower() sets the borrower of the book to the borrower passed in as a parameter
     *
     * @param borrower The member who borrowed the book.
     */
    public void setBorrower(Member borrower) {
        this.borrower = borrower;
    }
    /**
     * This function sets the pickUpDate variable to the value of the pickUpDate parameter.
     *
     * @param pickUpDate The date the item was picked up.
     */
    public void setPickUpDate(MyDate pickUpDate) {
        this.pickUpDate = pickUpDate;
    }
    /**
     * This function sets the return date of the book.
     *
     * @param returnDate The date the book is due to be returned.
     */
    public void setReturnDate(MyDate returnDate) {
        this.returnDate = returnDate;
    }

    /**
     * If the object is not null and is of the same class as the current object, then return true if the return date, pick
     * up date, and borrower are equal
     *
     * @param obj The object to compare this instance with.
     * @return The hashcode of the object.
     */
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        Reservation other = (Reservation) obj;
        return returnDate.equals(other.returnDate) && pickUpDate.equals(other.pickUpDate) && borrower.equals(other.borrower);
    }


}
