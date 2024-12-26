import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String[] options = {"Rock", "Paper", "Scissors"};
        
        System.out.println("Welcome to Rock, Paper, Scissors!");
        System.out.println("Type 'Rock', 'Paper', or 'Scissors' to play. Type 'exit' to quit.");

        while (true) {
            System.out.print("Your move: ");
            String userMove = scanner.nextLine();

            if (userMove.equalsIgnoreCase("exit")) {
                System.out.println("Thanks for playing!");
                break;
            }

            // Validate user input
            if (!userMove.equalsIgnoreCase("Rock") && 
                !userMove.equalsIgnoreCase("Paper") && 
                !userMove.equalsIgnoreCase("Scissors")) {
                System.out.println("Invalid move. Try again.");
                continue;
            }

            // Computer's move
            int computerIndex = random.nextInt(3);
            String computerMove = options[computerIndex];
            System.out.println("Computer chose: " + computerMove);

            // Determine the winner
            if (userMove.equalsIgnoreCase(computerMove)) {
                System.out.println("It's a tie!");
            } else if (
                (userMove.equalsIgnoreCase("Rock") && computerMove.equals("Scissors")) ||
                (userMove.equalsIgnoreCase("Paper") && computerMove.equals("Rock")) ||
                (userMove.equalsIgnoreCase("Scissors") && computerMove.equals("Paper"))
            ) {
                System.out.println("You win!");
            } else {
                System.out.println("You lose!");
            }
        }

        scanner.close();
    }
}
