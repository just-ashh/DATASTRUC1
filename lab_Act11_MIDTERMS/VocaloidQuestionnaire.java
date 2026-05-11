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
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream("questions.txt"), "UTF-8"))) {

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

        System.out.println("\nPlayers:");
        for (int i = 0; i < players.size(); i++) {
            System.out.println((i + 1) + ". " + players.get(i));
        }

        System.out.print("Select player number: ");
        int choice = sc.nextInt();
        sc.nextLine();

        if (choice < 1 || choice > players.size()) {
            System.out.println("Invalid player selection.");
            return;
        }

        String currentPlayer = players.get(choice - 1);
        System.out.println("Player selected: " + currentPlayer);

        Collections.shuffle(questions);
        ArrayList<Question> quiz = new ArrayList<>(questions.subList(0, Math.min(10, questions.size())));

        Stack<Integer> backStack = new Stack<>();
        Stack<Integer> nextStack = new Stack<>();

        ArrayList<Character> userAnswers = new ArrayList<>();
        ArrayList<Boolean> answered = new ArrayList<>();

        for (int i = 0; i < quiz.size(); i++) {
            userAnswers.add(' ');
            answered.add(false);
        }
        int current = 0;
        int score = 0;

        FileWriter logWriter = null;
        try {
            logWriter = new FileWriter(currentPlayer + "Usersession.txt", true);
            logWriter.write("=== Quiz session for " + currentPlayer + " ===\n");
        } catch (IOException e) {
            System.out.println("Error creating session log.");
        }

        System.out.println("\nStarting quiz...");

        while (true) {

            if (current < 0 || current >= quiz.size()) {
                System.out.println("No more questions available.");
                break;
            }

            Question q = quiz.get(current);

            System.out.println("\nQ" + (current + 1) + ": " + q.question);
            System.out.println("A. " + q.A);
            System.out.println("B. " + q.B);
            System.out.println("C. " + q.C);

            if (answered.get(current)) {
                System.out.println("Your answer: " + userAnswers.get(current));
                if (userAnswers.get(current) == q.answer) {
                    System.out.println("Result: Correct!");
                } else {
                    System.out.println("Result: Wrong! (Correct: " + q.answer + ")");
                }
            }

            System.out.println("""
                    \n[A/B/C] Answer
                    [N] Next
                    [P] Previous
                    [E] Edit Question
                    [D] Delete Question
                    [Q] Add Question
                    [X] End Quiz
                    """);

            String line = sc.nextLine().trim().toUpperCase();

            char input = (line.isEmpty()) ? ' ' : line.charAt(0);

            // user answer
            if (input == 'A' || input == 'B' || input == 'C') {

                if (answered.get(current)) {
                    System.out.println("You've already answered this question.");
                    continue;
                }

                userAnswers.set(current, input);
                answered.set(current, true);

                try {
                    if (logWriter != null) {
                        logWriter.write("Q" + (current + 1) + ": " + q.question + "\n");
                        logWriter.write("A. " + q.A + "\n");
                        logWriter.write("B. " + q.B + "\n");
                        logWriter.write("C. " + q.C + "\n");
                        logWriter.write("Player answer: " + input + "\n");

                        if (input == q.answer) {
                            logWriter.write("Result: Correct\n\n");
                        } else {
                            logWriter.write("Result: Wrong (Correct: " + q.answer + ")\n\n");
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Error writing to log...");
                }

                if (input == q.answer) {
                    System.out.println("Correct!");
                    score++;
                } else {
                    System.out.println("Wrong! Correct answer: " + q.answer);
                }
            }

            // next question
            else if (input == 'N') {

                if (current + 1 >= quiz.size()) {
                    System.out.println("You're at the last question already.");
                    continue;
                }

                backStack.push(current);
                current++;
                nextStack.clear();
            }

            // go back to last question
            else if (input == 'P') {
                if (!backStack.isEmpty()) {
                    nextStack.push(current);
                    current = backStack.pop();
                } else {
                    System.out.println("No previous question.");
                }
            }

            // close program
            else if (input == 'X') {
                break;
            }

            // edit current question
            else if (input == 'E') {

                Question editQ = quiz.get(current);

                System.out.println("\n=== EDIT QUESTION ===");

                System.out.print("New question: ");
                editQ.question = sc.nextLine();

                System.out.print("New Choice A: ");
                editQ.A = sc.nextLine();

                System.out.print("New Choice B: ");
                editQ.B = sc.nextLine();

                System.out.print("New Choice C: ");
                editQ.C = sc.nextLine();

                System.out.print("Correct answer (A/B/C): ");
                editQ.answer = sc.nextLine().toUpperCase().charAt(0);

                updateQuestionFile();

                System.out.println("Question updated successfully!");
            }

            // delete current question
            else if (input == 'D') {

                questions.remove(quiz.get(current));
                quiz.remove(current);

                userAnswers.remove(current);
                answered.remove(current);

                updateQuestionFile();

                System.out.println("Question deleted!");

                if (quiz.isEmpty()) {
                    System.out.println("No more questions left.");
                    break;
                }

                if (current >= quiz.size()) {
                    current = quiz.size() - 1;
                }
            }

            // add new question
            else if (input == 'Q') {

                System.out.println("\n=== ADD QUESTION ===");

                System.out.print("Question: ");
                String question = sc.nextLine();

                System.out.print("Choice A: ");
                String A = sc.nextLine();

                System.out.print("Choice B: ");
                String B = sc.nextLine();

                System.out.print("Choice C: ");
                String C = sc.nextLine();

                System.out.print("Correct answer (A/B/C): ");
                char ans = sc.nextLine().toUpperCase().charAt(0);

                Question newQ = new Question(question, A, B, C, ans);

                questions.add(newQ);
                quiz.add(newQ);

                userAnswers.add(' ');
                answered.add(false);

                updateQuestionFile();

                System.out.println("Question added successfully!");
            }

            else {
                System.out.println("Invalid input.");
            }
        }

        System.out.println("\nFinal Score: " + score + "/" + quiz.size());

        try {
            if (logWriter != null) {
                logWriter.write("Final Score: " + score + "/" + quiz.size() + "\n");
                logWriter.write("===============================\n\n");
                logWriter.close();
            }
        } catch (IOException e) {
            System.out.println("Error saving session log.");
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

    // rewrite questions.txt
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
            System.out.println("Error updating questions file.");
        }
    }
}