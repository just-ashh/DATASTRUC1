package activities;
import java.util.*;
import java.io.*;

public class TextToArray {
  
    public static void main(String[] args) throws Exception{
        File file = new File("Artists.txt");
        Scanner FR = new Scanner(file);
        Scanner scan = new Scanner(System.in);

        String strTagname[] = new String[50];
        String strArtType[] = new String[50];
        String strTools[] = new String[50];
        String strCommissionStatus[] = new String[50];
        int aExperience[] = new int[50];

        int counter = 0;

        while(FR.hasNextLine()){
            if (counter >= 50) break;
            strTagname[counter] = FR.nextLine();
            strArtType[counter] = FR.nextLine();
            strTools[counter] = FR.nextLine();
            aExperience[counter] = Integer.parseInt(FR.nextLine());
            strCommissionStatus[counter] = FR.nextLine();
            counter++;
        }
        for(int i = 0; i < counter; i++){
            System.out.println(i + " " + strTagname[i] + " " + strArtType[i] + " " + strTools[i] + " "
                                + aExperience[i] + " " + strCommissionStatus[i]);
        }

        FR.close();
        scan.close();

    }
}

