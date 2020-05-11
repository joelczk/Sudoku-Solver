package SudokuSolver;

import javafx.geometry.Pos;
import javafx.scene.control.Button;

public class ButtonUI {

    protected Messages messages;
    public ButtonUI() {
        messages = new Messages();
    }

    public Button createSolveButton() {
        Button solveButton = new Button(messages.SOLVESTRING);
        solveButton.setAlignment(Pos.CENTER);
        solveButton.setPrefWidth(Messages.WIDTH);
        solveButton.setTranslateY(messages.TRANSLATEY);
        solveButton.setTranslateX(messages.SOLVETRANSLATEX);
        return solveButton;
    }

    public Button createRandomizeButton() {
        Button randomiseButton = new Button(messages.RANDOMISESTRING);
        randomiseButton.setAlignment(Pos.CENTER);
        randomiseButton.setPrefWidth(Messages.WIDTH);
        randomiseButton.setTranslateY(messages.TRANSLATEY);
        randomiseButton.setTranslateX(messages.RANOMIZETRANSLATEX);
        return randomiseButton;
    }
}
