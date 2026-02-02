package activities;

import java.util.*;
import java.io.*;

public class MovieRegistrationWithWriter{
    public static void main(String[] args) throws Exception {

       File oFile=new File("MovieRegistration.txt");
       FileWriter FW=new FileWriter(oFile);
       Scanner input = new Scanner (System.in);
       int rent, sales, comedy, horror, sciFi, drama, cartoons, dvdTotal, vcdTotal, tapeTotal;
        rent = sales = comedy = horror = sciFi = drama = cartoons = dvdTotal = vcdTotal = tapeTotal = 0;

        char cAnotherP;
        do{
            //while(true)
        System.out.print("""
                Registration
                1. DVD
                2. VCD
                3. Tape
                """);
        FW.write("""
                Registration
                1. DVD
                2. VCD
                3. Tape
                """);
                System.out.print("Choice: ");
                FW.write("Choice: ");

                int codePlayer = input.nextInt();input.nextLine();
                FW.write(codePlayer + "\n");

                if (codePlayer == 1){
                    System.out.println("Type: DVD");
                    FW.write("\nType: DVD");
                    ++dvdTotal;
                }
                else if(codePlayer == 2){
                    System.out.println("Type: VCD");
                    FW.write("\nType: VCD");
                    ++vcdTotal;
                }else{
                    System.out.println("Type: Tape");
                    FW.write("\nType: Tape");
                    ++tapeTotal;
                }

                System.out.print("Input title: ");
                FW.write("\nInput title: ");
                String title = input.nextLine();
                FW.write(title + "\n");

                System.out.print("""
                        1. Horror
                        2. Sci-fi
                        3. Drama
                        4. Comedy
                        5. Cartoons
                        """);
                FW.write("""
                        1. Horror
                        2. Sci-fi
                        3. Drama
                        4. Comedy
                        5. Cartoons
                        """);

                        System.out.print("Category: ");
                        FW.write("Category: ");

                        int codeCategory = input.nextInt();input.nextLine();
                        FW.write(codeCategory + "\n");

                        if (codeCategory == 1){
                            ++horror;
                        }else if (codeCategory == 2){
                            ++sciFi;
                        }else if (codeCategory == 3){
                            ++drama;
                        }else if (codeCategory == 4){
                            ++comedy;
                        }else{
                            ++cartoons;
                        }

                        System.out.print("Minutes: ");
                        FW.write("\nMinutes: ");

                        int Minutes = input.nextInt();input.nextLine();
                        FW.write(Minutes + "\n");

                        System.out.print("Setting: ");
                        FW.write("Setting: ");

                        String setting = input.nextLine();
                        FW.write(setting + "\n");

                        System.out.print("""
                                Transaction Type:
                                1. Rental
                                2. Sales    
                                """);
                        FW.write("""
                                Transaction Type:
                                1. Rental
                                2. Sales    
                                """);        

                                System.out.print("Transaction: ");
                                FW.write("Transaction: ");

                                int transactionType = input.nextInt();input.nextLine();
                                FW.write(transactionType + "\n");

                                if(transactionType == 1){
                                    ++rent;
                                }else{
                                    ++sales;
                                }
                                System.out.print("Price: ");
                                FW.write("Price: ");

                                int price = input.nextInt();input.nextLine();
                                FW.write(price + "\n");
            
                                System.out.print("Register another? (Y/N) ");
                                FW.write("Register another? (Y/N) ");

                                cAnotherP = input.next().charAt(0);
                            }while((cAnotherP == 'Y') || (cAnotherP == 'y'));

                                System.out.println("Reports");
                                FW.write("\n====== Reports ======\n");
                                System.out.print("For rent: " + rent);
                                FW.write("For rent: " + rent);
                                System.out.println("For sale: " + sales);
                                FW.write("\nFor sale: " + sales);
                                System.out.println("VCD total: " + vcdTotal);
                                FW.write("\nVCD total: " + vcdTotal);
                                System.out.println("DVD total: " + dvdTotal);
                                FW.write("\nDVD total: " + dvdTotal);
                                System.out.println("Tape total: " + tapeTotal);
                                FW.write("\nTape total: " + tapeTotal);
                                System.out.println("Horror movies: " + horror);
                                FW.write("\nHorror movies: " + horror);
                                System.out.println("Sci-fi movies: " + sciFi);
                                FW.write("\nSci-fi movies: " + sciFi);
                                System.out.println("Drama movies: " + drama);
                                FW.write("\nDrama movies: " + drama);
                                System.out.println("Comedy movies: " + comedy);
                                FW.write("\nComedy movies: " + comedy);
                                System.out.println("Catoons mvoies: " + cartoons);
                                FW.write("\nCatoons mvoies: " + cartoons);
        input.close();
        FW.close();
    }
}