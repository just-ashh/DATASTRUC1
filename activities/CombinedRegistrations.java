package activities;

import java.util.Scanner;

public class CombinedRegistrations {
    static Scanner input = new Scanner(System.in);

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
        } while (true);
    }

    public static void artistRegistration() {

        String strTagname, strArtType, strTools, strCommissionStatus;
        int Experience;
        int registrationCount = 0;

        System.out.println("""
                Artist Registration!!!
                (Free registration for Artist Commission Support!)
                """);

        while (true) {
            strTagname = UserInput("Enter username: ");
            strArtType = UserInput("Traditional or Digital?: ");
            strTools = UserInput("Tools used: ");
            Experience = ArtistExperience("How long have you been drawing (in years)?: ");
            strCommissionStatus = UserInput("Commission status?: ");

            System.out.println("Input another registration? (Y/N): ");
            String choice = input.nextLine();

            if (!choice.equalsIgnoreCase("Y")) {
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

    public static String UserInput(String strInput) {
        String Info;
        System.out.println(strInput);
        Info = input.nextLine();

        return Info;
    }

    public static int ArtistExperience(String userInput) {
        int Time;
        System.out.println(userInput);
        Time = input.nextInt();
        input.nextLine();

        return Time;
    }

    public static void ArtistRegistered(String strTagname, String strArtType, String strTools,
            String strCommissionStatus, int Time) {
        System.out.println();
        System.out.println("Username: " + strTagname);
        System.out.println("Art type: " + strArtType);
        System.out.println("Tools used: " + strTools);
        System.out.println("Experience: " + Time + " years");
        System.out.println("Commission Status: " + strCommissionStatus);
    }

    public static void groceryReceipt() {

        String strProdName, strAnotherP, strCustomer;
        char cCustomer = 'Y', cAnotherP = 'y';

        double dQty, dBill, dPrice;
        double dTotal, dPay, dChange = 0;

        do {
            dBill = 0.0;

            do {
                System.out.println("\nWelcome to EFM Grocery!");
                System.out.print("Input product name: ");
                strProdName = input.nextLine();
                System.out.print("Input product price: ");
                dPrice = input.nextDouble();
                input.nextLine();
                System.out.print("Quantity: ");
                dQty = input.nextDouble();
                input.nextLine();

                dTotal = dQty * dPrice;
                System.out.println("Total: " + dTotal);

                dBill = dBill + dTotal;

                System.out.print("Another product? Y/N: ");
                strAnotherP = input.nextLine();
                cAnotherP = strAnotherP.charAt(0);

            } while ((cAnotherP == 'Y') || (cAnotherP == 'y'));

            System.out.println("Bill: " + dBill);
            System.out.print("Payment: ");
            dPay = input.nextDouble();
            input.nextLine();

            if (dPay >= dBill) {
                dChange = dPay - dBill;
                System.out.println("Change: " + dChange);
                System.out.println("Thank you for shopping!");
            } else {
                System.out.println("Money is not enough!");
            }
            System.out.print("Another customer? Y/N: ");
            strCustomer = input.nextLine();
            cCustomer = strCustomer.charAt(0);
        } while ((cCustomer == 'Y') || (cCustomer == 'y'));

        System.out.println("Grocery program terminating...");
    }

    public static void movieRegistration() {

        int rent, sales, comedy, horror, sciFi, drama, cartoons, dvdTotal, vcdTotal, tapeTotal;
        rent = sales = comedy = horror = sciFi = drama = cartoons = dvdTotal = vcdTotal = tapeTotal = 0;

        char cAnotherP;
        do {
            // while(true)
            System.out.print("""
                    Registration
                    1. DVD
                    2. VCD
                    3. Tape
                    """);
            System.out.print("Choice: ");
            int codePlayer = input.nextInt();
            input.nextLine();
            if (codePlayer == 1) {
                System.out.println("Type: DVD");
                ++dvdTotal;
            } else if (codePlayer == 2) {
                System.out.println("Type: VCD");
                ++vcdTotal;
            } else {
                System.out.println("Type: Tape");
                ++tapeTotal;
            }

            System.out.print("Input title: ");
            String title = input.nextLine();
            System.out.print("""
                    1. Horror
                    2. Sci-fi
                    3. Drama
                    4. Comedy
                    5. Cartoons
                    """);
            System.out.print("Category: ");
            int codeCategory = input.nextInt();
            input.nextLine();
            if (codeCategory == 1) {
                ++horror;
            } else if (codeCategory == 2) {
                ++sciFi;
            } else if (codeCategory == 3) {
                ++drama;
            } else if (codeCategory == 4) {
                ++comedy;
            } else {
                ++cartoons;
            }

            System.out.print("Minutes: ");
            int Minutes = input.nextInt();
            input.nextLine();
            System.out.print("Setting: ");
            String setting = input.nextLine();

            System.out.print("""
                    Transaction Type:
                    1. Rental
                    2. Sales
                    """);
            System.out.print("Transaction: ");
            int transactionType = input.nextInt();
            input.nextLine();
            if (transactionType == 1) {
                ++rent;
            } else {
                ++sales;
            }
            System.out.print("Price: ");
            int price = input.nextInt();
            input.nextLine();
            System.out.print("Register another? (Y/N) ");
            cAnotherP = input.nextLine().charAt(0);
        } while ((cAnotherP == 'Y') || (cAnotherP == 'y'));

        System.out.println("Reports");
        System.out.print("For rent: " + rent);
        System.out.println("For sale: " + sales);
        System.out.println("VCD total: " + vcdTotal);
        System.out.println("DVD total: " + dvdTotal);
        System.out.println("Tape total: " + tapeTotal);
        System.out.println("Horror movies: " + horror);
        System.out.println("Sci-fi movies: " + sciFi);
        System.out.println("Drama movies: " + drama);
        System.out.println("Comedy movies: " + comedy);
        System.out.println("Catoons mvoies: " + cartoons);
    }

}
