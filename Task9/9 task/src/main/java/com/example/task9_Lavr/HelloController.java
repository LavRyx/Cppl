package com.example.task9_Lavr;

import java.io.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import javafx.stage.FileChooser;
import javafx.stage.DirectoryChooser;
import java.util.Collections;


public class HelloController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Browse_button;

    @FXML
    private Button Browse_dir_button;

    @FXML
    private TextField Dir_Field;

    @FXML
    private TextArea Lab2;

    @FXML
    private TextField Path_Field;

    @FXML
    private Button button1;

    @FXML
    private Button button_answer;

    @FXML
    private TextArea text_answ2;

    @FXML
    private TextField text_enter;

    @FXML
    private TextField text_enter2;

    @FXML
    private TextField text_enter_file;

    @FXML
    private TextField text_enter_file2;

    String file_path, dir_path;


    @FXML
    void initialize() {
        Browse_button.setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            File file = fileChooser.showOpenDialog(Browse_button.getScene().getWindow());

            if (file != null) {

                Path_Field.setText(file.getPath());

            }
            file_path = Path_Field.getText();
        });

        Browse_dir_button.setOnAction(actionEvent -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File file = directoryChooser.showDialog(Browse_dir_button.getScene().getWindow());

            if (file != null) {
                // pickUpPathField it's your TextField fx:id
                Dir_Field.setText(file.getPath());

            }
            dir_path = Dir_Field.getText();
        });

        button1.setOnAction(actionEvent -> {
            if(!Lab2.getText().isEmpty())
                Lab2.clear();
            ArrayList<Integer> list1 = new ArrayList<>();
            if(!text_enter.getText().isEmpty()) {

                String[] numbers = (text_enter.getText()).split(" ");
                for (String number : numbers){
                    list1.add(Integer.parseInt(number));
                }
            }
            else {
                try (BufferedReader br = new BufferedReader(new FileReader(file_path)))
                {
                    String line;
                    line = br.readLine();
                    text_enter_file.setText(line);
                    String[] numbers = line.split(" ");

                    for (String number : numbers){
                        list1.add(Integer.parseInt(number));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            ArrayList<Integer> answerList = createNewList1(list1);
            for (Integer x : answerList) {
                Lab2.appendText(x + " ");
            }


            try (FileWriter writer = new FileWriter(dir_path + "\\test.txt", false))
            {
                for (Integer integer : answerList) {
                    writer.append(String.valueOf(integer)).append(" ");
                }
                writer.write("\n");
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        button_answer.setOnAction(actionEvent -> {
            if(!text_answ2.getText().isEmpty())
                text_answ2.clear();
            ArrayList<Integer> list1 = new ArrayList<>();
            if(!text_enter2.getText().isEmpty()) {

                String[] numbers = (text_enter2.getText()).split(" ");
                for (String number : numbers){
                    list1.add(Integer.parseInt(number));
                }
            }
            else {
                try (BufferedReader br = new BufferedReader(new FileReader(file_path)))
                {
                    String line;
                    line = br.readLine();
                    text_enter_file2.setText(line);
                    String[] numbers = line.split(" ");

                    for (String number : numbers){
                        list1.add(Integer.parseInt(number));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            ArrayList<Integer> result = createNewList2(list1);
            for (Integer x : result) {
                text_answ2.appendText(x + " ");
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(dir_path + "\\test.txt")))
            {
                for (Integer integer : result) {
                    writer.append(integer.toString()).append(" ");
                }
                writer.append("\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });






    }

    public static ArrayList<Integer> createNewList2(ArrayList<Integer> list) {
        ArrayList<Integer> sortedList = new ArrayList<>(list);
        sort(sortedList);

        ArrayList<Integer> result = new ArrayList<>();
        ArrayList<Integer> current = new ArrayList<>();
        for (int i = 0; i < sortedList.size() - 1; i++) {
            int currentNumber = sortedList.get(i);
            int nextNumber = sortedList.get(i + 1);
            if (currentNumber + 1 == nextNumber) {
                current.add(currentNumber);
            } else {
                current.add(currentNumber);
                if (current.size() > result.size()) {
                    result = new ArrayList<>(current);
                } else if (current.size() == result.size() && current.get(0) < result.get(0)) {
                    result = new ArrayList<>(current);
                }
                current.clear();
            }
        }
        if (!current.isEmpty() && (current.size() > result.size() || (current.size() == result.size() && current.get(0) < result.get(0)))){
            result = new ArrayList<>(current);
        }
        return result;
    }

    public static void sort(ArrayList<Integer> list) {
        Collections.sort(list);
    }


    public static ArrayList<Integer> createNewList1(ArrayList<Integer> list) {
        ArrayList<ArrayList<Integer>> container = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> result = new ArrayList<Integer>();
        int len = list.size(),
                max = 0;

        for (int i = 0; i+1 < len; i++) {
            for (int c = i, a = 0, b = 1; c+1 < len; a++, b++, c++) {
                ArrayList<Integer> seq = new ArrayList<Integer>();
                seq.add(list.get(i));
                int prev = list.get(i),
                        d = list.get(i+b) - prev;

                for (int j = i+a; j+1 < len; j++) {
                    if (list.get(j+1) - prev == d) {
                        seq.add(list.get(j+1));
                        prev = list.get(j+1);
                    }
                }

                max = max <= seq.size() ? seq.size() : max;
                container.add(seq);
            }
        }

        for (ArrayList<Integer> x : container) {
            if (x.size() == max) {
                result = x;
                break;
            }
        }

        return result;
    }








}
