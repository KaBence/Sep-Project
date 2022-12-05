package Model;

import java.io.Serializable;
import java.util.Objects;

public class Reservation implements Serializable
{
    private Member memberName;
    private MyDate rentDate;
    private MyDate returnDate;

    public Reservation(){
    }
    public Reservation( Member memberName, MyDate rentDate, MyDate returnDate){

        this.memberName = memberName;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
    }



    public Member getMemberName() {
        return memberName;
    }

    public MyDate getRentDate() {
        return rentDate;
    }

    public MyDate getReturnDate() {
        return returnDate;
    }



    public void setMemberName(Member memberName) {
        this.memberName = memberName;
    }

    public void setRentDate(MyDate rentDate) {
        this.rentDate = rentDate;
    }

    public void setReturnDate(MyDate returnDate) {
        this.returnDate = returnDate;
    }

    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        Reservation other = (Reservation) obj;
        return returnDate.equals(other.rentDate) && rentDate.equals(other.rentDate) && memberName.equals(other.memberName);
    }

//    public String toString() {
//        return "Reservation of "+boardGame.getName()+" by "+memberName.getMemberName()+" on " + rentDate.toString()+" till "+returnDate.toString();
//    }
}
