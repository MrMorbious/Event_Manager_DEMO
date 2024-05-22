package controller;

import model.Event;
import model.EventManagementSystem;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;


public class EventController {
    EventManagementSystem manager = new EventManagementSystem();


    public void searchEventByLocation () {
        boolean stillAdd = true;
        int statusYesNo;
        String input = "";
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter location you want to search: ");
        do {
            try {
                input = sc.nextLine();
                if (input.isEmpty())
                    throw new Exception("Location cannot empty!");
                stillAdd = false;
            }catch (Exception e){
                System.out.println(e.getMessage());
                System.out.print("Enter another location: ");
            }
        }while (stillAdd);

        manager.searchAllEventByLocation(input);
        System.out.println("\nContinue searching another event?");
        statusYesNo = addStatusYesNo();
        if (statusYesNo == 1){
            searchEventByLocation();
        }
    }

    public void updateEventDetails(int eventID) {
        int statusYesNo;
        Scanner sc = new Scanner(System.in);
        boolean stillAdd = true;
        System.out.println("\n ---> Leave blank to keep current <---");
        System.out.print("Event's ID you want to update: ");
        if (!manager.checkIdOfEventExist(addId())) {
            System.out.println("Event does not exist!\n");
            System.out.println("Continue updating another event?");
            statusYesNo = addStatusYesNo();
            if (statusYesNo == 1) updateEventDetails(eventID);
        } else {
//            int idWantUpdate;
//            idWantUpdate = addId();
//            updateEventDetails(idWantUpdate);

            Event eventWillUpdate = manager.findEventByID(eventID);
            System.out.print("Update event's name: ");
            do {
                try {
                    sc = new Scanner(System.in);
                    String newName = sc.nextLine();
                    if (!newName.isEmpty()) {
                        assert eventWillUpdate != null;
                        eventWillUpdate.setName(newName);
                    }
                    stillAdd = false;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.out.println("Enter another event's name: ");
                }
            } while (stillAdd);

            System.out.print("Update date (YYYY-MM-DD): ");
            do {
                try {
                    sc = new Scanner(System.in);
                    String input_date = sc.nextLine().trim();
                    if (input_date.isEmpty()){
                        break;
                    }
                    LocalDate input = LocalDate.parse(input_date);
                    eventWillUpdate.setDate(input);
                    stillAdd = false;
                } catch (Exception e) {
                    System.out.println("Invalid date format. Please enter date in the format YYYY-MM-DD.");
                    System.out.print("Enter another date:");
                    stillAdd = true;
                }

            } while (stillAdd) ;

            System.out.print("Update location: ");
            do {
                try {
                    sc = new Scanner(System.in);
                    String newLocation = sc.nextLine();
                    if (!newLocation.isEmpty()) {
                        eventWillUpdate.setLocation(newLocation);
                    }
                    stillAdd = false;
                } catch (Exception e){
                    System.out.println(e.getMessage());
                    System.out.print("Enter another location: ");
                    stillAdd = true;
                }
            }while (stillAdd);

            System.out.print("Update attendees: ");
            do {
                try {
                    sc = new Scanner(System.in);
                    String newAttendees = sc.nextLine();
                    if (!newAttendees.isEmpty()){
                        try {
                            int newAtt = Integer.parseInt(newAttendees);
                            if (newAtt <= 0)
                                throw  new Exception("Attendees must be a positive integer!");
                            eventWillUpdate.setNumberOfAttendees(newAtt);
                        } catch (NumberFormatException e){
                            throw new Exception("Invalid the number of attendees format!");
                        }
                    }
                    stillAdd = false;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.out.print("Enter another the number of attendees: ");
                    stillAdd = true;
                }

            }while (stillAdd);

            System.out.println("Update event's status: ");
            System.out.println("1. Available");
            System.out.println("2. Not Available");
            System.out.print("Your choice: ");
            do{
                try {
                    sc = new Scanner(System.in);
                    String input = sc.nextLine();

                    if (input.isEmpty()){
                        stillAdd = false;
                        break;
                    }
                    int input_choice = Integer.parseInt(input);

                    if (input_choice == 1) {
                        eventWillUpdate.setStatus(true);
                    }
                    else if (input_choice == 2)
                        eventWillUpdate.setStatus(false);
                    else throw new Exception("Just choice 1 or 2!");
                    stillAdd = false;
                } catch (NumberFormatException e){
                    System.out.println("Invalid input format! Please enter a number.");
                    System.out.print("Enter another choice: ");
                    stillAdd = true;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.out.print("Enter another choice: ");
                    stillAdd = true;
                }
            }while (stillAdd);
        }

    }
    public void updateEvent () {
        Scanner sc = new Scanner(System.in);
        int choice;
        boolean stillAdd = false;
        do {
            System.out.println("\n1. Update event details");
            System.out.println("2. Delete event");
            System.out.print("\nYour choice is: ");
            sc = new Scanner(System.in);
            choice = sc.nextInt();
            try {
                if(choice == 1) {
                    updateEventDetails(choice);
                    stillAdd = false;
                }
                if(choice == 2) {
                    System.out.println("Hello world");
                    stillAdd = false;
                }
                if(choice < 1 || choice > 2)
                    throw new Exception();
            }catch (Exception e){
                System.out.println("Just choose 1 or 2!");
                stillAdd = true;
            }
        }while (stillAdd);
    }
    public void createNewEvent(){
        int statusYesNo;
        Event events = new Event();
        int last_id = manager.getNextEventId();
        events.setEventId(last_id);
        events.addEventInformation();
        if(manager.addNewEvent(events)){
            System.out.println("\nAdded success !");
            System.out.println("Continue adding another event ?");
            statusYesNo = addStatusYesNo();
            if (statusYesNo == 1){
                createNewEvent();
            }

        } else System.out.println("Added fail !");
    }
    public void continue_check () {
        int statusYesNo;
        System.out.print("\nEnter event's id you want to check exist: ");
        if (manager.checkIdOfEventExist(addId()))
            System.out.println("--> Exist Event <--\n");
        else System.out.println("--> No Event Found!<--\n");
        System.out.println("Continue checking another event ?");
        statusYesNo = addStatusYesNo();
        if (statusYesNo == 1){
            continue_check();
        }
    }
    public void printList(){
        if (manager.checkListEmpty()){
            System.out.println("No element!");
        } else manager.printListOfEvent();
    }
    public void checkStoreToFile(){
        if (manager.checkListEmpty())
            System.out.println("No element!");
        else manager.storeDataToFile();
    }
    public int addStatusYesNo() {
        int statusYesNo = 0;
        boolean stillAdd = true;
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Yes");
        System.out.println("2. No");
        System.out.print("Your choice: ");
        do {
            try {
                sc = new Scanner(System.in);
                statusYesNo = sc.nextInt();
                if (statusYesNo != 1 && statusYesNo != 2)
                    throw new Exception();
                stillAdd = false;
            } catch (Exception e) {
                System.out.print("Just choose 1 or 2: ");
            }
        } while (stillAdd);
        return statusYesNo;
    }
    public int addId() {
        boolean stillAdd = true;
        int id = 0;
        Scanner sc = new Scanner(System.in);
        do {
            try {
                sc = new Scanner(System.in);
                id = sc.nextInt();
                stillAdd = false;
            } catch (Exception e) {
                System.out.println("ID cannot empty OR Wrong format!");
                System.out.print("Enter another ID: ");
            }
        } while (stillAdd);
        return id;
    }
}
