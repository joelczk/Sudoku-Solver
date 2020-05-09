import java.util.Scanner;

public class UI {
    protected int [][] board;
    protected Messages messages;
    private final Scanner in;

    /**
     * Class that determines UI of sudoku solver.
     */
    public UI() {
        this.in = new Scanner(System.in);
        board = new int [9][9];
        messages = new Messages();
    }

    /**
     * Sets the default board for sudoku solver.
     */
    public void setDefaultBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = 0;
            }
        }
    }

    /**
     * Gets the user input of the sudoku board.
     * @return user input
     */
    public String getUserInput() {
        return in.nextLine().trim();
    }

    /**
     * returns String suffix of number.
     * @param number number that user input
     * @return String suffix and number
     */
    public String suffix(int number) {
        String stringNumber = Integer.toString(number);
        String suff = "";
        if (number == 1) {
            suff = "st";
        } else if (number == 2) {
            suff = "nd";
        } else if (number == 3) {
            suff = "rd";
        } else {
            suff = "th";
        }
        return stringNumber + suff;
    }

    /**
     * UI of row.
     * @param count row number
     */
    public void rowUI(int count) {
        System.out.println(String.format(messages.rowNumber, suffix(count + 1)));
    }

    /**
     * Updates sudoku board.
     * @param count row number
     * @param array array that stores board
     */
    public void updateBoard(int count, int [] array) {
        for (int i = 0; i < 9; i++) {
            board[count][i] = array[i];
        }
    }

    /**
     * returns sudoku board.
     * @return array that stores board
     */
    public int [][] returnBoard() {
        return board;
    }

    /**
     * Loads sudoke board.
     * @param board array that stores board
     */
    public void loadBoard(int [][] board) {
        this.board = board;
    }

    /**
     * prints out sudoku board.
     */
    public void printBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0;j < 9; j++) {
                System.out.print(board[i][j] + "  ");
            }
            System.out.println();
        }
    }
}
