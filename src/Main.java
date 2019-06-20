import PhoneBook.Phonebook;

import java.util.Scanner;


public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter full path to phonebook file: \n");
        String path = in.nextLine();
        Phonebook phoneBook = new Phonebook();

        while (true) {
            System.out.println("Menu:\n");
            System.out.println("Press 1. For read from a file. \n");
            System.out.println("Press 2. For creating a new record. \n");
            System.out.println("Press 3. For delete a record. \n");
            System.out.println("Press 4. For access to a number. \n");
            System.out.println("Press 5. For print all numbers. \n");
            System.out.println("Press 6. For print top 5 outgoing calls. \n");
            System.out.println("Press 7. For an exit. \n");

            int choice = Integer.parseInt(in.nextLine());

            switch (choice) {

                case 1:
                    if (phoneBook.readFromFile(path)) {
                        System.out.println("Reading from file - Successful!\n");
                    } else {
                        System.out.println("Check the name or path file and restart the program!\n");
                    }
                    break;

                case 2:
                    System.out.println("Enter name: ");
                    String name = in.nextLine();
                    System.out.println("Enter number: ");
                    String phoneNumber = in.nextLine();
                    if (scanner.hasNextLine()) {
                        String phone = scanner.nextLine();
                        Phonebook.Results results = phoneBook.addContact(name, phoneNumber);
                        {
                            switch (results) {
                                case ADDED:
                                    System.out.println("Added new contact: " + name);
                                    break;
                                case UPDATED:
                                    System.out.println("Updated the contact: " + name);
                                    break;
                                case INVALID_NAME:
                                    System.out.println(name + " is not a valid name");
                                    break;
                                case INVALID_NUMBER:
                                    System.out.println(phone + " is not a valid phone number");
                                    break;
                            }
                        }
                    }
                    break;

                case 3:
                    System.out.println("Enter the name you want to delete: \n");
                    String deletedName = in.nextLine();
                    if (phoneBook.deleteContact(deletedName)) {
                        System.out.println("Deleting record - successful!\n");
                    } else {
                        System.out.println("The name you want to delete is not in the phonebook.\n");
                    }
                    break;

                case 4:
                    System.out.println("Enter the name you want to access: \n");
                    String viewName = in.nextLine();
                    System.out.println(phoneBook.viewPhoneNumber(viewName));
                    break;

                case 5:
                    phoneBook.printAllSorted();
                    System.out.println("These are all the contacts in your phonebook. \n");
                    break;

                case 6:
                    phoneBook.getTopFive();
                    System.out.println("These are the 5 most called contacts. \n");
                    break;
                case 7:
                    System.out.println("See you again soon! \n");
                    System.exit(0);
                    break;

                default:
                    System.out.println(choice + "Invalid input. \n");
            }
        }
    }
}


