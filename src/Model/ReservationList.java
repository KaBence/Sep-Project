package Model;
import java.io.Serializable;
import java.util.ArrayList;
public class ReservationList implements Serializable
{
  private ArrayList<Reservation> reservations;

  public ReservationList(){
    reservations=new ArrayList<>();
  }
  public void addReservation(Reservation reservation){
    reservations.add(reservation);
  }

  public void removeReservation(Reservation reservation){
    reservations.remove(reservation);
  }

  public ArrayList<Reservation> getReservations(){
    return reservations;
  }

  public Reservation get(int index){
    return reservations.get(index);
  }

  public int size(){
    return reservations.size();
  }
  public String toString(){
    String a = "";
    for (int i = 0; i< reservations.size(); i++){
      a += reservations.get(i).toString() +"\n";
    }
    return a;
  }
}
