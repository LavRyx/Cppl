package com.example.newtask7;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.util.ArrayList;
import java.util.List;

public class HelloController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Add1;

    @FXML
    private Button Add2;

    @FXML
    private Button ButtonCount1;

    @FXML
    private Button ButtonCount2;

    @FXML
    private Button Clear1;

    @FXML
    private Button Clear2;

    @FXML
    private ListView<String> Test1;

    @FXML
    private ListView<String> Test2;

    @FXML
    private TextField TextA;

    @FXML
    private TextField TextResult;

    @FXML
    private TextField textField1;

    @FXML
    private TextField textField2;

    @FXML
    void initialize() {


        assert Add1 != null : "fx:id=\"Add1\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert Add2 != null : "fx:id=\"Add2\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert ButtonCount1 != null : "fx:id=\"ButtonCount1\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert ButtonCount2 != null : "fx:id=\"ButtonCount2\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert Clear1 != null : "fx:id=\"Clear1\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert Clear2 != null : "fx:id=\"Clear2\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert Test1 != null : "fx:id=\"Test1\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert Test2 != null : "fx:id=\"Test2\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert TextA != null : "fx:id=\"TextA\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert TextResult != null : "fx:id=\"TextResult\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert textField1 != null : "fx:id=\"textField1\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert textField2 != null : "fx:id=\"textField2\" was not injected: check your FXML file 'hello-view.fxml'.";


        Add2.setOnAction(actionEvent -> {
            if (textField2.getText().isEmpty()) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("If you want to add a new element to the array, enter the number in text field");
                alert.show();
            } else {
                Test2.getItems().add(textField2.getText());
                textField1.clear();
            }

        });

        Clear2.setOnAction(actionEvent -> {
            Test2.getItems().clear();
        });

        ButtonCount2.setOnAction(actionEvent -> {
            try {
                if (Test2.getItems().size() == 0 || TextA.getText().isEmpty()) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Enter the elements of array or enter A number");
                    alert.show();
                } else {
                    int a = Integer.parseInt(TextA.getText());
                    int[] array_32 = new int[Test2.getItems().size()];
                    for (int i = 0; i < array_32.length; i++)
                        array_32[i] = Integer.parseInt(Test2.getItems().get(i));
                    Ex_32(array_32, a);
                }
            } catch (Exception o) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Ooops! Something happened. Error");
                alert.show();
            }

        });


        Add1.setOnAction(actionEvent -> {
            if (textField1.getText().isEmpty()) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("If you want to add a new element to the array, enter the number in text field");
                alert.show();
            } else {
                Test1.getItems().add(textField1.getText());
                textField1.clear();
            }

        });

        Clear1.setOnAction(actionEvent -> {
            Test1.getItems().clear();
        });


        ButtonCount1.setOnAction(actionEvent -> {
            try {
                if (Test1.getItems().size() == 0) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Enter the elements of array");
                    alert.show();
                } else {
                    int[] array_19 = new int[Test1.getItems().size()];
                    for (int i = 0; i < array_19.length; i++)
                        array_19[i] = Integer.parseInt(Test1.getItems().get(i));
                    Ex_19(array_19);
                }
            } catch (Exception o) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Ooops! Something happened. Error");
                alert.show();
            }

        });
    }

    void Ex_19(int[] arr) {
        TextResult.clear();

        List<Integer> longestPit = findLongestPit(arr);

        System.out.println("Longest pit: " + longestPit.toString());

    }

    public static List<Integer> findLongestPit(int[] arr) {
        List<Integer> longestPit = new ArrayList<>();
        int longestPitLength = 0;

        for (int i = 0; i < arr.length - 1; i++) {

            if ((arr[i] > arr[i + 1]) || (arr[i] < arr[i + 1])) {
                int j = i + 1;

                while (j < arr.length - 1 && arr[j] >= arr[j + 1]) {
                    j++;
                }

                while (j < arr.length - 1 && (arr[j] <= arr[j + 1] || arr[j] == arr[j + 1])) {
                    j++;
                }

                int pitLength = j - i + 1;
                if (pitLength > longestPitLength) {
                    longestPitLength = pitLength;
                    longestPit.clear();
                    for (int k = i; k <= j; k++) {
                        longestPit.add(arr[k]);
                    }
                }
                // jump to the end of the pit or the increasing sequence
                i = j - 1;
            }
        }

        return longestPit;
    }


    void Ex_32(int[] arr, int a) {
        TextResult.clear();

        List<Integer> lengths = new ArrayList<>();
        List<Integer> startIndices = new ArrayList<>();
        findLongestSubsequence(arr, a, lengths, startIndices);

        System.out.println("Lengths of all subsequences: " + lengths);
        System.out.println("Start indices of all subsequences: " + startIndices);
    }

     void findLongestSubsequence(int[] arr, int a, List<Integer> lengths, List<Integer> startIndices) {

        int maxLength = 1;
        int currentLength = 1;
        int endIndex = 0;
        int startIndex = 0;

        for (int i = 1; i < arr.length; i++) {
            if (Math.abs(arr[i] - arr[i - 1]) <= a) {
                currentLength++;

                if (currentLength > maxLength) {
                    maxLength = currentLength;
                    endIndex = i;
                }
            } else {
                lengths.add(currentLength);
                startIndices.add(startIndex);
                currentLength = 1;
                startIndex = i;
            }
        }

        lengths.add(currentLength);
        startIndices.add(startIndex);

        startIndex = endIndex - maxLength + 1;
        for(int i = startIndex + 1; i < lengths.size(); i++) {
            if(lengths.get(startIndex) == lengths.get(i)) {
                startIndex = startIndices.get(i);
                endIndex = startIndex + lengths.get(i) - 1;
                break;
            }

        }

        for (int i = startIndex; i <= endIndex; i++) {
            TextResult.appendText(arr[i] + " ");
        }
    }
}




