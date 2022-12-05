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
            if (event.equals(list.get(i))) {
                list.remove(i);
                break;
            }

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

    public EventList getEventsByLocation(String location){
        EventList list1 = new EventList();
        String[] tempName;
        for (int i = 0; i<list.size(); i++){
            tempName = list.get(i).getLocation().split(".");
            for (String a : tempName)
                if (a.toLowerCase().equals(location)){
                    list1.addEvent(list.get(i));
                }
        }
        return list1;
    }
        public EventList getEventsByTime(MyDate date){
            EventList list1 = new EventList();
            MyDate tempDate;
            for (int i = 0; i<list.size(); i++){
                tempDate = list.get(i).getDate();
                if (tempDate.equals(date)) list1.addEvent(list.get(i));
            }
            return list1;
    }

    public EventList getEventsByName(String name){
        EventList list1 = new EventList();
        String[] tempName;
        for (int i = 0; i<list.size(); i++){
            tempName = list.get(i).getName().split(" ");
            for (String a : tempName)
            if (a.toLowerCase().equals(name)){
                list1.addEvent(list.get(i));
            }
        }
        return list1;
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
    public Event get(int index){
        return list.get(index);
    }
    public int getIndexOf(Event event){
        int index = 0;
        for (int i = 0; i< list.size();i++){
            if (list.get(i).equals(event)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public int size(){
        return list.size();
    }
    public void setEvent(Event event, int index){
        list.set(index,event);
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


}
