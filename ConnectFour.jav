import java.util.Scanner;

class ConnectFourBoard {
    private final String[][] board;
    public static final int ROWS = 6;
    public static final int COLS = 7;

    public ConnectFourBoard() {
        board = new String[ROWS][COLS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = " ";
            }
        }
    }

    public boolean isFull() {
        for (int j = 0; j < COLS; j++) {
            if (board[0][j].equals(" ")) {
                return false;
            }
        }
        return true;
    }

    public boolean makeMove(int col, String symbol) {
        if (col < 0 || col >= COLS || !board[0][col].equals(" ")) {
            return false;
        }
        for (int i = ROWS - 1; i >= 0; i--) {
            if (board[i][col].equals(" ")) {
                board[i][col] = symbol;
                return true;
            }
        }
        return false;
    }

    public boolean checkWinner(String symbol) {
        // Check horizontal lines
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS - 3; j++) {
                if (board[i][j].equals(symbol) && board[i][j + 1].equals(symbol) &&
                    board[i][j + 2].equals(symbol) && board[i][j + 3].equals(symbol)) {
                    return true;
                }
            }
        }

        // Check vertical lines
        for (int i = 0; i < ROWS - 3; i++) {
            for (int j = 0; j < COLS; j++) {
                if (board[i][j].equals(symbol) && board[i + 1][j].equals(symbol) &&
                    board[i + 2][j].equals(symbol) && board[i + 3][j].equals(symbol)) {
                    return true;
                }
            }
        }

        // Check diagonal lines (bottom-left to top-right)
        for (int i = 3; i < ROWS; i++) {
            for (int j = 0; j < COLS - 3; j++) {
                if (board[i][j].equals(symbol) && board[i - 1][j + 1].equals(symbol) &&
                    board[i - 2][j + 2].equals(symbol) && board[i - 3][j + 3].equals(symbol)) {
                    return true;
                }
            }
        }

        // Check diagonal lines (top-left to bottom-right)
        for (int i = 0; i < ROWS - 3; i++) {
            for (int j = 0; j < COLS - 3; j++) {
                if (board[i][j].equals(symbol) && board[i + 1][j + 1].equals(symbol) &&
                    board[i + 2][j + 2].equals(symbol) && board[i + 3][j + 3].equals(symbol)) {
                    return true;
                }
            }
        }

        return false;
    }

    public void printBoard() {
        System.out.println(" 1  2  3  4  5  6  7");
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                System.out.print("|" + board[i][j]);
            }
            System.out.println("|");
        }
        System.out.println("---------------------");
    }
}

class ConnectFourPlayer {
    private final String symbol;

    public ConnectFourPlayer(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public void makeMove(ConnectFourBoard board, Scanner scanner) {
        int col;
        while (true) {
            System.out.println("Player " + symbol + ", enter your move (column 1-7): ");
            col = scanner.nextInt() - 1;
            if (board.makeMove(col, symbol)) {
                break;
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
    }
}

public class ConnectFour {
    private final ConnectFourBoard board;
    private final ConnectFourPlayer player1, player2;
    private ConnectFourPlayer currentPlayer;
    private final Scanner scanner;

    public ConnectFour() {
        board = new ConnectFourBoard();
        player1 = new ConnectFourPlayer("X");
        player2 = new ConnectFourPlayer("O");
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
        ConnectFour game = new ConnectFour();
        game.startGame();
    }
}
