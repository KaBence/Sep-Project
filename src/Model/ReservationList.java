package Model;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * The ReservationList class is a container for Reservation objects
 */
public class ReservationList implements Serializable
{
  private ArrayList<Reservation> reservations;

  /**
   * Default constructor for creating a new arraylist of reservations.
   */
  public ReservationList(){
    reservations=new ArrayList<>();
  }
  /**
   * This function adds a reservation to the list of reservations.
   * @param reservation The reservation to be added to the list of reservations.
   */
  public void addReservation(Reservation reservation){
    reservations.add(reservation);
  }

  /**
   * Remove the reservation from the list of reservations.
   * @param reservation The reservation to be removed from the list of reservations.
   */
  public void removeReservation(Reservation reservation){
    reservations.remove(reservation);
  }



  /**
   * This function returns the reservation at the given index.
   * @param index the index of the reservation to be returned
   * @return The reservation at the given index.
   */
  public Reservation get(int index){
    return reservations.get(index);
  }

  /**
   * Returns the number of reservations in the list.
   * @return The size of the reservations arraylist.
   */
  public int size(){
    return reservations.size();
  }
  /**
   * This function returns a string that contains all the reservations in the array list
   * @return The toString method is returning a string that contains all the reservations in the arraylist.
   */
  public String toString(){
    String a = "";
    for (int i = 0; i< reservations.size(); i++){
      a += reservations.get(i).toString() +"\n";
    }
    return a;
  }
}
