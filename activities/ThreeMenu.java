import java.util.Scanner;

public class ThreeMenu {

    public static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        int userChoice;

        do {
            System.out.println("""
                    === Main Menu ===
                    [1] Artist Registration
                    [2] Grocery Receipt
                    [3] Movie Registration
                    [0] Exit Program...
                    """);

            System.out.println("Choose a following: ");
        try{
            userChoice = input.nextInt();
            input.nextLine();

            switch (userChoice) {

                case 0:
                    System.out.println("Now exiting program...");
                    System.exit(0);
                    break;
                case 1:
                    artistRegistration();
                    break;
                case 2:
                    groceryReceipt();
                    break;
                case 3:
                    movieRegistration();
                    break;
                default:
                    System.out.println("Invalid input.");
                    input.nextLine();
                    break;
            }
        } catch (Exception e){
            System.out.println("Invalid.");
            e.printStackTrace();
        }

}while(true);

}

}


