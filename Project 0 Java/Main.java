import java.util.Scanner;

public class Main {

    private static final String[][] MENUS = {
            // Main Menu
            {"Add New Contact", "Edit Contact", "Delete Contact", "View Phonebook", "Exit"},
            // Edit Contact Menu
            {"Student Number", "First Name", "Last Name", "Occupation", "Country Code",
                    "Area Code", "Phone Number", "None - Go back to Main Menu"},
            // Menu for View Phonebook
            {"View All", "View Contact through ID", "View Contacts through Country Code",
                    "Go back to Main Menu"},
    };
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        Phonebook pb = new Phonebook();
        boolean exit = false;
        while (true) {
            showMenu(1, 1);
            int opt = Integer.parseInt(prompt("Select an option: "));
            switch (opt) {
                case 1:
                    pb.insert(createNewPerson());
                    break;
                case 2:
                    String editMenuPrompt = "Enter student number to edit: ";
                    String studentNumber = prompt(editMenuPrompt);
                    Person personToEdit = pb.getContact(studentNumber);
                    if (personToEdit != null) {
                        int editOpt;
                        do {
                            System.out.println("Here is the existing information about " + studentNumber + ":");
                            System.out.println(personToEdit);

                            showMenu(2, 1);
                            editOpt = Integer.parseInt(prompt("Enter choice: "));
                            switch (editOpt) {
                                case 1:
                                    String newStudentNumber = prompt("Enter new student number: ");
                                    personToEdit.setId(newStudentNumber);
                                    break;
                                case 2:
                                    String newSurname = prompt("Enter new surname: ");
                                    personToEdit.setLName(newSurname);
                                    break;
                                case 3:
                                    // Handle editing gender
                                    break;
                                case 4:
                                    String newOccupation = prompt("Enter new occupation: ");
                                    personToEdit.setOccupation(newOccupation);
                                    break;
                                case 5:
                                    int newCountryCode = Integer.parseInt(prompt("Enter new country code: "));
                                    personToEdit.setCountryCode(newCountryCode);
                                    break;
                                case 6:
                                    // Handle editing area code
                                    break;
                                case 7:
                                    String newPhoneNumber = prompt("Enter new phone number: ");
                                    personToEdit.setContactNum(newPhoneNumber);
                                    break;
                                case 8:
                                    // Exit from the edit loop
                                    break;
                                default:
                                    System.out.println("Invalid option!");
                            }

                            System.out.println("Modified information:");
                            System.out.println(personToEdit);
                        } while (editOpt != 8);

                    } else {
                        System.out.println("Student with number " + studentNumber + " not found!");
                    }
                    break;
                case 3:
                    deleteEntryFromAseanPhonebook(pb);
                    break;
                case 4:
                    viewSearchAseanPhonebook(pb);
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option!");
            }
            if (exit)
                break;
        }
    }

    private static void deleteEntryFromAseanPhonebook(Phonebook pb) {
        String studentNumber = prompt("Enter student number to delete: ");
        Person personToDelete = pb.getContact(studentNumber);
        if (personToDelete != null) {
            String confirmDelete = prompt("Are you sure you want to delete it [Y/N]? ").toUpperCase();
            if (confirmDelete.equals("Y")) {
                pb.deleteContact(studentNumber);
                System.out.println("Deletion successful");
            } else {
                System.out.println("Deletion did not proceed");
            }
        } else {
            System.out.println("Student with number " + studentNumber + " not found!");
        }
    }

    private static void viewSearchAseanPhonebook(Phonebook pb) {
        while (true) {
            showSearchMenu();
            int searchOpt = Integer.parseInt(prompt("Enter option: "));
            switch (searchOpt) {
                case 1:
                    searchByCountry(pb);
                    break;
                case 2:
                    searchBySurname(pb);
                    break;
                case 3:
                    return; // Go back to main menu
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    private static void showSearchMenu() {
        System.out.println("[1] Search by country");
        System.out.println("[2] Search by surname");
        System.out.println("[3] Go back to main menu");
    }

    private static void searchByCountry(Phonebook pb) {
        // TODO: Implement according to provided instructions
    }

    private static void searchBySurname(Phonebook pb) {
        String surname = prompt("Enter surname: ");
        Person foundPerson = pb.getContactBySurname(surname);
        if (foundPerson != null) {
            System.out.println("Found student:");
            System.out.println(foundPerson);
        } else {
            System.out.println("No student found with the surname: " + surname);
        }
    }

    private static void showMenu(int menuIdx, int inlineTexts) {
        String[] menu = MENUS[menuIdx - 1];
        int count = 0;
        String space = inlineTexts == 0 ? "" : "%-12s";
        String fmt = "[%d] " + space;
        for (int i = 0; i < menu.length; i++) {
            System.out.printf(fmt, i + 1, menu[i]);
            if (inlineTexts != 0) {
                count += 1;
            }
            if (count % inlineTexts == 0) {
                System.out.print("\n");
            }
        }
    }

    private static int convertChoice(int opt) {
        switch (opt) {
            case 1:
                return 60; // Federation of Malaysia
            case 2:
                return 62; // Republic of Indonesia
            case 3:
                return 63; // Republic of the Philippines
            case 4:
                return 65; // Republic of Singapore
            case 5:
                return 66; // Kingdom of Thailand
            case 6:
                return 84; // Socialist Republic of Vietnam
            case 7:
                return 673; // Brunei Darussalam
            case 8:
                return 855; // Kingdom of Cambodia
            case 9:
                return 856; // Lao People’s Democratic Republic
            case 10:
                return 95; // Republic of the Union of Myanmar
            case 11:
                return 670; // Democratic Republic of Timor Leste
            default:
                return 0; // Default value if the choice is not recognized
        }
    }

    private static Person createNewPerson() {
        // ... (rest of the code remains unchanged)
        String id, fname, lname, sex, occupation, contactNum;
        int countryCode, areaCode;
        id = prompt("Enter Contact ID: ");
        fname = prompt("Enter First Name: ");
        lname = prompt("Enter Last Name: ");
        occupation = prompt("Enter Occupation: ");
        sex = prompt("Enter sex/gender: ");
        countryCode = Integer.parseInt(prompt("Enter Country Code: "));
        areaCode = Integer.parseInt(prompt("Enter Area Code: "));
        contactNum = prompt("Enter Contact Number: ");
        return new Person(id, fname, lname, sex, occupation, contactNum, countryCode, areaCode);
    }

    private static String prompt(String phrase) {
        System.out.print(phrase);
        return input.nextLine();
    }
}
