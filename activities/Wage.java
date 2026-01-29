package activities;
import java.util.*;
public class Wage {
    public static void main(String[] args) {
    /*int employeeNum;
    String fName, mName, lName;
    double hWorked, hRate, overtimePay, totalDeductions;*/
    int employeeNumber;
    String firstName, middleName, lastName;
    double hoursWorked, hourlyRate, overtimePay, totalDeductions;
    firstName = InputString("First name: ");
    middleName = InputString("Middle name: ");
    lastName = InputString("Last name: ");

    employeeNumber = InputInt("Input employee number: ");

    hoursWorked = InputDouble("Hours Worked: ");
    hourlyRate = InputDouble("Hourly rate: ");
    overtimePay = InputDouble("Overtime pay: ");
    totalDeductions = InputDouble("Total Deductions: ");

    
    double PAY = OverallNet(hourlyRate, hoursWorked, overtimePay, totalDeductions);
    fullOutput(firstName, middleName, lastName, PAY);

    

    
    }
    public static String InputString(String strMessage){
        String Name;
        Scanner input = new Scanner(System.in);
        System.out.print(strMessage);
        Name = input.nextLine();
        return Name;
    }
    public static int InputInt(String strMessage){
        int eNum;
        Scanner input = new Scanner(System.in);
        System.out.print(strMessage);
        eNum = input.nextInt();
        return eNum;
    }
    public static double InputDouble(String strMessage) {
        double overall;
        Scanner input = new Scanner(System.in);
        System.out.print(strMessage);
        overall = input.nextDouble();
        return overall;
    }
    public static double OverallNet(double hourlyRate, double hoursWorked, double overtimePay, double totalDeductions) {
        double NetSalary = (hourlyRate * hoursWorked) + overtimePay- totalDeductions;
        return NetSalary;
    }
    public static void fullOutput(String firstName, String middleName, String lastName, double PAY) {
        System.out.print(firstName + " " + middleName + " " + lastName);
        System.out.println("Your net pay is: " + PAY);
        
        
    }
}