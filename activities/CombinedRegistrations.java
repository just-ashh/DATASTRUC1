package activities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;

public class CombinedRegistrations {

    static Scanner input = new Scanner(System.in);

    static class Question {
        String question;
        String A, B, C;
        char answer;

        public Question(String question, String A, String B, String C, char answer) {
            this.question = question;
            this.A = A;
            this.B = B;
            this.C = C;
            this.answer = answer;
        }
    }

    static ArrayList<String> players = new ArrayList<>();
    static ArrayList<Question> questions = new ArrayList<>();

    public static void main(String[] args) {

        int userChoice;

        do {

            System.out.println("""
                    === Main Menu ===
                    [1] Artist Registration
                    [2] Grocery Receipt
                    [3] Movie Registration
                    [4] Quizzer
                    [0] Exit Program
                    """);

            System.out.print("Choose: ");
            userChoice = input.nextInt();
            input.nextLine();

            switch (userChoice) {

                case 1:
                    artistRegistration();
                    break;

                case 2:
                    groceryReceipt();
                    break;

                case 3:
                    movieRegistration();
                    break;

                case 4:
                    quizMenu();
                    break;

                case 0:
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("Invalid input.");
            }

        } while (userChoice != 0);
    }


    //ARTIST REG
    public static void artistRegistration() {

        String strTagname, strArtType, strTools, strCommissionStatus;
        int experience;
        int registrationCount = 0;

        System.out.println("""
                === Artist Registration ===
                """);

        while (true) {

            strTagname = userInput("Enter username: ");
            strArtType = userInput("Traditional or Digital?: ");
            strTools = userInput("Tools used: ");
            experience = artistExperience("Years of experience?: ");
            strCommissionStatus = userInput("Commission status?: ");

            System.out.println("\n=== Registered Artist ===");
            artistRegistered(strTagname, strArtType, strTools,
                    strCommissionStatus, experience);

            registrationCount++;

            System.out.print("\nAdd another registration? (Y/N): ");
            String choice = input.nextLine();

            if (!choice.equalsIgnoreCase("Y")) {
                break;
            }
        }

        System.out.println("\nTotal Registrations: " + registrationCount);
    }

    public static String userInput(String text) {
        System.out.print(text);
        return input.nextLine();
    }

    public static int artistExperience(String text) {
        System.out.print(text);
        int time = input.nextInt();
        input.nextLine();
        return time;
    }

    public static void artistRegistered(String username,
            String artType,
            String tools,
            String commission,
            int years) {

        System.out.println("Username: " + username);
        System.out.println("Art Type: " + artType);
        System.out.println("Tools: " + tools);
        System.out.println("Experience: " + years + " years");
        System.out.println("Commission Status: " + commission);
    }

//GROCERY REG
    public static void groceryReceipt() {

        char anotherCustomer;

        do {

            double bill = 0;

            char anotherProduct;

            do {

                System.out.println("\n=== Grocery Receipt ===");

                System.out.print("Product Name: ");
                String product = input.nextLine();

                System.out.print("Price: ");
                double price = input.nextDouble();

                System.out.print("Quantity: ");
                double qty = input.nextDouble();
                input.nextLine();

                double total = price * qty;

                bill += total;

                System.out.println("Product Total: " + total);

                System.out.print("Add another product? (Y/N): ");
                anotherProduct = input.nextLine().charAt(0);

            } while (anotherProduct == 'Y' || anotherProduct == 'y');

            System.out.println("\nBill: " + bill);

            System.out.print("Payment: ");
            double payment = input.nextDouble();
            input.nextLine();

            if (payment >= bill) {
                System.out.println("Change: " + (payment - bill));
            } else {
                System.out.println("Insufficient payment.");
            }

            System.out.print("Next customer? (Y/N): ");
            anotherCustomer = input.nextLine().charAt(0);

        } while (anotherCustomer == 'Y' || anotherCustomer == 'y');
    }

    //MOVIE REG
    public static void movieRegistration() {

        int rent = 0, sales = 0;
        int horror = 0, sciFi = 0, drama = 0, comedy = 0, cartoons = 0;
        int dvd = 0, vcd = 0, tape = 0;

        char again;

        do {

            System.out.println("""
                    === Movie Registration ===
                    1. DVD
                    2. VCD
                    3. Tape
                    """);

            System.out.print("Choice: ");
            int type = input.nextInt();
            input.nextLine();

            if (type == 1)
                dvd++;
            else if (type == 2)
                vcd++;
            else
                tape++;

            System.out.print("Movie Title: ");
            String title = input.nextLine();

            System.out.println("""
                    1. Horror
                    2. Sci-fi
                    3. Drama
                    4. Comedy
                    5. Cartoons
                    """);

            System.out.print("Category: ");
            int category = input.nextInt();
            input.nextLine();

            switch (category) {
                case 1:
                    horror++;
                    break;
                case 2:
                    sciFi++;
                    break;
                case 3:
                    drama++;
                    break;
                case 4:
                    comedy++;
                    break;
                default:
                    cartoons++;
            }

            System.out.print("Minutes: ");
            int minutes = input.nextInt();
            input.nextLine();

            System.out.print("Setting: ");
            String setting = input.nextLine();

            System.out.println("""
                    1. Rental
                    2. Sales
                    """);

            System.out.print("Transaction Type: ");
            int transaction = input.nextInt();
            input.nextLine();

            if (transaction == 1)
                rent++;
            else
                sales++;

            System.out.print("Price: ");
            int price = input.nextInt();
            input.nextLine();

            System.out.print("Register another? (Y/N): ");
            again = input.nextLine().charAt(0);

        } while (again == 'Y' || again == 'y');

        System.out.println("\n=== REPORTS ===");

        System.out.println("For Rent: " + rent);
        System.out.println("For Sale: " + sales);

        System.out.println("DVD Total: " + dvd);
        System.out.println("VCD Total: " + vcd);
        System.out.println("Tape Total: " + tape);

        System.out.println("Horror: " + horror);
        System.out.println("Sci-fi: " + sciFi);
        System.out.println("Drama: " + drama);
        System.out.println("Comedy: " + comedy);
        System.out.println("Cartoons: " + cartoons);
    }

    //QUIZ MENU
    public static void quizMenu() {

        loadUsers();
        loadQuestions();

        int choice;

        do {

            System.out.println("""
                    === QUIZ MENU ===
                    [1] Register Player
                    [2] Play Quiz
                    [3] Show Leaderboard
                    [0] Back
                    """);

            System.out.print("Choice: ");
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {

                case 1:
                    registerUser();
                    break;

                case 2:
                    playModule();
                    break;

                case 3:
                    showLeaderboard();
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);
    }

    //QUIZ REG
    public static void registerUser() {

        System.out.print("Enter username: ");
        String name = input.nextLine();

        if (players.contains(name)) {
            System.out.println("Username already exists.");
            return;
        }

        players.add(name);

        try (FileWriter writer = new FileWriter("user.txt", true)) {
            writer.write(name + "\n");
        } catch (IOException e) {
            System.out.println("Error saving user.");
        }

        System.out.println("User registered!");
    }

    public static void loadUsers() {

        players.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader("user.txt"))) {

            String line;

            while ((line = reader.readLine()) != null) {
                players.add(line);
            }

        } catch (IOException e) {
            System.out.println("No users found.");
        }
    }

    public static void loadQuestions() {

        questions.clear();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("questions.txt"), "UTF-8"))) {

            String line;

            while ((line = reader.readLine()) != null) {

                if (line.trim().isEmpty())
                    continue;

                String question = line.substring(3);

                String A = reader.readLine().substring(3);
                String B = reader.readLine().substring(3);
                String C = reader.readLine().substring(3);

                char ans = reader.readLine()
                        .trim()
                        .toUpperCase()
                        .charAt(0);

                questions.add(new Question(question, A, B, C, ans));
            }

        } catch (IOException e) {
            System.out.println("Error loading questions.");
        }
    }

    public static void playModule() {

        if (players.isEmpty()) {
            System.out.println("No registered users.");
            return;
        }

        if (questions.isEmpty()) {
            System.out.println("No questions loaded.");
            return;
        }

        System.out.println("\n=== PLAYERS ===");

        for (int i = 0; i < players.size(); i++) {
            System.out.println((i + 1) + ". " + players.get(i));
        }

        System.out.print("Select player: ");
        int choice = input.nextInt();
        input.nextLine();

        if (choice < 1 || choice > players.size()) {
            System.out.println("Invalid player.");
            return;
        }

        String currentPlayer = players.get(choice - 1);

        Collections.shuffle(questions);

        ArrayList<Question> quiz = new ArrayList<>(questions.subList(
                0,
                Math.min(10, questions.size())));

        ArrayList<Character> userAnswers = new ArrayList<>();
        ArrayList<Boolean> answered = new ArrayList<>();

        for (int i = 0; i < quiz.size(); i++) {
            userAnswers.add(' ');
            answered.add(false);
        }

        Stack<Integer> backStack = new Stack<>();

        int current = 0;
        int score = 0;

        while (true) {

            if (current < 0 || current >= quiz.size()) {
                break;
            }

            Question q = quiz.get(current);

            System.out.println("\nQ" + (current + 1) + ": " + q.question);
            System.out.println("A. " + q.A);
            System.out.println("B. " + q.B);
            System.out.println("C. " + q.C);

            System.out.println("""
                    \n[A/B/C] Answer
                    [N] Next
                    [P] Previous
                    [E] Edit Question
                    [D] Delete Question
                    [Q] Add Question
                    [X] End Quiz
                    """);

            char command = input.nextLine()
                    .trim()
                    .toUpperCase()
                    .charAt(0);

            // ANSWER
            if (command == 'A' || command == 'B' || command == 'C') {

                if (answered.get(current)) {
                    System.out.println("Already answered.");
                    continue;
                }

                userAnswers.set(current, command);
                answered.set(current, true);

                if (command == q.answer) {
                    score++;
                    System.out.println("Correct!");
                } else {
                    System.out.println("Wrong! Correct answer: " + q.answer);
                }
            }

            // NEXT
            else if (command == 'N') {

                if (current + 1 >= quiz.size()) {
                    System.out.println("Last question already.");
                } else {
                    backStack.push(current);
                    current++;
                }
            }

            // PREVIOUS
            else if (command == 'P') {

                if (backStack.isEmpty()) {
                    System.out.println("No previous question.");
                } else {
                    current = backStack.pop();
                }
            }

            // EDIT
            else if (command == 'E') {

                Question edit = quiz.get(current);

                System.out.print("New Question: ");
                edit.question = input.nextLine();

                System.out.print("Choice A: ");
                edit.A = input.nextLine();

                System.out.print("Choice B: ");
                edit.B = input.nextLine();

                System.out.print("Choice C: ");
                edit.C = input.nextLine();

                System.out.print("Correct Answer: ");
                edit.answer = input.nextLine()
                        .toUpperCase()
                        .charAt(0);

                updateQuestionFile();

                System.out.println("Question updated.");
            }

            // DELETE
            else if (command == 'D') {

                questions.remove(quiz.get(current));
                quiz.remove(current);

                userAnswers.remove(current);
                answered.remove(current);

                updateQuestionFile();

                System.out.println("Question deleted.");

                if (quiz.isEmpty()) {
                    break;
                }

                if (current >= quiz.size()) {
                    current = quiz.size() - 1;
                }
            }

            // ADD
            else if (command == 'Q') {

                System.out.print("Question: ");
                String question = input.nextLine();

                System.out.print("Choice A: ");
                String A = input.nextLine();

                System.out.print("Choice B: ");
                String B = input.nextLine();

                System.out.print("Choice C: ");
                String C = input.nextLine();

                System.out.print("Correct Answer: ");
                char ans = input.nextLine()
                        .toUpperCase()
                        .charAt(0);

                Question newQ = new Question(question, A, B, C, ans);

                questions.add(newQ);
                quiz.add(newQ);

                userAnswers.add(' ');
                answered.add(false);

                updateQuestionFile();

                System.out.println("Question added.");
            }

            // EXIT
            else if (command == 'X') {
                break;
            }

            else {
                System.out.println("Invalid input.");
            }
        }

        System.out.println("\nFinal Score: " + score + "/" + quiz.size());

        saveScore(currentPlayer, score);

        System.out.println("\n=== LEADERBOARD ===");
        showLeaderboard();
    }


    public static void saveScore(String name, int score) {

        try (FileWriter writer = new FileWriter("leaderboard.txt", true)) {

            writer.write(name + " - " + score + "\n");

        } catch (IOException e) {
            System.out.println("Error saving score.");
        }
    }

    public static void showLeaderboard() {

        try (BufferedReader reader = new BufferedReader(
                new FileReader("leaderboard.txt"))) {

            String line;
            int rank = 1;

            while ((line = reader.readLine()) != null) {

                System.out.println(rank + ". " + line);

                rank++;
            }

        } catch (IOException e) {
            System.out.println("No leaderboard found.");
        }
    }


    public static void updateQuestionFile() {

        try (FileWriter writer = new FileWriter("questions.txt")) {

            for (Question q : questions) {

                writer.write("Q: " + q.question + "\n");
                writer.write("A. " + q.A + "\n");
                writer.write("B. " + q.B + "\n");
                writer.write("C. " + q.C + "\n");
                writer.write(q.answer + "\n");
            }

        } catch (IOException e) {
            System.out.println("Error updating file.");
        }
    }
}