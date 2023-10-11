package com.example.task12_lavr;

import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.Timer;
import java.util.TimerTask;

public class HelloController {

    @FXML
    private TextArea area_chess;

    @FXML
    private AnchorPane pane_chess;

    @FXML
    private Spinner<Integer> spinner_moves;

    @FXML
    private Spinner<Integer> spinner_x;

    @FXML
    private Spinner<Integer> spinner_y;

    @FXML
    private Button button_show;

    @FXML
    private Button button_clear;

    @FXML
    private TextField textField_Coordinate;

    @FXML
    private TextField textField_Original;

    private static final int S = 8;
    private static Rectangle[][] rectangles = new Rectangle[S][S];
    private int color_num = 0;

    @FXML
    void initialize() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        pane_chess.getChildren().add(gridPane);

        spinner_moves.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 8));
        spinner_x.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 8));
        spinner_y.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 8));

        Color[] colors = {Color.RED, Color.PINK, Color.AQUA, Color.DARKGREEN, Color.YELLOW, Color.ORANGE};

        button_show.setOnAction(actionEvent -> {
            area_chess.clear();
            if (color_num == 5) {
                color_num = 0;
            }

            int[][] board = new int[S][S];
            int x, y, n;
            x = spinner_x.getValue();
            y = spinner_y.getValue();
            n = spinner_moves.getValue();

            if (n > 6) n = 6;
            knightMoves(board, x - 1, y - 1, n, x - 1, y - 1);

            Rectangle rectangle = rectangles[x - 1][y - 1];
            rectangle.setFill(Color.BROWN);

            for (int i = 0; i < S; i++) {
                for (int j = 0; j < S; j++) {
                    if (board[i][j] == 1) {
                        System.out.println("(" + i + ";" + j + ")");
                        area_chess.appendText(" | ");
                        FadeTransition ft;
                        if (i != (x - 1) && j != (y - 1)) {
                            Rectangle rectangle1 = rectangles[i][j];
                            if (!(rectangle1.getFill() == colors[0]) && !(rectangle1.getFill() == colors[1]) &&
                                    !(rectangle1.getFill() == colors[2]) && !(rectangle1.getFill() == colors[3]) &&
                                    !(rectangle1.getFill() == colors[4]) && !(rectangle1.getFill() == colors[5])) {
                                rectangle1.setFill(colors[color_num]);
                                ft = new FadeTransition(Duration.millis(1000), rectangle1);
                                ft.setFromValue(1.0);
                                ft.setToValue(0.1);
                                ft.setCycleCount(Timeline.INDEFINITE);
                                ft.setAutoReverse(true);
                                ft.play();


                                int finalJ = j + 1;
                                int finalI = i + 1;
                                rectangle1.setOnMouseEntered(event -> {
                                    Rectangle sourceRectangle1 = (Rectangle) event.getSource();
                                    String originalcoordinate = (String) sourceRectangle1.getUserData();
                                    textField_Original.setText(originalcoordinate);

                                    String Coordinate = finalJ + "," + finalI;
                                    textField_Coordinate.setText(Coordinate);

                                });
                            }
                        }
                    } else {
                        area_chess.appendText(" . ");
                    }
                }
                area_chess.appendText("\n");
            }
            color_num++;
        });

        button_clear.setOnAction(actionEvent -> {
            area_chess.clear();
            FadeTransition ft = new FadeTransition(Duration.millis(1500), gridPane);
            ft.setFromValue(1.0);
            ft.setToValue(0.1);
            ft.setCycleCount(Timeline.INDEFINITE);
            ft.setAutoReverse(true);
            ft.play();


            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    Rectangle rectangle = new Rectangle(50, 50);
                    rectangles[row][col] = rectangle;

                    if ((row + col) % 2 == 0) {
                        rectangle.setFill(Color.WHITE);
                    } else {
                        rectangle.setFill(Color.BLACK);
                    }
                    rectangle.setUserData((row + 1) + "," + (col + 1));
                    gridPane.add(rectangle, col, row);
                }
            }


            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                int counter = 0;

                @Override
                public void run() {
                    counter++;

                    if (counter >= 4) {
                        timer.cancel();
                        ft.stop();
                    }
                }
            };


            timer.schedule(task, 0, 1000);

        });
    }

    public static boolean knightMoves(int[][] board, int x, int y, int n, int originalX, int originalY) {
        if (n > 0) {
            for (int i = 0; i < S; i++) {
                for (int j = 0; j < S; j++) {
                    if (!movePossible(board, x, y, i, j)) continue;
                    if (n == 1) {
                        board[i][j] = 1;
                        Rectangle rectangle1 = rectangles[i][j];
                        rectangle1.setUserData((y + 1) + "," + (x + 1));
                    }
                    if (knightMoves(board, i, j, n - 1, originalX, originalY)) {
                        board[i][j] = 1;
                        Rectangle rectangle = rectangles[i][j];
                        rectangle.setUserData((originalX + 1) + "," + (originalY + 1));
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean movePossible(int[][] board, int x, int y, int i, int j) {
        if (((x - 2 == i || x + 2 == i) && (y - 1 == j || y + 1 == j)) ||
                ((x - 1 == i || x + 1 == i) && (y - 2 == j || y + 2 == j)))
            return true;
        return false;
    }
}