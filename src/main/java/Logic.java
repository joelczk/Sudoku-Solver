public class Logic {
    protected int [][]board;
    protected UI ui;
    public static final int EMPTY = 0;
    public static final int SIZE = 9;

    public Logic() {
        ui = new UI();
    }

    public void updateBoard(int [][] board) {
        this.board = board;
    }

    private boolean isSameRow(int row, int number) {
        for (int i = 0; i < SIZE; i++) {
            if (board[row][i] == number) {
                return true;
            }
        }
        return false;
    }

    private boolean isSameCol(int col, int number) {
        for (int i = 0; i < SIZE; i++) {
            if (board[i][col] == number) {
                return true;
            }
        }
        return false;
    }

    private boolean isSameBox(int row, int col, int number)  {
        int r = row - (row % 3);
        int c = col - (col % 3);
        for (int i = r; i < r + 3; i++) {
            for (int j = c; j < c + 3; j++) {
                if (board[i][j] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isValid(int row, int col, int number) {
        return !isSameRow(row, number) && !isSameCol(col, number) && !isSameBox(row, col, number);
    }

    /**
     * checks whether sudoku can be solved.
     * @return boolean value whether the sudoku can be solved
     */
    public boolean solve() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == EMPTY) {
                    for (int number = 1; number <= SIZE; number++) {
                        if (isValid(row, col, number)) {
                            board[row][col] = number;
                            if (solve()) {
                                return true;
                            } else {
                                board[row][col] = EMPTY;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public int [][] returnBoard() {
        return board;
    }
}
