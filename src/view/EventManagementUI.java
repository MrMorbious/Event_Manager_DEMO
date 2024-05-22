package view;

import controller.EventController;
import model.EventManagementSystem;
import java.util.Scanner;

public class EventManagementUI {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        boolean stillAdd = true;
        EventController controller = new EventController();
        EventManagementSystem manager = new EventManagementSystem();
        manager.loadDataFromFile();

        do {
            System.out.println("\n");
            System.out.println("============Welcome to Event Manager============");
            System.out.println("1. Create a new event");
            System.out.println("2. Check if an event exists");
            System.out.println("3. Search for event information by location");
            System.out.println("4. Update event");
            System.out.println("\t4.1. Update event details");
            System.out.println("\t4.2. Delete event");
            System.out.println("5. Save events to a file");
            System.out.println("6. Print the list of events from the file");
            System.out.println("7. Quit");
            System.out.println("=================================================");
            System.out.print("Your choice is: ");

            do {
                try {
                    sc = new Scanner(System.in);
                    choice = sc.nextInt();
                    if(choice <= 0 || choice > 7)
                        throw new Exception("Please enter function from 1 to 7!");
                stillAdd = false;
                }catch (Exception e){
                    System.out.println(e.getMessage());
                    System.out.print("Enter another choice: ");
                }

            }while (stillAdd);


            switch (choice) {
                case 1:
                    controller.createNewEvent();
                    break;

                case 2:
                    controller.continue_check();
                    break;
                case 3:
                    controller.searchEventByLocation();
                    break;

                case 4:
                    controller.updateEvent();
                    break;

                case 5:
                    controller.checkStoreToFile();
                    break;
                case 6:
                    controller.printList();
                    break;

                case 7:
                    System.out.println("Quit menu. See you later!");
                    break;
            }
        }while(choice >= 1 && choice < 7);

    }
}