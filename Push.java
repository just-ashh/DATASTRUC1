import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Push{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter commit message: ");
        String commitMessage = scanner.nextLine();

        try {
            run("git", "config", "user.name", "just-ashh");
            run("git", "config", "user.email", "angelclaire.bacsal@lorma.edu");

            run("git", "add", ".");

            run("git", "commit", "-m", commitMessage);

            run("git", "pull", "--rebase", "origin", "main");

            run("git", "push", "origin", "main");

            System.out.println("Push complete.");

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        scanner.close();
    }

    private static void run(String... cmd) throws Exception {
        ProcessBuilder pb = new ProcessBuilder(cmd);
        pb.redirectErrorStream(true);

        Process p = pb.start();

        try (BufferedReader br =
                     new BufferedReader(new InputStreamReader(p.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }

        if (p.waitFor() != 0) {
            throw new RuntimeException("Failed: " + String.join(" ", cmd));
        }
    }
}