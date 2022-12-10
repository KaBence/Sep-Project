package Model;
import java.io.Serializable;
import java.util.ArrayList;


/**
 * This class is used to create an event object that contains the location, event name, guests, date, max capacity, games,
 * capacity, and member list
 */
public class Event implements Serializable {
    private String location;
    private String eventName;
    private ArrayList<String> guests;
    private String guestsString;
    private MyDate date;
    private int maxCapacity;
    private BoardGameList games;
    private int capacity;
    private MemberList memberList;

    // This is the constructor for the Event class. It is initializing the variables of the class.
    public Event(MyDate date, String location, String name, String guests, int capacity, BoardGameList games, MemberList members) {

        this.location = location;
        this.eventName = name;
        this.capacity = capacity;
        this.date = date;
        guestsString = guests;
        ArrayList<String> a = new ArrayList<String>();
        String[] tempArr = guests.split(",");
        for (int i = 0; i < tempArr.length; i++) {
            a.add(tempArr[i]);
        }
        this.guests = a;
        this.games = games;
        this.memberList = members;
    }

    /**
     * This function sets the maximum capacity of the cache.
     *
     * @param maxCapacity The maximum number of items that can be stored in the cache.
     */
    public void setCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    /**
     * This function returns the name of the event
     *
     * @return The event name.
     */
    public String getEventName(){
        return eventName;
    }
    /**
     * This function returns the capacity of the array.
     *
     * @return The capacity of the array.
     */
    public int getCapacity(){
        return capacity;
    }
    /**
     * This function sets the date of the object to the date passed in as a parameter.
     *
     * @param date The date of the event.
     */
    public void setDate(MyDate date){
        this.date = date;
    }

    /**
     * This function returns a string that contains the event name, date, location, capacity, games, members, and guests
     *
     * @return The event name, date, location, capacity, games, memberList, and guests.
     */
    public String toString() {
        return eventName + " on " + date + " at " + location + " with a max capacity of " + capacity + " players, playing these games" + games.toString() + "with these members: " + memberList
            + " and having these guests: \n" + guests.toString().replace("[", "").replace("]", "");
    }

    /**
     * This function sets the location of the object.
     *
     * @param location The location of the file.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * This function sets the event name
     *
     * @param eventName The name of the event you want to track.
     */
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    /**
     * If the object is null or not of the same class, return false. Otherwise, return true if the location, event name,
     * guests, date, capacity, games, and member list are equal
     *
     * @param obj the object to compare to
     * @return The method is returning a boolean value.
     */
    public boolean equals(Object obj) {

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Event other = (Event) obj;
        return
                location.equals(other.location) &&
                        eventName.equals(other.eventName) 
                        && date.equals(other.date) && capacity == other.capacity ;
    }

    /**
     * Returns the date of the event.
     *
     * @return The date of the event.
     */
    public MyDate getDate() {
        return date;
    }
    /**
     * Return a new MyDate object with the same day, month and year as the current object, but with the time set to
     * 00:00:00.
     *
     * @return A new MyDate object with the same day, month and year as the original object.
     */
    public MyDate getDateNoTime(){
        return new MyDate(date.getDay(), date.getMonth(), date.getYear()) ;
    }

    /**
     * This function copies the event and returns a new event with the same information.
     *
     * @return A copy of the event.
     */

    public Event copy() {
        String a = "";
        for (int i = 0; i < guests.size(); i++) {
            a += guests.get(i);
        }
        return new Event(date, location, eventName, a, capacity, games,
            memberList);
    }

    /**
     * This function returns the games variable.
     *
     * @return The games variable is being returned.
     */
    public BoardGameList getGames() {
        return games;
    }

    /**
     * > This function returns the memberList
     *
     * @return The memberList object.
     */
    public MemberList getMemberList() {
        return memberList;
    }

    /**
     * This function sets the memberList variable to the list parameter.
     *
     * @param list The list of members to be displayed.
     */
    public void setMemberList(MemberList list) {
        memberList = list;
    }

    /**
     * This function adds a board game to the list of games
     *
     * @param boardGame The board game to be added to the list of games.
     */
    public void addGames(BoardGame boardGame) {
        games.addBoardGame(boardGame);
    }

    /**
     * This function sets the games variable to the games parameter.
     *
     * @param games The list of games to be displayed.
     */
    public void setGames(BoardGameList games) {
        this.games = games;
    }

    /**
     * This function returns the location of the current object.
     *
     * @return The location of the event.
     */
    public String getLocation() {
        return location;
    }

    /**
     * This function returns a string of the guests.
     *
     * @return The guestsString is being returned.
     */
    public String getGuests() {
        return guestsString;
    }

    /**
     * This function returns an ArrayList of Strings that contains the names of the guests.
     *
     * @return An ArrayList of Strings
     */
    public ArrayList<String> getGuestsArr() {
        return guests;
    }

    /**
     * Adds a guest to the list of guests.
     *
     * @param guest The name of the guest to add.
     */
    public void addGuest(String guest) {
        guests.add(guest);
    }

    /**
     * Remove the guest with the given name from the list of guests.
     *
     * @param name The name of the guest to remove.
     */
    public void RemoveGuest(String name) {
        for (int i = 0; i < guests.size(); i++) {
            if (name.equals(guests.get(i))) {
                guests.remove(i);
                break;
            }
        }
    }
}

