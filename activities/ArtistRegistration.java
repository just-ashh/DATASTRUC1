package activities;
import java.util.*;

public class ArtistRegistration{
    public static void main(String[] args){
        String strTagname, strArtType, strTools, strCommissionStatus;

        int Experience;

        System.out.println("""
                Artist Registration!!!
                (Free registration for Artist Commission Support!)
                """);
        strTagname = UserInput("Enter username: ");
        strArtType = UserInput("Traditional or Digital?: ");
        strTools = UserInput("Tools used: ");
        Experience = ArtistExperience("How long have you been drawing (in years)?: ");
        strCommissionStatus = UserInput("Commission status?: ");

        ArtistRegistered(strTagname, strArtType, strTools, strCommissionStatus, Experience);

    }

    public static String UserInput(String strInput){
        String Info;
        Scanner input = new Scanner(System.in);
        System.out.println(strInput);
        Info = input.nextLine();
        
        return Info;

    }


    public static int ArtistExperience(String userInput){
        int Time;
        Scanner input = new Scanner(System.in);
        System.out.println(userInput);
        Time = input.nextInt();

        return Time;

    }
    

    public static void ArtistRegistered(String strTagname, String strArtType, String strTools, String strCommissionStatus, int Time){
        System.out.println();
        System.out.println("Username: " + strTagname);
        System.out.println("Art type: " + strArtType);
        System.out.println("Tools used: " + strTools);
        System.out.println("Experience: " + Time + " years");
        System.out.println("Commission Status: " + strCommissionStatus);

    }



}