public class Main {

    public UI ui;
    public Messages messages;
    public Parser parser;
    public Logic logic;

    /**
     * Main function for sudoku solver.
     */
    public Main() {
        ui = new UI();
        messages = new Messages();
        parser = new Parser();
        logic = new Logic();
    }

    /**
     * Function to solve the sudoku.
     */
    public void solve() {
        if (logic.solve()) {
            System.out.println(messages.solvedSudoku);
            int [][] board = logic.returnBoard();
            ui.loadBoard(board);
            ui.printBoard();
        } else {
            System.out.println(messages.invalidSudoku);
        }
    }

    /**
     * Function to run sudoku solver.
     */
    public void run() {
        int count = 0;
        ui.setDefaultBoard();
        while (count < 9) {
            try {
                ui.rowUI(count);
                String input = ui.getUserInput();
                parser.updateInput(input);
                parser.isValidInput();
                int [] array = parser.returnArray();
                ui.updateBoard(count,array);
                count++;
            } catch (SudokuException e) {
                System.out.println(e.toString());
            }
        }
        System.out.println(messages.successfulBuild);
        System.out.println(messages.initialMatrix);
        ui.printBoard();
        int [][] storeBoard = ui.returnBoard();
        logic.updateBoard(storeBoard);
        solve();
    }


    public static void main(String [] args) throws SudokuException {
        new Main().run();
    }
}
