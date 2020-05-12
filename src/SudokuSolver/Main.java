package SudokuSolver;

import SudokuSolver.Logic.Randomize;
import SudokuSolver.Logic.Solver;
import SudokuSolver.Logic.Sudoku;
import SudokuSolver.UI.ButtonUI;
import SudokuSolver.UI.GridPaneUI;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    private final static int SIZE = 9;
    private TextField [][]gridText = new TextField[SIZE][SIZE];
    private static String [][] storeArray = new String[SIZE][SIZE];
    private Solver solver;
    private Messages messages;
    private ButtonUI buttonUI;
    private GridPaneUI gridPaneUI;
    private Randomize randomize;
    private Sudoku sudoku;

    public Main() {
        messages = new Messages();
        solver = new Solver();
        buttonUI = new ButtonUI();
        gridPaneUI = new GridPaneUI();
        randomize = new Randomize();
        sudoku = new Sudoku();
    }

    public String[][] getArray() {
        String [][] storeArray = new String [SIZE][SIZE];
        for (int i = 0; i < SIZE;i ++) {
            for (int j = 0; j < SIZE; j ++) {
                String numberText = gridText[i][j].getText();
                storeArray[i][j] = numberText;
            }
        }
        return storeArray;
    }

    private Parent createContent() {
        Pane layer = new Pane();
        GridPane root = new GridPane();
        gridPaneUI.createGridPane(root);
        for (int i = 0; i < SIZE; i ++) {
            for (int j = 0; j < SIZE; j ++) {
                TextField textField = gridPaneUI.createTextField(messages.isEditable, messages.ZEROSTRING);
                textField.setStyle("-fx-border-color: black");
                gridText[i][j] = textField;
                root.add(textField, i, j, messages.ONE, messages.ONE);
            }
        }
        Button solveButton = buttonUI.createSolveButton();
        Button randomiseButton = buttonUI.createRandomizeButton();
        Button checkButton = buttonUI.createCheckButton();

        checkButton.setOnAction(e -> {
            storeArray = getArray();
            sudoku.updateGrid(storeArray);
            solver.updateGrid(storeArray);
            int [][] initialGrid = sudoku.getGrid();
            solver.solve();
            int [][] outputGrid = solver.getGrid();
            for (int i = 0; i < SIZE; i ++) {
                for (int j = 0; j < SIZE; j ++) {
                    TextField textField = gridPaneUI.createTextField(messages.isNotEditable, storeArray[i][j]);
                    textField.setStyle("-fx-border-color: black");
                    if (initialGrid[i][j] != outputGrid[i][j]) {
                        textField.setEditable(true);
                        textField.setStyle("-fx-background-color: yellow; -fx-border-color: black");
                    }
                    gridText[i][j] = textField;
                    root.add(textField, i, j, messages.ONE, messages.ONE);
                }
            }
        });

        randomiseButton.setOnAction(e -> {
            int [][] randomizedGrid = randomize.hideKDigits();
            for (int i = 0; i < SIZE; i ++) {
                for (int j = 0; j < SIZE; j ++) {
                    int number = randomizedGrid[i][j];
                    TextField textField = gridPaneUI.createTextField(messages.isNotEditable, Integer.toString(number));
                    textField.setStyle("-fx-border-color: black");
                    if (number == 0) {
                        textField.setEditable(true);
                    }
                    gridText[i][j] = textField;
                    root.add(textField, i, j, messages.ONE, messages.ONE);
                }
            }
        });

        solveButton.setOnAction(e -> {
            storeArray = getArray();
            solver.updateGrid(storeArray);
            solver.solve();
            int [][] outputGrid = solver.getGrid();
            for (int i = 0; i < SIZE; i ++) {
                for (int j = 0; j <SIZE; j ++) {
                    int number = outputGrid[i][j];
                    TextField textField = gridPaneUI.createTextField(messages.isNotEditable, Integer.toString(number));
                    textField.setStyle("-fx-border-color: black");
                    gridText[i][j] = textField;
                    root.add(textField, i, j, messages.ONE, messages.ONE);
                }
            }
        });

        layer.getChildren().addAll(root, randomiseButton, solveButton, checkButton);
        return layer;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.setTitle(messages.SUDOKUSOLVER);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
