package com.example.task10_javafx;

import Task10.Candy;
import Task10.FileUtil;
import Task10.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ControllerTask {

    //private static final int MIN_STUDENTS_PER_COURSE = 3; // minimum number of students to keep per course
    //private static final double MIN_AVERAGE_SCORE = 3.0; // minimum average score to avoid expulsion
    //CandyShop candyShop = new CandyShop();
    private final ObservableList<Student> data = FXCollections.observableArrayList();
    LinkedHashSet<Candy> purchasedCandies = new LinkedHashSet<>();
    static List<Candy> candies = new ArrayList<>();
    private final FileChooser fileChooser = new FileChooser();
    @FXML
    private TextArea FileStudents;

    @FXML
    private TextArea droupoutStud_Field;

    @FXML
    private Button btnAddCandy;

    @FXML
    private Button btnCalcCandy;

    @FXML
    private Button btnClearOutCandy;

    @FXML
    private Button btnDeleteCandy;

    @FXML
    private Button btnOpenCandy;

    @FXML
    private Button btnSaveCandy;

    @FXML
    private Button filterButton;

    @FXML
    private ListView<String> listCandy;

    @FXML
    private Button loadButton;

    @FXML
    private Label nameCandy;

    @FXML
    private Label nameCandy1;

    @FXML
    private Label priceCandy;

    @FXML
    private Spinner<Integer> spinnerCountMoney;

    @FXML
    private TextField studentCountField;

    @FXML
    private TextField textFieldNameCandy;

    @FXML
    private TextField textFieldPriceCandy;

    @FXML
    private TextArea textOut;

    @FXML
    private TextField thresholdField;

    public static String openFileDialog() {
        Stage stage = new Stage();
        String filePath = null;
        File initialDir = new File(System.getProperty("user.dir"));

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите файл");
        fileChooser.setInitialDirectory(initialDir);
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Текстовые файлы", "*.txt")
        );

        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            filePath = selectedFile.getAbsolutePath();
        }

        return filePath;
    }
    @FXML
    void initialize() {
        spinnerCountMoney.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 999999));
        List<Student> students = new ArrayList<>();





        Collections.sort(candies);
        fillListView(candies, listCandy);

        loadButton.setOnAction(actionEvent -> {
            students.clear();

            // Добавляем студентов в список
            students.add(new Student("Козлов Максим Евгеньевич", "мужской", 1, 4.7));
            students.add(new Student("Лебедев Алексей Степанович", "мужской", 1, 3.0));
            students.add(new Student("Сидоров Сидор Сидорович", "мужской", 1, 3.0));

            students.add(new Student("Кузнецова Дарья Владиславовна", "женский", 2, 3.0));
            students.add(new Student("Ковалева Юлия Степановна", "женский", 2, 3.1));

            students.add(new Student("Сафонова Полина Александровна", "женский", 3, 4.5));
            students.add(new Student("Киселев Егор Артемович", "мужской", 3, 3.3));

            students.add(new Student("Степанов Тимур Кириллович", "мужской", 4, 5.0));
            students.add(new Student("Зайцева Надежда Александровна", "женский", 4, 4.3));
            students.add(new Student("Лукин Петр Андреевич", "мужской", 4, 3.0));

            String ooo2 = "";
            for (Student student : students) {
                ooo2+=(student.name + ", " + student.gender + ", " + student.course + ", " + student.gpa);
                ooo2+="\n";
            }
            FileStudents.setText(ooo2);
        });

        filterButton.setOnAction(actionEvent -> {
            students.clear();
            droupoutStud_Field.clear();
            String input = FileStudents.getText();
            String[] lines = input.split("\n");
            // для каждой строки разбиваем ее содержимое
            for (String line : lines) {
                String[] parts = line.split(", ");
                String name = parts[0];
                String pol = parts[1];
                int kurs = Integer.parseInt(parts[2]);
                double ball = Double.parseDouble(parts[3]);
                students.add(new Student(name,pol,kurs,ball));
            }
            List<Student> expelledStudents = filterStudents(students, Double.parseDouble(thresholdField.getText()), Integer.parseInt(studentCountField.getText()));

            for (Student student : expelledStudents) {
                droupoutStud_Field.appendText(student.getName() + " " + student.getCourse());
                droupoutStud_Field.appendText("\n");
            }







        });

        btnCalcCandy.setOnAction(actionEvent -> {
            double budget = (double)spinnerCountMoney.getValue();

            purchasedCandies.clear();
            Collections.sort(candies);
            List<String> selectedCandyNames = new ArrayList<>();

            int maxCurrentCandyPrice = 0;
            String candyName = "";
            double totalCost = 0;
            int numTypes = 1;
            while (numTypes == 1) {
                numTypes = 0;
                totalCost = 0;
                selectedCandyNames.clear();
                for (Candy candy : candies) {
                    if (totalCost + candy.price <= budget) {
                        if (candy.price > maxCurrentCandyPrice) {
                            maxCurrentCandyPrice = (int) candy.price;
                            candyName = candy.name;
                        }
                        totalCost += candy.price;
                        numTypes++;
                        selectedCandyNames.add(candy.name);
                    }
                }

                if (numTypes == 1) {
                    selectedCandyNames.remove(0);
                    candies.remove(0);
                }
            }

            String resultText = "";
            if (numTypes == 0) {
                resultText+=("Candy list:\n");
                resultText+=(candyName);
                resultText+=("\nRemaining money " + (budget - maxCurrentCandyPrice));
                textOut.setText(resultText);
            } else {
                resultText+=("List of purchased candies:\n");
                for (String name : selectedCandyNames) {
                    resultText+=(name+", ");
                }
                resultText+=("\n" + (budget - totalCost) + " Remaining money");
                textOut.setText(resultText);
            }
        });
        btnAddCandy.setOnAction(actionEvent -> {
            try{
                candies.add(new Candy(textFieldNameCandy.getText(),  Double.parseDouble(textFieldPriceCandy.getText())));
                Collections.sort(candies);
                fillListView(candies, listCandy);
                ClearTextCandy();
            }
            catch (Exception ex){}

        });
        btnDeleteCandy.setOnAction(actionEvent -> {
            try
            {
                candies.remove(listCandy.getSelectionModel().selectedIndexProperty().get());
                Collections.sort(candies);
                fillListView(candies, listCandy);
                ClearTextCandy();
            }
            catch (Exception ex){}

        });
        btnSaveCandy.setOnAction(actionEvent -> {
            try {
                FileUtil.writeToFileCandy(candies, FileUtil.saveFileDialog());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        btnOpenCandy.setOnAction(actionEvent -> {
            try {
                candies = FileUtil.readFromFileCandy(FileUtil.openFileDialog());
                fillListView(candies, listCandy);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        btnClearOutCandy.setOnAction(actionEvent -> textOut.clear());


    }
    private <T> void fillListView(List<T> items, ListView<String> listView) {
        // Создаем объект ObservableList из списка items
        ObservableList<String> itemStrings = FXCollections.observableArrayList();

        // Добавляем каждый элемент списка items в ObservableList
        for (var item : items) {
            itemStrings.add(item.toString());
        }

        // Устанавливаем ObservableList в качестве источника данных для ListView
        listView.setItems(itemStrings);
    }

    private void ClearTextCandy()
    {
        textFieldPriceCandy.clear();
        textFieldNameCandy.clear();
    }

    public static List<Student> filterStudents(List<Student> students, double threshold, int minStudentsPerCourse) {
        List<Student> expelledStudents = new ArrayList<>();

        Map<Integer, List<Student>> studentsByCourse = new HashMap<>();
        for (Student student : students) {
            List<Student> courseStudents = studentsByCourse.getOrDefault(student.getCourse(), new ArrayList<>());
            courseStudents.add(student);
            studentsByCourse.put(student.getCourse(), courseStudents);
        }

        // Проходим по каждому курсу
        for (int course : studentsByCourse.keySet()) {
            List<Student> courseStudents = studentsByCourse.get(course);

            // Проверяем, нужно ли отчислить студентов на данном курсе
            if (courseStudents.size() > minStudentsPerCourse) {
                // Сортируем студентов по среднему баллу (от самого низкого к самому высокому)
                courseStudents.sort(Comparator.comparingDouble(Student::getGpa));

                // Определяем количество студентов для отчисления
                int studentsToExpel = courseStudents.size() - minStudentsPerCourse;

                // Проверяем, есть ли студенты на границе отчисления с одинаковыми баллами
                if (courseStudents.get(studentsToExpel - 1).getGpa() == courseStudents.get(studentsToExpel).getGpa()) {
                    // Не отчисляем никого
                    continue;
                }

                // Проверяем средний балл каждого студента и отчисляем тех, у кого балл ниже порогового значения
                for (int i = 0; i < studentsToExpel; i++) {
                    Student student = courseStudents.get(i);
                    if (student.getGpa() < threshold) {
                        expelledStudents.add(student);
                    }
                }
            }
        }

        return expelledStudents;
    }


}
