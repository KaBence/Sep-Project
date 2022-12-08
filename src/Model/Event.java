package Model;
import java.io.Serializable;
import java.util.ArrayList;
public class Event implements Serializable
{
    private String location;
    private String eventName;
    private ArrayList<String> guests;
    private String guestsString;
    private MyDate date;
    private int maxCapacity;
    private BoardGameList games;
    private int capacity;

    public Event(MyDate date, String location, String name, String guests, int capacity, BoardGameList games){

        this.location = location;
        this.eventName = name;
        this.capacity = capacity;
        this.date = date;
        guestsString = guests;
        ArrayList<String> a = new ArrayList<String>();
        String[] tempArr = guests.split(",");
        for (int i = 0; i<tempArr.length; i++){
            a.add(tempArr[i]);
        }
        this.guests = a;
        this.games = games;
    }

        public void setCapacity(int maxCapacity)
    {
        this.maxCapacity = maxCapacity;
    }

    public String getEventName(){
        return eventName;
    }
    public int getCapacity(){
        return capacity;
    }
    public void setDate(MyDate date){
        this.date = date;
    }

    public String toString(){
        return eventName + " on " +date+ " at " + location +" with a max capcity of "+capacity +" players, " + games.toString()+" and having these guests: \n" + guests;
    }
    public void setLocation(String location){
        this.location = location;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public boolean equals(Object obj){

        if (obj == null || getClass() != obj.getClass())
        {
            return false;
        }
        Event other = (Event)obj;
        return
                location.equals(other.location) &&
                        eventName.equals(other.eventName) && guests.equals(other.guests)
                && date.equals(other.date) && capacity == other.capacity;
    }

    public MyDate getDate() {
        return date;
    }
    public Event copy(){
        String a = "";
        for (int i = 0; i< guests.size();i++){
            a+= guests.get(i);
        }
        return new Event(date,location, eventName, a,capacity,games);
    }

    public BoardGameList getGames() {
        return games;
    }

    public void addGames(BoardGame boardGame){
        games.addBoardGame(boardGame);
    }
    public void setGames(BoardGameList games){
        this.games = games;
    }
    public String getLocation(){
        return location;
    }
    public String getGuests(){
        return guestsString;
    }
    public void addGuest(String guest) {
        guests.add(guest);
    }
    public void RemoveGuest(String name){
        for (int i = 0; i<guests.size();i++){
            if (name.equals(guests.get(i))) {guests.remove(i); break;}
        }
    }
}

