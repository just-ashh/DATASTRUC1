package activities;

import java.util.*;
import java.io.*;

public class ArtistsRegistration2 {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        // open txt file
        File file = new File("DATASTRUC1/activities/Artists.txt");
        Scanner FR = new Scanner(file);

        // array lists (max of 50 entries)
        String strTagname[] = new String[50];
        String strArtType[] = new String[50];
        String strTools[] = new String[50];
        String strCommissionStatus[] = new String[50];
        int aExperience[] = new int[50];

        int counter = 0; // artists inputted or registered

        while (FR.hasNextLine()) {
            if (counter >= 50)
                break;
            strTagname[counter] = FR.nextLine();
            strArtType[counter] = FR.nextLine();
            strTools[counter] = FR.nextLine();
            aExperience[counter] = Integer.parseInt(FR.nextLine());
            strCommissionStatus[counter] = FR.nextLine();
            counter++;
        }

        FR.close();

        userMenu(strTagname, strArtType, strTools, strCommissionStatus, aExperience, counter);

        sc.close();

    }

    static void userMenu(String strTagname[], String strArtType[], String strTools[], String strCommissionStatus[],
            int aExperience[], int counter) throws Exception {
        while (true) {
            System.out.println("""
                    === MENU ===
                    [1] Add
                    [2] Search
                    [3] Edit
                    [4] Delete
                    [5] Sort
                    [6] List
                    [0] Exit program
                    """);
            int userInput = sc.nextInt();
            sc.nextLine();
            switch (userInput) {

                // ADDING INDEX
                case 1:
                    if (counter >= 50) {
                        System.out.println("Array full! Cannot add more artists.");
                        break;
                    }

                    // ask where to add said artist, first, middle, last
                    System.out.println("Where to insert?");
                    System.out.println("""
                            [1] First Index
                            [2] Middle Index
                            [3] Last Index
                            """);

                    int positionChoice = sc.nextInt();
                    sc.nextLine();

                    int insertIndex = counter; // default to last index
                    if (positionChoice == 1)
                        insertIndex = 0;
                    else if (positionChoice == 2) {
                        System.out.println("Enter index (0 to " + counter + "): ");
                        insertIndex = sc.nextInt();
                        sc.nextLine();
                        if (insertIndex < 0)
                            insertIndex = 0;
                        if (insertIndex > counter)
                            insertIndex = counter;
                    }

                    // shift names to the right for space making
                    for (int i = counter; i > insertIndex; i--) {
                        strTagname[i] = strTagname[i - 1];
                        strArtType[i] = strArtType[i - 1];
                        strTools[i] = strTools[i - 1];
                        aExperience[i] = aExperience[i - 1];
                        strCommissionStatus[i] = strCommissionStatus[i - 1];
                    }
                    // ask for new data for artists
                    System.out.println("Enter Tagname: ");
                    strTagname[insertIndex] = sc.nextLine();
                    System.out.println("Enter Art Type: ");
                    strArtType[insertIndex] = sc.nextLine();
                    System.out.println("Enter Tools: ");
                    strTools[insertIndex] = sc.nextLine();
                    System.out.println("Enter Experience (years): ");
                    aExperience[insertIndex] = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.println("Enter Commission Status: ");
                    strCommissionStatus[insertIndex] = sc.nextLine();

                    counter++; // increase total count
                    System.out.println("Artist added at index " + insertIndex + "!");

                    // add to txt file to updt
                    try {
                        FileWriter fw = new FileWriter("DATASTRUC1/activities/Artists.txt");
                        for (int i = 0; i < counter; i++) {
                            fw.write(strTagname[i] + "\n");
                            fw.write(strArtType[i] + "\n");
                            fw.write(strTools[i] + "\n");
                            fw.write(aExperience[i] + "\n");
                            fw.write(strCommissionStatus[i] + "\n");
                        }
                        fw.close();
                        System.out.println("Artists.txt updated successfully!");
                    } catch (IOException e) {
                        System.out.println("Error occured: " + e.getMessage());
                    }
                    break;

                // SEARCHING INDEX
                case 2:
                    System.out.println("""
                            [1] Search by index
                            [2] Search by keyword
                            """);

                    int searchChoice = -1;

                    while (true) {
                        try {
                            System.out.print("Enter 1 or 2: ");
                            searchChoice = sc.nextInt();
                            sc.nextLine();

                            if (searchChoice != 1 && searchChoice != 2) {
                                System.out.println("Invalid choice. Choose only 1 or 2.");
                                continue;
                            }

                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter number.");
                            sc.nextLine();
                        }

                    }
                    if (searchChoice == 1) { // Search by INDEX

                        int index = -1;

                        while (true) {
                            try {
                                System.out.print("Enter index (0 - " + (counter - 1) + "): ");
                                index = sc.nextInt();
                                sc.nextLine();

                                if (index < 0 || index >= counter) { // if input number out of range of listed artists
                                    System.out.println("Invalid index. Try again!");
                                    continue;
                                }
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input. Please enter a valid number.");
                                sc.nextLine();
                            }
                        }

                        System.out.println("=== Artist Found at index: " + index + " ===");
                        System.out.println("Tagname: " + strTagname[index]);
                        System.out.println("Art type: " + strArtType[index]);
                        System.out.println("Tools: " + strTools[index]);
                        System.out.println("Experience: " + aExperience[index] + " years");
                        System.out.println("Commission status: " + strCommissionStatus[index]);

                    } else if (searchChoice == 2) { // search by KEYWORD
                        System.out.print("Enter Artist's name to search: ");
                        String searchName = sc.nextLine().toLowerCase();

                        boolean found = false; // set default to not found

                        for (int i = 0; i < counter; i++) {
                            if (strTagname[i] != null && strTagname[i].toLowerCase().contains(searchName)) {
                                System.out.println("=== Artist found at number " + i + " ===");
                                System.out.println("Tagname: " + strTagname[i]);
                                System.out.println("Art type: " + strArtType[i]);
                                System.out.println("Tools: " + strTools[i]);
                                System.out.println("Experience: " + aExperience[i] + " years");
                                System.out.println("Commission status: " + strCommissionStatus[i]);
                                found = true; // if found true, display
                            }
                        }

                        if (!found) {
                            System.out.println("Artist not found."); // display if artist hasnt been found
                        }

                    } else {
                        System.out.println("Invalid choice.");
                        return;
                    }
                    break;

                // EDITING INDEX
                case 3:
                    int editIndex = -1;

                    while (true) {
                        try {
                            System.out.println("Enter an index to edit (0 - " + (counter - 1) + "): ");
                            editIndex = sc.nextInt();
                            sc.nextLine();

                            if (editIndex < 0 || editIndex >= counter) {
                                System.out.println("Invalid index. Try a valid number.");
                                continue;
                            }

                            break;

                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid number.");
                            sc.nextLine();
                        }
                    }

                    System.out.println("=== Artist selected ===");
                    System.out.println("Tagname: " + strTagname[editIndex]);
                    System.out.println("Art type: " + strArtType[editIndex]);
                    System.out.println("Tools: " + strTools[editIndex]);
                    System.out.println("Experience: " + aExperience[editIndex] + " years");
                    System.out.println("Commission status: " + strCommissionStatus[editIndex]);

                    System.out.println("Would you like to edit this entry? (Y/N): ");
                    String choice = sc.nextLine().toLowerCase();

                    if (choice.equals("y") || choice.equals("yes")) {
                        // Overwriting
                        System.out.println("Enter username: ");
                        strTagname[editIndex] = sc.nextLine();

                        System.out.println("Enter Art Type (Digital/Traditional): ");
                        strArtType[editIndex] = sc.nextLine();

                        System.out.println("Enter tools: ");
                        strTools[editIndex] = sc.nextLine();

                        System.out.println("Enter Experience in years: ");
                        aExperience[editIndex] = sc.nextInt();
                        sc.nextLine();

                        System.out.println("Enter Commission status: ");
                        strCommissionStatus[editIndex] = sc.nextLine();

                        System.out.println("Artist updated successfully!");

                        try {
                            FileWriter fw = new FileWriter("DATASTRUC1/activities/Artists.txt");
                            for (int i = 0; i < counter; i++) {
                                fw.write(strTagname[i] + "\n");
                                fw.write(strArtType[i] + "\n");
                                fw.write(strTools[i] + "\n");
                                fw.write(aExperience[i] + "\n");
                                fw.write(strCommissionStatus[i] + "\n");
                            }

                            fw.close();
                            System.out.println("File updated successfully!");
                        } catch (IOException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Edit cancelled.");
                    }

                    break;
                // REMOVE AN ITEM/INDEX
                case 4:
                    System.out.println("Enter an artist's name to remove: ");
                    String deleteIndex = sc.nextLine().toLowerCase();

                    boolean foundIndex = false;

                    for (int i = 0; i < counter; i++) {
                        if (strTagname[i] != null && strTagname[i].toLowerCase().contains(deleteIndex)) {

                            System.out.println("=== Artist selected ===");
                            System.out.println("Tagname: " + strTagname[i]);
                            System.out.println("Art type: " + strArtType[i]);
                            System.out.println("Tools: " + strTools[i]);
                            System.out.println("Experience: " + aExperience[i] + " years");
                            System.out.println("Commission status: " + strCommissionStatus[i]);

                            // Show artist selected to delete
                            System.out.println("Do you want to remove selected artist? (Y/N): ");
                            String userChoice = sc.nextLine().toLowerCase();

                            // Shift list to the left to remove the new space
                            if (userChoice.equals("y") || userChoice.equals("yes")) {
                                for (int j = i; j < counter - 1; j++) {
                                    strTagname[j] = strTagname[j + 1];
                                    strArtType[j] = strArtType[j + 1];
                                    strTools[j] = strTools[j + 1];
                                    aExperience[j] = aExperience[j + 1];
                                    strCommissionStatus[j] = strCommissionStatus[j + 1];
                                }
                                // Clear the last slot
                                strTagname[counter - 1] = null;
                                strArtType[counter - 1] = null;
                                strTools[counter - 1] = null;
                                aExperience[counter - 1] = 0;
                                strCommissionStatus[counter - 1] = null;

                                counter--; // removes 1 from the counter

                                System.out.println("Artist removed successfully.");

                                // rewrite txt file
                                try {
                                    FileWriter fw = new FileWriter("DATASTRUC1/activities/Artists.txt");
                                    for (int k = 0; k < counter; k++) {
                                        fw.write(strTagname[k] + "\n");
                                        fw.write(strArtType[k] + "\n");
                                        fw.write(strTools[k] + "\n");
                                        fw.write(aExperience[k] + "\n");
                                        fw.write(strCommissionStatus[k] + "\n");
                                    }

                                    fw.close();
                                    System.out.println("File updated successfully!");

                                } catch (IOException e) {
                                    System.out.println("Error occured: " + e.getMessage());
                                }
                            } else {
                                System.out.println("Deletion cancelled.");
                            }

                            foundIndex = true;
                            break;
                        }
                    }

                    if (!foundIndex) {
                        System.out.println("Artist not found.");
                    }

                    break;

                // SORTING BY USERNAME
                case 5:
                    System.out.println("""
                            Sort by username:
                            [1] Ascending (A-Z)
                            [2] Descending (Z-A)
                            """);

                            int userChoice = sc.nextInt(); sc.nextLine();
                    for (int i = 0; i < counter - 1; i++) {
                        for (int j = 0; j < counter - i - 1; j++) {

                            if (strTagname[j] != null && strTagname[j + 1] != null &&
                                ((userChoice == 1 && strTagname[j].compareToIgnoreCase(strTagname[j + 1]) > 0) ||
                                 (userChoice == 2 && strTagname[j].compareToIgnoreCase(strTagname[j + 1]) < 0)
                                )) {

                                String tempTag = strTagname[j];
                                strTagname[j] = strTagname[j + 1];
                                strTagname[j + 1] = tempTag;

                                String tempType = strArtType[j];
                                strArtType[j] = strArtType[j + 1];
                                strArtType[j + 1] = tempType;

                                String tempTools = strTools[j];
                                strTools[j] = strTools[j + 1];
                                strTools[j + 1] = tempTools;

                                int tempExp = aExperience[j];
                                aExperience[j] = aExperience[j + 1];
                                aExperience[j + 1] = tempExp;

                                String tempStatus = strCommissionStatus[j];
                                strCommissionStatus[j] = strCommissionStatus[j + 1];
                                strCommissionStatus[j + 1] = tempStatus;
                            }
                        }
                    }

                    System.out.println("Sorting done...");

                    // Save updated sorted file
                    try {
                        FileWriter fw = new FileWriter("DATASTRUC1/activities/Artists.txt");
                        for (int k = 0; k < counter; k++) {
                            fw.write(strTagname[k] + "\n");
                            fw.write(strArtType[k] + "\n");
                            fw.write(strTools[k] + "\n");
                            fw.write(aExperience[k] + "\n");
                            fw.write(strCommissionStatus[k] + "\n");
                        }

                        fw.close();
                        if(userChoice == 1)
                            System.out.println("Artist sorted in an ascending order!");
                        else
                            System.out.println("Artist sorted in a descending order!");
                    } catch (IOException e) {
                        System.out.println("Error occured: " + e.getMessage());
                    }
                    break;

                // REVEALING LISTING
                case 6:
                    System.out.println("=== Artist List ===");
                    for (int i = 0; i < counter; i++) {
                        System.out.println(i + " " + strTagname[i] + " " + strArtType[i] + " " + strTools[i] + " "
                                + aExperience[i] + " " + strCommissionStatus[i]);
                    }
                    break;
                case 0:
                    System.out.println("Exiting program...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input. Try again.");
                    break;
            }
        }
    }

}
