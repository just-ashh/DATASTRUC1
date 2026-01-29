package activities;
import java.util.*;

public class ArtistRegistration{
    public static Scanner input = new Scanner(System.in);
    public static void main(String[] args){

        String strTagname, strArtType, strTools, strCommissionStatus;
        int Experience;
        int registrationCount = 0;

        System.out.println("""
                Artist Registration!!!
                (Free registration for Artist Commission Support!)
                """);

        while(true){
            strTagname = UserInput("Enter username: ");
            strArtType = UserInput("Traditional or Digital?: ");
            strTools = UserInput("Tools used: ");
            Experience = ArtistExperience("How long have you been drawing (in years)?: ");
            strCommissionStatus = UserInput("Commission status?: ");

            System.out.println("Input another registration? (Y/N): ");
            String choice = input.nextLine();

            if(!choice.equalsIgnoreCase("Y")){
                System.out.println("Registration closed. Thank you! <3");
                System.out.println();
                break;
            }

            registrationCount++;
        }
         System.out.println("==== Final Registration ====");
         ArtistRegistered(strTagname, strArtType, strTools, strCommissionStatus, Experience);
         System.out.println();
         System.out.println("Total Registrations: " + registrationCount);

    }
    public static String UserInput(String strInput){
        String Info;
        System.out.println(strInput);
        Info = input.nextLine();
        
        return Info;
    }


    public static int ArtistExperience(String userInput){
        int Time;
        System.out.println(userInput);
        Time = input.nextInt(); input.nextLine();

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