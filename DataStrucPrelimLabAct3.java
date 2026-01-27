import java.util.*;
public class DataStrucPrelimLabAct3{
    public static void main(String[] args) {
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
                System.out.print("Choice: ");
                int codePlayer = input.nextInt();input.nextLine();
                if (codePlayer == 1){
                    System.out.println("Type: DVD");
                    ++dvdTotal;
                }
                else if(codePlayer == 2){
                    System.out.println("Type: VCD");
                    ++vcdTotal;
                }else{
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
                        int codeCategory = input.nextInt();input.nextLine();
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
                        int Minutes = input.nextInt();input.nextLine();
                        System.out.print("Setting: ");
                        String setting = input.nextLine();

                        System.out.print("""
                                Transaction Type:
                                1. Rental
                                2. Sales    
                                """);
                                System.out.print("Transaction: ");
                                int transactionType = input.nextInt();input.nextLine();
                                if(transactionType == 1){
                                    ++rent;
                                }else{
                                    ++sales;
                                }
                                System.out.print("Price: ");
                                int price = input.nextInt();input.nextLine();
                                System.out.print("Register another? (Y/N) ");
                                cAnotherP = input.next().charAt(0);
                            }while((cAnotherP == 'Y') || (cAnotherP == 'y'));

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