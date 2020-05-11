package SudokuSolver;

import javafx.application.Application;
import javafx.geometry.Pos;
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
    private Logic logic;
    private Messages messages;
    private ButtonUI buttonUI;
    private GridPaneUI gridPaneUI;
    public Main() {
        messages = new Messages();
        logic = new Logic();
        buttonUI = new ButtonUI();
        gridPaneUI = new GridPaneUI();
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
        solveButton.setOnAction(e -> {
            storeArray = getArray();
            logic.updateGrid(storeArray);
            logic.solve();
            int [][] outputGrid = logic.getGrid();
            root.getChildren().clear();
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
        layer.getChildren().addAll(root, randomiseButton, solveButton);
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
