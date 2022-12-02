package Model;

import java.io.Serializable;
import java.util.ArrayList;
import Util.MyFileHandler;
import java.io.FileNotFoundException;
import java.io.IOException;
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

    public Event getEventByName(String name){
        for (int i = 0; i<list.size(); i++){
            if (list.get(i).getName().equals(name)){
                return list.get(i);
            }
        }
        return null;
    }
    private String filename;
    public EventList(String filename){this.filename = filename;}
    public String toString(){
        String a = "";
        for(int i = 0; i< list.size();i++){
            a+= list.get(i) + "\n";
        }
        return a;
    }
    public EventList getAllEvents(){
        EventList list2 = new EventList();
        try
        {
            list2 = (EventList)MyFileHandler.readFromBinaryFile(filename);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        catch (IOException e)
        {
            System.out.println("IO Error reading file");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Class Not Found");
        }
        return list2;
    }

    public static void main(String[] args) {
        EventList eventBlea = new EventList("events.bin");
        EventList smm = eventBlea.getAllEvents();
        System.out.println(smm);
    }
}
