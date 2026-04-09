package lab_Act11_MIDTERMS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

class Question {
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

public class VocaloidQuestionnaire {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<String> players = new ArrayList<>();
    static ArrayList<Question> questions = new ArrayList<>();

    public static void main(String[] args) {

        loadUsers();
        loadQuestions();

        int choice;

        do {
            System.out.println("""
                    === MENU ===
                    [1] Player Registration
                    [2] Play Module
                    [3] Leaderboard
                    [0] Exit
                    """);

            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // clear buffer

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
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 0);
    }

    // register player
    public static void registerUser() {
        System.out.print("Enter username: ");
        String name = sc.nextLine();

        if (players.contains(name)) {
            System.out.println("Username already exists!");
            return;
        }

        players.add(name);
        saveUser(name);
        System.out.println("User registered successfully!");
    }

    public static void saveUser(String name) {
        try (FileWriter writer = new FileWriter("user.txt", true)) {
            writer.write(name + "\n");
        } catch (IOException e) {
            System.out.println("Error saving user.");
        }
    }

    public static void loadUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader("user.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                players.add(line);
            }
        } catch (IOException e) {
            System.out.println("No existing users found.");
        }
    }

    // load questions from the txt
    public static void loadQuestions() {
        try (BufferedReader reader = new BufferedReader(new FileReader("questions.txt"))) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty())
                    continue;

                // parse question format
                String question = line.substring(line.indexOf(":") + 2); // skip "Q1: "
                String A = reader.readLine().substring(3); // skip "A. "
                String B = reader.readLine().substring(3); // skip "B. "
                String C = reader.readLine().substring(3); // skip "C. "
                char ans = reader.readLine().trim().toUpperCase().charAt(0);

                questions.add(new Question(question, A, B, C, ans));
            }

        } catch (IOException e) {
            System.out.println("Error loading questions.");
        }
    }

    // play questionnaire program
    public static void playModule() {

        if (players.isEmpty()) {
            System.out.println("No user registered yet!");
            return;
        }

        if (questions.isEmpty()) {
            System.out.println("No questions loaded!");
            return;
        }

        // display all players registered
        System.out.println("\nPlayers:");
        for (int i = 0; i < players.size(); i++) {
            System.out.println((i + 1) + ". " + players.get(i));
        }

        // select registered player
        System.out.print("Select player number: ");
        int choice = sc.nextInt();
        sc.nextLine();

        if (choice < 1 || choice > players.size()) {
            System.out.println("Invalid player selection.");
            return;
        }

        String currentPlayer = players.get(choice - 1);
        System.out.println("Player selected: " + currentPlayer);

        // randomize the 30 questions
        Collections.shuffle(questions);

        FileWriter logWriter = null;
        try {
            logWriter = new FileWriter(currentPlayer + "Usersession.txt", true); // one log per player
            logWriter.write("=== Quiz session for " + currentPlayer + " ===\n");
        } catch (IOException e) {
            System.out.println("Error creating session log.");
        }

        int score = 0;
        System.out.println("\nStarting quiz...");

        // ask 10 questions
        for (int i = 0; i < 10 && i < questions.size(); i++) {

            Question q = questions.get(i);

            System.out.println("\nQ" + (i + 1) + ": " + q.question);
            System.out.println("A. " + q.A);
            System.out.println("B. " + q.B);
            System.out.println("C. " + q.C);

            System.out.print("Answer: ");
            char answer;

            try {
                answer = sc.nextLine().toUpperCase().charAt(0);
            } catch (Exception e) {
                answer = ' ';
            }

            // ensure answers are correctly inputted
            while (answer != 'A' && answer != 'B' && answer != 'C') {
                System.out.print("Invalid! Enter A, B, or C: ");
                answer = sc.nextLine().toUpperCase().charAt(0);
            }

            // log and write in player inputs
            try {
                logWriter.write("Q" + (i + 1) + ": " + q.question + "\n");
                logWriter.write("A. " + q.A + "\n");
                logWriter.write("B. " + q.B + "\n");
                logWriter.write("C. " + q.C + "\n");
                logWriter.write("Player answer: " + answer + "\n");

                if (answer == q.answer) {
                    logWriter.write("Result: Correct\n\n");
                } else {
                    logWriter.write("Result: Wrong (Correct answer: " + q.answer + ")\n\n");
                }
            } catch (IOException e) {
                System.out.println("Error writing to session log.");
            }

            if (answer == q.answer) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Wrong! Correct answer: " + q.answer);
            }
        }

        System.out.println("\nFinal Score: " + score + "/10");

        try {
            logWriter.write("Final Score: " + score + "/10\n");
            logWriter.write("===============================\n\n");
            logWriter.close();
        } catch (IOException e) {
            System.out.println("Error closing session log.");
        }

        saveScore(currentPlayer, score);
    }

    // save scores
    public static void saveScore(String name, int score) {
        try (FileWriter writer = new FileWriter("leaderboard.txt", true)) {
            writer.write(name + " - " + score + "\n");
            System.out.println("Score saved!");
        } catch (IOException e) {
            System.out.println("Error saving score.");
        }
    }

    // display leaderboard scores
    public static void showLeaderboard() {
        System.out.println("\n== LEADERBOARD ==");

        try (BufferedReader reader = new BufferedReader(new FileReader("leaderboard.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("No scores saved yet.");
        }
    }
}