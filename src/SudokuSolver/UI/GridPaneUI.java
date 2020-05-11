package SudokuSolver.UI;

import SudokuSolver.Messages;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class GridPaneUI {
    protected Messages messages;

    public GridPaneUI() {
        messages = new Messages();
    }

    public void createGridPane(GridPane root) {
        root.setAlignment(Pos.CENTER);
        root.setPrefSize(messages.SIXHUNDRED, messages.SIXHUNDRED);
        root.setStyle("-fx-padding: 20");
    }

    public TextField createTextField(boolean isEditable, String textNumber) {
        TextField textField = new TextField(textNumber);
        textField.setPrefHeight(messages.HEIGHT);
        textField.setAlignment(Pos.CENTER);
        textField.setEditable(isEditable);
        return textField;
    }
}
