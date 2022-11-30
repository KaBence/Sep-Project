package Model;
import java.util.ArrayList;
public class ReservationList
{
    private ArrayList<Reservation> list;

    public ReservationList(){
    }
    public void addReservation(Reservation reservation){
        list.add(reservation);
    }
    public void setReservations(ArrayList<Reservation> reservations){
        list = reservations;
    }
    public ArrayList<Reservation> getReservations(){
        return list;
    }
    public String toString(){
        String a = "";
        for (int i = 0; i<list.size();i++){
            a += list.get(i).toString() +"\n";
        }
        return a;
    }
}
