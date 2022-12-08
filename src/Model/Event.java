package Model;
import java.io.Serializable;
import java.util.ArrayList;
public class Event implements Serializable
{
    private String location;
    private String name;
    private ArrayList<String> guests;
    private String guestsString;
    private MyDate date;
    private int maxCapacity;
    private BoardGameList games;
    private int capacity;
    private MemberList members;

    public Event(MyDate date, String location, String name, String guests, int capacity, BoardGameList games, MemberList members){

        this.location = location;
        this.name = name;
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
        this.members = members;
    }

        public void setCapacity(int maxCapacity)
    {
        this.maxCapacity = maxCapacity;
    }

    public String getName(){
        return name;
    }
    public int getCapacity(){
        return capacity;
    }
    public void setDate(MyDate date){
        this.date = date;
    }

    public String toString(){
        return name + " on " +date+ " at " + location +" with a max capcity of "+capacity +" players, playing these games" + games.toString()+"with these members: " +members+" and having these guests: \n" + guests;
    }
    public void setLocation(String location){
        this.location = location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean equals(Object obj){

        if (obj == null || getClass() != obj.getClass())
        {
            return false;
        }
        Event other = (Event)obj;
        return
                location.equals(other.location) &&
                        name.equals(other.name) && guests.equals(other.guests)
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
        return new Event(date,location,name, a,capacity,games, members);
    }

    public BoardGameList getGames() {
        return games;
    }
    public MemberList getMembers(){
        return members;
    }
    public void setMembers(MemberList list){
        members = list;
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
    public ArrayList<String> getGuestsArr(){
        return guests;
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

