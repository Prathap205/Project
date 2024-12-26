import java.util.Scanner;

class Board {
    private String[][] board;
    public static final int SIZE = 3;

    public Board() {
        board = new String[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = " ";
            }
        }
    }

    public boolean isFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j].equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean makeMove(int row, int col, String symbol) {
        if (row >= 0 && row < SIZE && col >= 0 && col < SIZE && board[row][col].equals(" ")) {
            board[row][col] = symbol;
            return true;
        }
        return false;
    }

    public boolean checkWinner(String symbol) {
        // Check rows and columns
        for (int i = 0; i < SIZE; i++) {
            if (board[i][0].equals(symbol) && board[i][1].equals(symbol) && board[i][2].equals(symbol)) {
                return true;
            }
            if (board[0][i].equals(symbol) && board[1][i].equals(symbol) && board[2][i].equals(symbol)) {
                return true;
            }
        }
        // Check diagonals
        return (board[0][0].equals(symbol) && board[1][1].equals(symbol) && board[2][2].equals(symbol)) ||
               (board[0][2].equals(symbol) && board[1][1].equals(symbol) && board[2][0].equals(symbol));
    }

    public void printBoard() {
        System.out.println("---------");
        for (int i = 0; i < SIZE; i++) {
            System.out.print("| ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("---------");
        }
    }
}

class Player {
    private final String symbol;

    public Player(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public void makeMove(Board board, Scanner scanner) {
        int row, col;
        while (true) {
            System.out.println("Player " + symbol + ", enter your move (row and column 1-3): ");
            row = scanner.nextInt() - 1;
            col = scanner.nextInt() - 1;
            if (board.makeMove(row, col, symbol)) {
                break;
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
    }
}

public class TicTacToe {
    private final Board board;
    private final Player player1, player2;
    private Player currentPlayer;
    private final Scanner scanner;

    public TicTacToe() {
        board = new Board();
        player1 = new Player("X");
        player2 = new Player("O");
        currentPlayer = player1;
        scanner = new Scanner(System.in);
    }

    public void startGame() {
        while (true) {
            board.printBoard();
            currentPlayer.makeMove(board, scanner);
            if (board.checkWinner(currentPlayer.getSymbol())) {
                board.printBoard();
                System.out.println("Player " + currentPlayer.getSymbol() + " wins!");
                break;
            }
            if (board.isFull()) {
                board.printBoard();
                System.out.println("It's a draw!");
                break;
            }
            currentPlayer = (currentPlayer == player1) ? player2 : player1;
        }
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.startGame();
    }
}

