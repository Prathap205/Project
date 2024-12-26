import java.util.Scanner;
import java.util.Random;

public class GuessTheNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int target = random.nextInt(100) + 1; // Random number between 1 and 100
        int guess = 0;

        System.out.println("Welcome to Guess the Number!");
        System.out.println("I have chosen a number between 1 and 100. Try to guess it!");

        while (true) {
            System.out.print("Enter your guess: ");
            guess = scanner.nextInt();

            if (guess < target) {
                System.out.println("Too low! Try again.");
            } else if (guess > target) {
                System.out.println("Too high! Try again.");
            } else {
                System.out.println("Congratulations! You guessed the number!");
                break;
            }
        }

        System.out.println("Thanks for playing!");
        scanner.close();
    }
}
