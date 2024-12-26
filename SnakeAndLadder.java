import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class SnakeAndLadder {
    private static final int WINNING_POSITION = 100;
    private HashMap<Integer, Integer> snakes;
    private HashMap<Integer, Integer> ladders;
    private int playerPosition;

    public SnakeAndLadder() {
        snakes = new HashMap<>();
        ladders = new HashMap<>();
        playerPosition = 1;

        // Define snakes (key = head, value = tail)
        snakes.put(16, 6);
        snakes.put(48, 26);
        snakes.put(62, 19);
        snakes.put(88, 24);
        snakes.put(95, 56);
        snakes.put(97, 78);

        // Define ladders (key = bottom, value = top)
        ladders.put(3, 22);
        ladders.put(5, 8);
        ladders.put(20, 29);
        ladders.put(27, 74);
        ladders.put(36, 44);
        ladders.put(50, 67);
    }

    private void printBoardStatus() {
        System.out.println("You are currently at position: " + playerPosition);
    }

    private int rollDice() {
        Random random = new Random();
        return random.nextInt(6) + 1;
    }

    private void movePlayer(int diceRoll) {
        int newPosition = playerPosition + diceRoll;

        if (newPosition > WINNING_POSITION) {
            System.out.println("Roll exceeds the winning position. Try again!");
            return;
        }

        // Check for snakes
        if (snakes.containsKey(newPosition)) {
            System.out.println("Oops! Bitten by a snake. Going down to " + snakes.get(newPosition));
            newPosition = snakes.get(newPosition);
        }

        // Check for ladders
        else if (ladders.containsKey(newPosition)) {
            System.out.println("Yay! Found a ladder. Climbing up to " + ladders.get(newPosition));
            newPosition = ladders.get(newPosition);
        }

        playerPosition = newPosition;
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Snake and Ladder!");

        while (playerPosition < WINNING_POSITION) {
            System.out.print("\nPress 'r' to roll the dice: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("r")) {
                int diceRoll = rollDice();
                System.out.println("You rolled a " + diceRoll);
                movePlayer(diceRoll);
                printBoardStatus();

                if (playerPosition == WINNING_POSITION) {
                    System.out.println("Congratulations! You reached position 100 and won the game!");
                    break;
                }
            } else {
                System.out.println("Invalid input. Please press 'r' to roll the dice.");
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        SnakeAndLadder game = new SnakeAndLadder();
        game.startGame();
    }
}
