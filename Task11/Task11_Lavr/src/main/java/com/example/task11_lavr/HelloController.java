package com.example.task11_lavr;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;


import java.io.*;

public class HelloController {

    @FXML
    private Button Button_Browse_Dir;

    @FXML
    private Button Button_Browse_Path;

    @FXML
    private Button button_Convert;

    @FXML
    private Button button_File1;

    @FXML
    private Button button_File2;

    @FXML
    private Button button_Fix;

    @FXML
    private Button button_Save1;

    @FXML
    private Button button_Save2;

    @FXML
    private TextArea textArea_Enter1;

    @FXML
    private TextArea textArea_Enter2;

    @FXML
    private TextArea textArea_Result1;

    @FXML
    private TextArea textArea_Result2;

    @FXML
    private TextArea textArea_Roots;

    @FXML
    private TextField textField_Dir;

    @FXML
    private TextField textField_Path;

    String file_path, dir_path;
    File file_read;
    @FXML
    void initialize() {

        button_File1.setOnAction(actionEvent -> {
            String fileContent = readFile(file_read);
            textArea_Enter1.setText(fileContent);
        });
        button_File2.setOnAction(actionEvent -> {
            String fileContent = readFile(file_read);
            textArea_Enter2.setText(fileContent);
        });

        Button_Browse_Path.setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            file_read = fileChooser.showOpenDialog(Button_Browse_Path.getScene().getWindow());

            if (file_read != null) {
                textField_Path.setText(file_read.getPath());
            }
            file_path = textField_Path.getText();
        });

        Button_Browse_Dir.setOnAction(actionEvent -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File file = directoryChooser.showDialog(Button_Browse_Dir.getScene().getWindow());
            if (file != null) {
                textField_Dir.setText(file.getPath());

            }
            dir_path = textField_Dir.getText();
        });

        button_Save1.setOnAction(actionEvent -> {
            String text = textArea_Result1.getText();
            if (dir_path != null) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(dir_path + "\\Exercise 18.txt"))) {
                    writer.write(text);
                    textArea_Result1.appendText("\n\nСохранено!\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        button_Save2.setOnAction(actionEvent -> {
            String text = textArea_Result2.getText();
            if (dir_path != null) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(dir_path + "\\Exercise 12.txt"))) {
                    writer.write(text);
                    textArea_Result2.appendText("\n\nСохранено!\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        button_Fix.setOnAction(actionEvent -> {
            textArea_Result2.clear();
            String text = textArea_Enter2.getText();
            String vocabulary = textArea_Roots.getText();
            List<String> vocabularyList = Arrays.asList(vocabulary.split(" "));
            String result = findMistake(text, vocabularyList);
            textArea_Result2.appendText(result);


        });



        button_Convert.setOnAction(actionEvent -> {
            textArea_Result1.clear();
            String csharpCode = textArea_Enter1.getText();

            String pattern = "(\\w+)\\s*\\+\\+";
            Pattern regex = Pattern.compile(pattern);
            Matcher matcher = regex.matcher(csharpCode);
            String processedCode = matcher.replaceAll("$1 = $1 + 1");

            textArea_Result1.appendText(processedCode);

        });

    }

    public static String chooseRoot(List<String> roots) {
        int max = roots.get(0).length();
        String res = roots.get(0);
        for (String r : roots) {
            if (max < r.length()) {
                max = r.length();
                res = r;
            }
        }
        return res;
    }

    public static List<String> checkable(String word, List<String> vocabulary) {
        List<String> res = new ArrayList<>();

        for (String v : vocabulary) {
            int n = v.length();
            int i = 0;
            int mistakes = 0;
            word = word.toLowerCase();
            while (i + n < word.length()) {
                for (int j = 0; j < n; j++) {
                    if (word.charAt(j + i) != v.charAt(j)) {
                        mistakes += 1;
                    }
                    if (mistakes > 1) {
                        break;
                    }
                }
                if (mistakes == 1) {
                    res.add(v);
                }
                if (mistakes == 0) {
                    res.add(v);
                }
                mistakes = 0;
                i += 1;
            }
        }
        if (res.size() > 1) {
            return Arrays.asList(chooseRoot(res));
        } else if (res.size() == 1) {
            return res;
        } else {
            return new ArrayList<>();
        }
    }

    public static Integer indexOfMistake(String word, String root) {
        int i = 0;
        int mistake = 0;
        int n = root.length();
        word = word.toLowerCase();
        while (i + n < word.length()) {
            for (int j = 0; j < n; j++) {
                if (word.charAt(j + i) != root.charAt(j)) {
                    mistake += 1;
                }
                if (mistake > 1) {
                    break;
                }
            }
            if (mistake == 1) {
                return i;
            }
            mistake = 0;
            i += 1;
        }
        return null;
    }

    public static String changeTheText(String text, String root, int i1, int i2) {
        StringBuilder result = new StringBuilder(text);
        for (int i = i1 + i2; i < i1 + i2 + root.length(); i++) {
            if (Character.toLowerCase(result.charAt(i)) == root.charAt(i - i1 - i2)) {
                continue;
            }
            result.setCharAt(i, root.charAt(i - i1 - i2));
        }
        return result.toString();
    }


    public static String findMistake(String text, List<String> vocabulary) {
        int index = 0;
        String marks = " :;,.()\n"; // add sth
        while (index != text.length()) {
            if (marks.contains(String.valueOf(text.charAt(index)))) {
                index += 1;
                continue;
            }
            StringBuilder word = new StringBuilder();

            while (index < text.length() && !marks.contains(String.valueOf(text.charAt(index)))) {
                word.append(text.charAt(index));
                index += 1;
            }
            List<String> result = checkable(word.toString(), vocabulary);
            if (!result.isEmpty()) {
                Integer i = indexOfMistake(word.toString(), result.get(0));
                if (i != null) {
                    text = changeTheText(text, result.get(0), index - word.length(), i); // may be mistake with index
                }
            }
        }
        return text;
    }


    private String readFile(File file) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}
