package SudokuSolver;

public class Logic {
    protected int [][] grid;
    protected static final int SIZE = 9;
    protected static final int EMPTY = 0;

    public Logic() {
        grid = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i ++) {
            for (int j = 0; j < SIZE; j ++) {
                grid[i][j] = 0;
            }
        }
    }

    int getNumber(String text) {
        switch (text) {
        case "1":
            return 1;
        case "2":
            return 2;
        case "3":
            return 3;
        case "4":
            return 4;
        case "5":
            return 5;
        case "6":
            return 6;
        case "7":
            return 7;
        case "8":
            return 8;
        case "9":
            return 9;
        default:
            return 0;
        }
    }

    public int[][] getGrid() {
        return grid;
    }

    public void updateGrid(String [][] array) {
        int [][] storeArray = new int[SIZE][SIZE];
        for (int i = 0; i < 9; i ++) {
            for (int j = 0; j < 9; j ++) {
                storeArray[i][j] = getNumber(array[i][j]);
            }
        }
        grid = storeArray;
    }

    private boolean isSameRow(int row, int number) {
        for (int i = 0; i < SIZE; i++) {
            if (grid[row][i] == number) {
                return true;
            }
        }
        return false;
    }

    private boolean isSameCol(int col, int number) {
        for (int i = 0; i < SIZE; i++) {
            if (grid[i][col] == number) {
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
                if (grid[i][j] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isValid(int row, int col, int number) {
        return !isSameRow(row, number) && !isSameCol(col, number) && !isSameBox(row, col, number);
    }

    public boolean solve() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (grid[row][col] == EMPTY) {
                    for (int number = 1; number <= SIZE; number++) {
                        if (isValid(row, col, number)) {
                            grid[row][col] = number;
                            if (solve()) {
                                return true;
                            } else {
                                grid[row][col] = EMPTY;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
