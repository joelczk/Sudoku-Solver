package SudokuSolver.Logic;

public class Solver extends Sudoku {
    public Solver() {
        super();
    }

    public boolean solve() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (grid[row][col] == EMPTY) {
                    for (int number = 1; number <= SIZE; number++) {
                        if (super.isValid(row, col, number)) {
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
