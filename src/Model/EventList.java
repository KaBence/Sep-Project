package Model;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * The EventList class is a container for a list of Event objects
 */
public class EventList implements Serializable
{
    private ArrayList<Event> events;

    /**
     * A non-argument constructor for the EventList class. It is creating a new ArrayList of type Event.
     */
    public EventList(){
        events = new ArrayList<Event>();
    }
    /**
     * This function adds an event to the list of events.
     * @param event The event to be added to the list of events.
     */
    public void addEvent(Event event){
        events.add(event);
    }
    /**
     * Remove the event from the list of events.
     * @param event the event to be removed
     */
    public void removeEvent(Event event){
        for (int i = 0; i< events.size(); i++){
            if (event.equals(events.get(i))) {
                events.remove(i);
                break;
            }

        }
    }

    /**
     * This method checks the events by its location as its parameters in the EvenList class.
     * @param location The location of the event.
     * @return A list of events that contain the location.
     */
    public EventList getEventsByLocation(String location){
        EventList list1 = new EventList();
        String tempLocation;
        for (int i = 0; i< events.size(); i++){
            tempLocation = events.get(i).getLocation();
            if (tempLocation.toLowerCase().contains(location)){
                    list1.addEvent(events.get(i));
                }
        }
        return list1;
    }
        /**
         * This method checks the events by time as its parameters in the EvenList class.
         * @param date the date you want to search for
         * @return A list of events that are on the same date as the date passed in.
         */
        public EventList getEventsByTime(MyDate date){
            EventList list1 = new EventList();
            MyDate tempDate;
            for (int i = 0; i< events.size(); i++){
                tempDate = events.get(i).getDate();
                if (tempDate.equals(date)) list1.addEvent(events.get(i));
            }
            return list1;
    }
    /**
     * This method checks the events by date as its parameters in the EvenList class.
     * @param date The date you want to search for.
     * @return A list of events that are on the same date as the date passed in.
     */
    public EventList getEventsByDate(MyDate date){
        EventList list1 = new EventList();
        MyDate tempDate;
        for (int i = 0; i< events.size(); i++){
            tempDate = events.get(i).getDateNoTime();
            if (tempDate.equals(new MyDate(date.getDay(), date.getMonth(), date.getYear()))) list1.addEvent(events.get(i));
        }
        return list1;
    }
    /**
     * This function takes a BoardGame object as a parameter and returns an EventList object that contains all the events
     * that have the BoardGame object in their BoardGameList
     * @param game The game you want to search for
     * @return A list of events that contain the game.
     */
    public EventList getEventsByGame(BoardGame game){
        EventList list1 = new EventList();
        BoardGameList tempGames;
        for (int i = 0; i< events.size(); i++){
            tempGames = events.get(i).getGames();
            for (int j = 0; j< tempGames.size();j++){
                if (tempGames.get(j).equals(game)) list1.addEvent(events.get(i));
            }
        }
        return list1;
    }

    /**
     * This function takes in a string and returns an EventList object that contains all the events that have the string in
     * their name
     * @param name The name of the event you want to search for.
     * @return A list of events that contain the name.
     */
    public EventList getEventsByName(String name){
        EventList list1 = new EventList();
        String tempName;
        for (int i = 0; i< events.size(); i++) {
            tempName = events.get(i).getEventName();
            if (tempName.toLowerCase().contains(name)) {
                list1.addEvent(events.get(i));
            }
        }
        return list1;
    }


    /**
     * This function returns a string that contains all the events in the calendar
     * @return The toString method is returning a string of all the events in the arraylist.
     */
    public String toString(){
        String a = "";
        for(int i = 0; i< events.size(); i++){
            a+= events.get(i) + "\n";
        }
        return a;
    }
    /**
     * This function returns the event at the given index.
     * @param index The index of the event you want to get.
     * @return The event at the given index.
     */
    public Event get(int index){
        return events.get(index);
    }
    /**
     * This function returns the index of the event in the events array list.
     * @param event the event you want to find the index of
     * @return The index of the event in the list.
     */
    public int getIndexOf(Event event){
        int index = 0;
        for (int i = 0; i< events.size(); i++){
            if (events.get(i).equals(event)) {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * Returns the number of events in the event list.
     * @return The size of the events arraylist.
     */
    public int size(){
        return events.size();
    }
    /**
     * This function sets the event at the given index to the given event
     * @param event The event to be added to the list.
     * @param index The index of the event you want to set.
     */
    public void setEvent(Event event, int index){
        events.set(index,event);
    }
    /**
     * This function swaps the events
     * @param x the index of the first event
     * @param y the index of the event you want to replace
     */
    public void replace(int x, int y){
        Event a = get(x);
        Event b = get(y);
        setEvent(a,y);
        setEvent(b,x);
    }

}

