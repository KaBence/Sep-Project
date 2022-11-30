package Model;
import java.io.Serializable;
import java.util.ArrayList;
public class Event implements Serializable
{
    private MyDate time;
    private String location;
    private String name;
    private ArrayList<String> guests;
    private MyDate date;
    private BoardGame game;

    public Event(MyDate date,MyDate time, String location, String name, ArrayList<String> guests){
        this.time = time;
        this.location = location;
        this.name = name;
        this.guests = guests;
    }

    public int getTime(){
        return time;
    }
    public String getName(){
        return name;
    }
    public void setDate(MyDate date){
        this.date = date;
    }

    public String toString(){
        return name + " on " +date.toString()+" "+ time + "at" + location;
    }
    public void setLocation(String location){
        this.location = location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setGuests(ArrayList<String> guests) {
        this.guests = guests;
    }
    public boolean equals(Object obj){

        if (obj == null || getClass() != obj.getClass())
        {
            return false;
        }
        Event other = (Event)obj;
        return time == other.time &&
                location.equals(other.location) &&
                name.equals(other.name);
    }
    public void setBoardGame(BoardGame boardGame){
        this.game = boardGame;
    }

    public MyDate getDate() {
        return date;
    }
    public Event copy(){
        return new Event(time,location,name,guests);
    }

    public BoardGame getGame() {
        return game;
    }
    public String getLocation(){
        return location;
    }

    public void addGuest(String guest) {
        guests.add(guest);
    }
    public void RemoveGuest(String name){
        guests.remove(name);

//        for (int i = 0; i<guests.size();i++){
//            if (name.equals(guests.get(i))) {guests.remove(i); break;}
//        }
    }
}

