package SudokuSolver.Logic;

import SudokuSolver.Messages;

public class Checker extends Sudoku {
    protected Messages messages;
    protected boolean [][] mask;
    protected final static int SIZE = 9;
    protected Solver solver;

    public Checker() {
        super();
        messages = new Messages();
        solver = new Solver();
        mask = new boolean[SIZE][SIZE];
        for (int i = 0; i < SIZE; i ++) {
            for (int j = 0; j < SIZE; j ++) {
                mask[i][j] = true;
            }
        }
    }

    public boolean[][]check(String [][] array) {
        super.updateGrid(array);
        solver.updateGrid(array);
        int [][] initialArray = super.getGrid();
        solver.solve();
        int [][] outputGrid = solver.getGrid();
        for (int i = 0; i < 9; i ++) {
            for (int j = 0; j < 9; j ++) {
                if (initialArray[i][j] != outputGrid[i][j]) {
                    mask[i][j]= false;
                }
            }
        }
        for (int i = 0; i < SIZE; i ++) {
            for (int j = 0; j < SIZE; j ++) {
                System.out.print(mask[i][j] + " ");
            }
            System.out.println();
        }
        return mask;
    }
}
