package Model;

import java.io.Serializable;
import java.util.ArrayList;
import Util.MyFileHandler;
import java.io.FileNotFoundException;
import java.io.IOException;
public class EventList implements Serializable
{
    private ArrayList<Event> events;
    public EventList(){
        events = new ArrayList<Event>();
    }
    public void addEvent(Event event){
        events.add(event);
    }
    public void removeEvent(Event event){
        for (int i = 0; i< events.size(); i++){
            if (event.equals(events.get(i))) {
                events.remove(i);
                break;
            }

        }
    }
    public Event getEventByDate(MyDate date){

        for (int i = 0; i< events.size(); i++){
            if (events.get(i).getDate().equals(date)){
                return events.get(i);
            }
        }
        return null;
    }

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
        public EventList getEventsByTime(MyDate date){
            EventList list1 = new EventList();
            MyDate tempDate;
            for (int i = 0; i< events.size(); i++){
                tempDate = events.get(i).getDate();
                if (tempDate.equals(date)) list1.addEvent(events.get(i));
            }
            return list1;
    }

    public EventList getEventsByName(String name){
        EventList list1 = new EventList();
        String tempName;
        for (int i = 0; i< events.size(); i++) {
            tempName = events.get(i).getName();
            if (tempName.toLowerCase().contains(name)) {
                list1.addEvent(events.get(i));
            }
        }
        return list1;
    }


    public String toString(){
        String a = "";
        for(int i = 0; i< events.size(); i++){
            a+= events.get(i) + "\n";
        }
        return a;
    }
    public Event get(int index){
        return events.get(index);
    }
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

    public int size(){
        return events.size();
    }
    public void setEvent(Event event, int index){
        events.set(index,event);
    }

}

