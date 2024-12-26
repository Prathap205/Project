import java.util.Random;
import java.util.Scanner;

public class DiceRollingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int playerScore = 0, computerScore = 0;
        String playAgain;

        System.out.println("Welcome to the Dice Rolling Game!");
        System.out.println("You and the computer will roll a dice. The highest roll wins each round.");
        System.out.println("First to 3 points wins the game!");

        while (playerScore < 3 && computerScore < 3) {
            System.out.println("\nPress Enter to roll the dice...");
            scanner.nextLine();

            int playerRoll = random.nextInt(6) + 1; // Roll a dice (1-6)
            int computerRoll = random.nextInt(6) + 1;

            System.out.println("You rolled: " + playerRoll);
            System.out.println("Computer rolled: " + computerRoll);

            if (playerRoll > computerRoll) {
                playerScore++;
                System.out.println("You win this round!");
            } else if (computerRoll > playerRoll) {
                computerScore++;
                System.out.println("Computer wins this round!");
            } else {
                System.out.println("It's a tie!");
            }

            System.out.println("Score - You: " + playerScore + ", Computer: " + computerScore);
        }

        if (playerScore == 3) {
            System.out.println("\nCongratulations! You won the game!");
        } else {
            System.out.println("\nGame over! The computer won the game.");
        }

        System.out.println("Thanks for playing!");
        scanner.close();
    }
}
