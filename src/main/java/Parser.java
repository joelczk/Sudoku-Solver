
public class Parser {
    protected String input;
    protected String[] values;
    protected Messages messages;

    public Parser() {
        messages = new Messages();
    }

    public void updateInput(String input) {
        this.input = input;
        values = input.split(",");
    }

    /**
     * Checks if input is numeric.
     * @return boolean value, depending on whether input is numeric
     */
    public boolean isNumeric() {
        for (int i = 0; i < 9; i++) {
            if (!values[i].matches("[0-9]+")) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if input is valid.
     * @throws SudokuException if input is invalid
     */
    public void isValidInput() throws SudokuException {
        if (values.length != 9) {
            throw new SudokuException(messages.invalidLength);
        } else if (!isNumeric()) {
            throw new SudokuException(messages.invalidInput);
        }
    }

    /**
     * Returns update sudoke board.
     * @return array storing sudoku board
     */
    public int[] returnArray() {
        int [] integerValues = new int[9];
        for (int i = 0; i < 9; i++) {
            int number = Integer.parseInt(values[i]);
            integerValues[i] = number;
        }
        return integerValues;
    }
}
