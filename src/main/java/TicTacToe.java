import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        Scanner scanner = new Scanner(System.in);
        int row, col;

        while (!game.isBoardFull() && !game.checkForPlay()) {
            game.printBoard();
            System.out.println("Игрок " + game.gameNow + ", прошу ввести две цифры через пробел строку (0-2) и колонку (0-2): ");
            row = scanner.nextInt();
            col = scanner.nextInt();
            game.placeMark(row, col);
        }

        game.printBoard();
        if (game.checkForPlay()) {
            game.changePlayer();
            System.out.println("Игрок " + game.gameNow + " победил! УРА! ");
        } else {
            System.out.println("Победила Дружба!");
        }


    }


    private char[][] board;
    private char gameNow;

    public TicTacToe() {
        board = new char[3][3];
        gameNow = 'X';
        initializeBoard();
    }

    private void initializeBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = '-';
            }
        }
    }

    private void printBoard() {
        System.out.println("-------------");
        for (int row = 0; row < 3; row++) {
            System.out.print("| ");
            for (int col = 0; col < 3; col++) {
                System.out.print(board[row][col] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    private boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkForPlay() {
        return checkRowsForPlay() || checkColumnsForPlay() || checkDiagonalsForPlay();
    }

    private boolean checkRowsForPlay() {
        for (int row = 0; row < 3; row++) {
            if (checkRowCol(board[row][0], board[row][1], board[row][2])) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumnsForPlay() {
        for (int col = 0; col < 3; col++) {
            if (checkRowCol(board[0][col], board[1][col], board[2][col])) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonalsForPlay() {
        return (checkRowCol(board[0][0], board[1][1], board[2][2]) || checkRowCol(board[0][2], board[1][1], board[2][0]));
    }

    private boolean checkRowCol(char c1, char c2, char c3) {
        return (c1 != '-' && c1 == c2 && c2 == c3);
    }

    public void changePlayer() {
        gameNow = (gameNow == 'X') ? 'O' : 'X';
    }

    public void placeMark(int row, int col) {
        if (row >= 0 && row < 3 && col >= 0 && col < 3) {
            if (board[row][col] == '-') {
                board[row][col] = gameNow;
                changePlayer();
            }
        }
    }
}
