package Model;

import java.io.Serializable;
import java.util.ArrayList;

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
    public EventList getEventsByDate(MyDate date){
        EventList list1 = new EventList();
        MyDate tempDate;
        for (int i = 0; i< events.size(); i++){
            tempDate = events.get(i).getDateNoTime();
            if (tempDate.equals(new MyDate(date.getDay(), date.getMonth(), date.getYear()))) list1.addEvent(events.get(i));
        }
        return list1;
    }
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
    public void replace(int x, int y){
        Event a = get(x);
        Event b = get(y);
        setEvent(a,y);
        setEvent(b,x);
    }

}

