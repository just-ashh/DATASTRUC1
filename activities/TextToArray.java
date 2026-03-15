
import java.util.*;
import java.io.*;

public class TextToArray {

    public static void main(String[] args) throws Exception {
        //open txt file
        File file = new File("DATASTRUC1/activities/Artists.txt");
        Scanner FR = new Scanner(file);
        Scanner sc = new Scanner(System.in);

        //array lists (max of 50 entries)
        String strTagname[] = new String[50];
        String strArtType[] = new String[50];
        String strTools[] = new String[50];
        String strCommissionStatus[] = new String[50];
        int aExperience[] = new int[50];

        int counter = 0; //artists inputted or registered

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

    static void userMenu(String strTagname[], String strArtType[], String strTools[], String strCommissionStatus[], int aExperience[], int counter){

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
                    [0] Exit program
                    """);
            int userInput = sc.nextInt();
            switch (userInput) {
                case 1: //ADDING
                    if(counter >= 50){
                        System.out.println("Array full! Cannot add more artists.");
                        break;
                    }

                    //ask where to add said artist, first, middle, last
                    System.out.println("Where to insert?");
                    System.out.println("""
                            [1] First Index
                            [2] Middle Index
                            [3] Last Index
                            """);

                            int positionChoice = sc.nextInt(); sc.nextLine();
                    
                    int insertIndex = counter; //default to last index
                    if(positionChoice == 1) insertIndex = 0;
                    else if(positionChoice == 2){
                        System.out.println("Enter index (0 to " + counter + "): ");
                        insertIndex = sc.nextInt(); sc.nextLine();
                        if(insertIndex < 0) insertIndex = 0;
                        if(insertIndex > counter) insertIndex = counter;
                    }
                    
                    //shift names to the right for space making
                    for (int i = counter; i > insertIndex; i--){
                        strTagname[i] = strTagname[i - 1];
                        strArtType[i] = strArtType[i - 1];
                        strTools[i] = strTools[i - 1];
                        aExperience[i] = aExperience[i - 1];
                        strCommissionStatus[i] = strCommissionStatus[i - 1]; 
                    }
                    //ask for new data for artists
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

                    //add to txt file to updt
                    try{
                        FileWriter fw = new FileWriter("DATASTRUC1/activities/Artists.txt");
                        for(int i = 0; i < counter; i++){
                            fw.write(strTagname[i] + "\n");
                            fw.write(strArtType[i] + "\n");
                            fw.write(strTools[i] + "\n");
                            fw.write(aExperience[i] + "\n");
                            fw.write(strCommissionStatus[i] + "\n");
                        }                   
                            fw.close();
                            System.out.println("Artists.txt updated successfully!");
                        }
                        catch (IOException e){
                            System.out.println("Error occured: " + e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("Item searched.");
                    break;
                case 3:
                    System.out.println("Item edits.");
                    break;
                case 4:
                    System.out.println("Item deleted.");
                    break;
                case 5:
                    System.out.println("Item sorted.");
                    break;
                case 6:
                    System.out.println("=== Artist List ===");
                    for(int i = 0; i < counter; i++){
                        System.out.println(i + " " + strTagname[i] + " " + strArtType[i] + " " + strTools[i] + " " + aExperience[i] + " " + strCommissionStatus[i]);
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
