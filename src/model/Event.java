package model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.io.Serializable;

public class Event implements Serializable {
    private int eventId;
    private String name;
    private LocalDate date;
    private String location;
    private int numberOfAttendees;
    private boolean status;

    public Event (int eventId, String name, LocalDate date, String location, int numberOfAttendees, boolean status) {
        this.eventId = eventId;
        this.name = name;
        this.date = date;
        this.location = location;
        this.numberOfAttendees = numberOfAttendees;
        this.status = status;
    }

    public Event() {}

    public int getEventId() {return eventId;}

    public void setEventId(int eventId) {this.eventId = eventId;}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getNumberOfAttendees() {
        return numberOfAttendees;
    }

    public void setNumberOfAttendees(int numberOfAttendees) {
        this.numberOfAttendees = numberOfAttendees;
    }

    @Override
    public String toString(){
        return status ? "Available" : "Not Available";
    }
    public void setStatus(boolean status) {
        this.status = status;
    }

    public void addEventInformation(){
        Scanner sc = new Scanner(System.in);
        boolean stillAdd = true;

        System.out.print("Enter event's name: ");
        do {
            try{
                sc = new Scanner(System.in);
                name = sc.nextLine().trim();
                if (name.length() < 5 ){
                    throw new Exception("The name least five characters");
                }
                if(name.isEmpty())
                    throw new Exception("Event's name not empty!");
                stillAdd = false;
            }catch (Exception e){
                System.out.println(e.getMessage());
                System.out.print("Enter another name: ");
            }
        }while (stillAdd);

        System.out.print("Enter the date (YYYY-MM-DD): ");
        do {
            sc = new Scanner(System.in);
            String date_input = sc.nextLine();
            if (date_input.trim().isEmpty()) {
                try {
                    throw new IllegalArgumentException("Event's date cannot be empty!");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
            try{
                date = LocalDate.parse(date_input);
                stillAdd = false;
            }catch (DateTimeParseException e){
                System.out.println("Invalid date format. Please enter date in the format YYYY-MM-DD.");
                System.out.print("Enter another date: ");
                stillAdd = true;
            }
        }while (stillAdd);

        System.out.print("Enter event's location: ");

        do{
            try{
                sc = new Scanner(System.in);
                location = sc.nextLine();
                if(location.isEmpty())
                    throw new Exception("Event's location not empty!");
                stillAdd = false;
            }catch (Exception e){
                System.out.println(e.getMessage());
                System.out.print("Enter another location: ");
                stillAdd = true;
            }
        }while (stillAdd);

        System.out.print("Enter the number of attendees: ");
        do {
            try{
                sc = new Scanner(System.in);
                String attendees_input = sc.nextLine();
                try{
                    int attendeesOfEvent = Integer.parseInt(String.valueOf(attendees_input));
                    if (attendees_input.isEmpty())
                        throw new Exception("The number of attendees cannot empty!");
                    if(attendeesOfEvent <= 0)
                        throw new Exception("Attendees must be a positive integer!");
                }catch (NumberFormatException e){
                    System.out.println("Invalid the number of attendees format!");
                }
                numberOfAttendees = Integer.parseInt(String.valueOf(attendees_input));
                stillAdd = false;
            }catch (Exception e){
                System.out.println(e.getMessage());
                System.out.print("Enter another attendees: ");
                stillAdd = true;
            }
        }while (stillAdd);

        System.out.println("\nEnter event's status: ");
        System.out.println("1. Available");
        System.out.println("2. Not Available");
        System.out.print("Your choice: ");
        do{
            try {
                sc = new Scanner(System.in);
                int input_choice = sc.nextInt();
                if (input_choice == 1) {
                    status = true;
                }
                 else if (input_choice == 2)
                    status = false;
                 else throw new Exception("Just choice 1 or 2!");
                stillAdd = false;
            }catch (Exception e){
                System.out.println(e.getMessage());
                System.out.print("Enter another choice: ");
                stillAdd = true;
            }
        }while (stillAdd);
    }
    public void displayOfEvent() {
        System.out.println("Event ID: #" + eventId);
        System.out.println("Name: " + name);
        System.out.println("Date: " + date);
        System.out.println("Location: " + location);
        System.out.println("Attendees: " + numberOfAttendees);
        System.out.println("Status: " + toString() +"\n--------------------");
    }

}
