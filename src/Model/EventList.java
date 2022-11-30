package Model;

import java.io.Serializable;
import java.util.ArrayList;
public class EventList implements Serializable
{
    private ArrayList<Event> list;
    public EventList(){
        list = new ArrayList<Event>();
    }
    public void addEvent(Event event){
        list.add(event);
    }
    public void removeEvent(Event event){
        for (int i = 0; i<list.size();i++){
            if (event.equals(list.get(i))) list.remove(i);
            break;
        }
    }
    public Event getEventByDate(MyDate date){

        for (int i = 0; i<list.size(); i++){
            if (list.get(i).getDate().equals(date)){
                return list.get(i);
            }
        }
        return null;
    }

    public Event getEventByLocation(String location){
        for (int i = 0; i<list.size(); i++){
            if (list.get(i).getLocation().equals(location)){
                return list.get(i);
            }
        }
        return null;
    }
    //    public Event getEventByTime(int time){
//        for (int i = 0; i<list.size(); i++){
//            if (list.get(i).getTime().equals(time)){
//                return list.get(i);
//            }
//        }
//        return null;
//    }
    public Event getEventByBoardGame(BoardGame boardgame){
        for (int i = 0; i<list.size(); i++){
            if (list.get(i).getGame().equals(boardgame)){
                return list.get(i);
            }
        }
        return null;
    }
    public Event getEventByName(String name){
        for (int i = 0; i<list.size(); i++){
            if (list.get(i).getName().equals(name)){
                return list.get(i);
            }
        }
        return null;
    }
}
