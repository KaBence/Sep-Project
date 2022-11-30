package Model;

import java.io.Serializable;
import java.util.Objects;

public class Reservation implements Serializable
{
    private BoardGame boardGame;
    private Member memberName;
    private MyDate rentDate;
    private MyDate returnDate;

    public Reservation(){
    }
    public Reservation(BoardGame boardGame, Member memberName, MyDate rentDate, MyDate returnDate){
        this.boardGame = boardGame;
        this.memberName = memberName;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
    }

    public BoardGame getBoardGame() {
        return boardGame;
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

    public void setBoardGame(BoardGame boardGame) {
        this.boardGame = boardGame;
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
        return returnDate.equals(other.rentDate) && rentDate.equals(other.rentDate) && memberName.equals(other.memberName) && boardGame.equals(other.boardGame);
    }

//    public String toString() {
//        return "Reservation of "+boardGame.getName()+" by "+memberName.getMemberName()+" on " + rentDate.toString()+" till "+returnDate.toString();
//    }
}
