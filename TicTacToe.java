import java.util.Scanner;

public class TicTacToeGame {
    private static char[][] board = new char[3][3];
    private static char currentPlayer = 'X';

    public static void main(String[] args) {
        initializeBoard();
        printBoard();
        playGame();
    }

    private static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    private static void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    private static void playGame() {
        boolean gameWon = false;
        boolean isDraw = false;

        while (!gameWon && !isDraw) {
            int[] move = getPlayerMove();
            int row = move[0];
            int col = move[1];

            if (board[row][col] == '-') {
                board[row][col] = currentPlayer;
                printBoard();

                if (checkWin(row, col)) {
                    System.out.println("Congratulations! Player " + currentPlayer + " wins!");
                    gameWon = true;
                } else if (checkDraw()) {
                    System.out.println("It's a draw!");
                    isDraw = true;
                }

                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            } else {
                System.out.println("Cell already taken! Try again.");
            }
        }
    }

    private static int[] getPlayerMove() {
        Scanner scanner = new Scanner(System.in);
        int[] move = new int[2];

        System.out.print("Player " + currentPlayer + ", enter your move (row and column): ");
        move[0] = scanner.nextInt();
        move[1] = scanner.nextInt();

        return move;
    }

    private static boolean checkWin(int row, int col) {
        return (checkRow(row) || checkColumn(col) || checkDiagonals());
    }

    private static boolean checkRow(int row) {
        return (board[row][0] == board[row][1] && board[row][1] == board[row][2] && board[row][0] != '-');
    }

    private static boolean checkColumn(int col) {
        return (board[0][col] == board[1][col] && board[1][col] == board[2][col] && board[0][col] != '-');
    }

    private static boolean checkDiagonals() {
        return ((board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != '-') ||
                (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != '-'));
    }

    private static boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }
}
