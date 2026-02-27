package activities;

import java.util.*;
import java.io.*;

public class TextToArray {

    public static void main(String[] args) throws Exception {
        File file = new File("DATASTRUC1/activities/Artists.txt");
        Scanner FR = new Scanner(file);
        Scanner sc = new Scanner(System.in);

        String strTagname[] = new String[50];
        String strArtType[] = new String[50];
        String strTools[] = new String[50];
        String strCommissionStatus[] = new String[50];
        int aExperience[] = new int[50];

        int counter = 0;

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

        for (int i = 0; i < counter; i++) {
            System.out.println(i + " " + strTagname[i] + " " + strArtType[i] + " " + strTools[i] + " "
                    + aExperience[i] + " " + strCommissionStatus[i]);
        }

        FR.close();
        sc.close();

    }

    static void userMenu(){

    Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    === MENU ===
                    [1] Add
                    [2] Search
                    [3] Edit
                    [4] Delete
                    [5] Sort
                    [6] List
                    """);
            int userInput = sc.nextInt();
            switch (userInput) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:

                    break;
                default:
                    System.out.println("Invalid input. Try again.");
                    break;
            }
         sc.close();
        }
    }
}
