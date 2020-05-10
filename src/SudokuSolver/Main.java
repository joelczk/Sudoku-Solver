package SudokuSolver;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    private final static int SIZE = 9;
    private TextField [][]gridText = new TextField[SIZE][SIZE];
    private static String [][] storeArray = new String[SIZE][SIZE];
    private Logic logic;
    private Messages messages;

    public Main() {
        messages = new Messages();
        logic = new Logic();
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
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setPrefSize(messages.SIXHUNDRED, messages.SIXHUNDRED);
        root.setStyle("-fx-padding: 20");
        for (int i = 0; i < SIZE; i ++) {
            for (int j = 0; j < SIZE; j ++) {
                TextField textField = new TextField(messages.ZEROSTRING);
                textField.setPrefHeight(messages.HEIGHT);
                textField.setAlignment(Pos.CENTER);
                textField.setEditable(true);
                gridText[i][j] = textField;
                root.add(textField, i, j, messages.ONE, messages.ONE);
            }
        }
        Button solveButton = new Button(messages.SOLVESTRING);
        solveButton.setAlignment(Pos.CENTER);
        solveButton.setPrefWidth(Messages.WIDTH);
        solveButton.setTranslateY(messages.TRANSLATEY);
        root.add(solveButton,messages.FOUR,messages.TEN,messages.ONE,messages.ONE);
        solveButton.setOnAction(e -> {
            storeArray = getArray();
            logic.updateGrid(storeArray);
            logic.solve();
            int [][] outputGrid = logic.getGrid();
            root.getChildren().clear();
            for (int i = 0; i < SIZE; i ++) {
                for (int j = 0; j <SIZE; j ++) {
                    int number = outputGrid[i][j];
                    TextField textField = new TextField(Integer.toString(number));
                    textField.setPrefHeight(messages.PREFERREDHEIGHT);
                    textField.setAlignment(Pos.CENTER);
                    textField.setEditable(false);
                    gridText[i][j] = textField;
                    root.add(textField, i, j, messages.ONE, messages.ONE);
                }
            }
        });
        return root;
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
