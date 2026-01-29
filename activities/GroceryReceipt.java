package activities;
import java.util.Scanner;

public class GroceryReceipt {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        String strProdName, strAnotherP, strCustomer;
        char cCustomer = 'Y', cAnotherP = 'y';

        double dQty, dBill, dPrice;
        double dTotal, dPay, dChange = 0;

        do{
            dBill = 0.0;

            do{
                System.out.println("\nWelcome to EFM Grocery!");
                System.out.print("Input product name: ");
                strProdName = input.nextLine();
                System.out.print("Input product price: ");
                dPrice = input.nextDouble(); input.nextLine();
                System.out.print("Quantity: ");
                dQty = input.nextDouble(); input.nextLine();

                dTotal = dQty * dPrice;
                System.out.println("Total: " + dTotal);

                dBill = dBill + dTotal;

                System.out.print("Another product? Y/N: ");
                strAnotherP = input.nextLine();
                cAnotherP = strAnotherP.charAt(0);

            } while ((cAnotherP == 'Y') || (cAnotherP == 'y'));

            System.out.println("Bill: " + dBill);
            System.out.print("Payment: ");
            dPay = input.nextDouble(); input.nextLine();

            if(dPay >= dBill){
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

    }
    

