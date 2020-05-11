package SudokuSolver.Logic;

import java.util.Random;
public class Randomize extends Sudoku {

    protected String [][] grid;
    protected static final int SIZE = 9;
    protected static final int SRN = 3; // square root of SIZE
    protected int NUMBERMISSINGDIGITS; // no of missing digits
    protected int MINMISSINGDIGITS = 50;
    protected static final int MAXMISSINGDIGITS = 60;
    protected Solver solver;

    public Randomize() {
        super();
        solver = new Solver();
        grid = new String [SIZE][SIZE];
        Random rand = new Random();
        NUMBERMISSINGDIGITS = 0;
        while (NUMBERMISSINGDIGITS < MINMISSINGDIGITS || NUMBERMISSINGDIGITS > MAXMISSINGDIGITS) {
            NUMBERMISSINGDIGITS = rand.nextInt(MAXMISSINGDIGITS);
        }
        for (int i = 0; i < SIZE; i ++) {
            for (int j = 0; j < SIZE; j ++) {
                grid[i][j] = "0";
            }
        }
    }

    public int [][] hideKDigits() {
        solver.updateGrid(grid);
        solver.solve();
        int[][] outputGrid = solver.getGrid();
        int number = 0;
        System.out.println(NUMBERMISSINGDIGITS);
        while (number < NUMBERMISSINGDIGITS) {
            Random rand = new Random();
            int rowNumber = (int) rand.nextInt(9);
            int colNumber = (int) rand.nextInt(9);
            if (outputGrid[rowNumber][colNumber] != 0) {
                outputGrid[rowNumber][colNumber] = 0;
                number++;
            }
        }
        return outputGrid;
    }
}
