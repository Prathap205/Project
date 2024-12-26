import java.util.Random;
import java.util.Scanner;

class Player {
    private String name;
    private int position;
    private boolean hasStarted;

    public Player(String name) {
        this.name = name;
        this.position = 0;
        this.hasStarted = false;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public boolean hasStarted() {
        return hasStarted;
    }

    public void startGame() {
        hasStarted = true;
    }

    public void move(int steps) {
        position += steps;
        if (position > 50) {
            position = 50; // Cap at the finish line
        }
    }

    public void resetPosition() {
        position = 0;
        hasStarted = false;
    }
}

class LudoGame {
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Random dice;
    private Scanner scanner;

    public LudoGame(String player1Name, String player2Name) {
        player1 = new Player(player1Name);
        player2 = new Player(player2Name);
        currentPlayer = player1;
        dice = new Random();
        scanner = new Scanner(System.in);
    }

    public void startGame() {
        System.out.println("Welcome to Ludo!");
        System.out.println("First to reach position 50 wins the game!");
        System.out.println("Rolling a 6 is required to start moving.");
        System.out.println();

        while (true) {
            System.out.println(currentPlayer.getName() + "'s turn.");
            System.out.println("Press Enter to roll the dice.");
            scanner.nextLine();

            int roll = rollDice();
            System.out.println(currentPlayer.getName() + " rolled a " + roll + ".");

            if (!currentPlayer.hasStarted()) {
                if (roll == 6) {
                    System.out.println("You rolled a 6! You can start moving.");
                    currentPlayer.startGame();
                } else {
                    System.out.println("You need a 6 to start. Turn skipped.");
                }
            } else {
                currentPlayer.move(roll);
                System.out.println(currentPlayer.getName() + " is now at position " + currentPlayer.getPosition() + ".");

                // Check for collision
                if (currentPlayer == player1 && player1.getPosition() == player2.getPosition()) {
                    System.out.println("Collision! " + player2.getName() + " is sent back to the start!");
                    player2.resetPosition();
                } else if (currentPlayer == player2 && player2.getPosition() == player1.getPosition()) {
                    System.out.println("Collision! " + player1.getName() + " is sent back to the start!");
                    player1.resetPosition();
                }

                // Check for win condition
                if (currentPlayer.getPosition() == 50) {
                    System.out.println(currentPlayer.getName() + " wins the game!");
                    break;
                }
            }

            // Switch player
            currentPlayer = (currentPlayer == player1) ? player2 : player1;
        }
    }

    private int rollDice() {
        return dice.nextInt(6) + 1; // Generate a number between 1 and 6
    }
}

public class Ludo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name for Player 1:");
        String player1Name = scanner.nextLine();
        System.out.println("Enter name for Player 2:");
        String player2Name = scanner.nextLine();

        LudoGame game = new LudoGame(player1Name, player2Name);
        game.startGame();
    }
}
