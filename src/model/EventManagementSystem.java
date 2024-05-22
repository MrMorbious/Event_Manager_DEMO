package model;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class EventManagementSystem {
    private static ArrayList<Event> listOfEvents = new ArrayList<>();
    public int getNextEventId() {
        if (listOfEvents != null && !listOfEvents.isEmpty()) {
            return listOfEvents.get(listOfEvents.size() - 1).getEventId()+1;
        }
        return 1;
    }
    public boolean addNewEvent(Event eventInformation){
        return listOfEvents.add(eventInformation);
    }
    public void printListOfEvent(){
        for (Event event : listOfEvents)
            event.displayOfEvent();
    }
    public  Event findEventByLocation(String location){
        for (Event eventInformation : listOfEvents){
            if(eventInformation.getLocation().equalsIgnoreCase(location))
                return eventInformation;
        }
        return null;
    }
    public void searchAllEventByLocation(String location) {
        if (findEventByLocation(location) == null)
            System.out.println("No Event Found");
        else {
            System.out.println("\nEvent that have the location are: ");
            for (Event eventInformation : listOfEvents) {
                if (eventInformation.getLocation().equalsIgnoreCase(location))
                    eventInformation.displayOfEvent();
            }
        }
    }

    public Event findEventByID(int eventID) {
        for (Event bookInformation : listOfEvents) {
            if (bookInformation.getEventId() == eventID )
                return bookInformation;
        }
        return null;
    }
    public boolean checkIdOfEventExist(int eventId){
        for (Event eventInformation : listOfEvents){
            if (eventInformation.getEventId() == eventId) {
                return true;
            }
        }
        return false;
    }
    public boolean checkListEmpty() {
        return listOfEvents.isEmpty();
    }
    public void storeDataToFile(){
        String fileNameOfEvent = "events.dat";
        try{
            FileOutputStream f = new FileOutputStream(fileNameOfEvent);
            ObjectOutputStream fo = new ObjectOutputStream(f);
            for (Event eventInformation : listOfEvents){
                fo.writeObject(eventInformation);
            }
            fo.close();
            f.close();
        }catch (Exception ignored){
        }
        System.out.println("Store data to file success!");
    }
    public void loadDataFromFile(){
        String fileNameOfEvent = "events.dat";
        try {
            File f = new File(fileNameOfEvent);
            if (!f.exists()) return;
            FileInputStream fi = new FileInputStream(f);
            ObjectInputStream fo = new ObjectInputStream(fi);
            Event e;
            while((e = (Event)fo.readObject()) != null) {
                addNewEvent(e);
            }
            fo.close();
            fi.close();
        }catch (Exception ignored){
        }
    }

}
