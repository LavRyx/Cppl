package Task10;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class FileUtil {
    // Метод для сохранения списка конфет в файл
    public static void writeToFileCandy(List<Candy> candies, String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        for (Candy candy : candies) {
            writer.write(candy.name + "," + candy.price + "\n");
        }
        writer.close();
    }

    public static List<Candy> readFromFileCandy(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        List<Candy> candies = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] tokens = line.split(",");
            String name = tokens[0];
            double pricePerKg = Double.parseDouble(tokens[1]);
            Candy candy = new Candy(name, pricePerKg);
            candies.add(candy);
        }
        reader.close();
        return candies;
    }



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

    public static String saveFileDialog() {
        Stage stage = new Stage();
        String filePath = null;
        File initialDir = new File(System.getProperty("user.dir"));

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Сохранить файл");
        fileChooser.setInitialDirectory(initialDir);
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Текстовые файлы", "*.txt")
        );

        File selectedFile = fileChooser.showSaveDialog(stage);
        if (selectedFile != null) {
            filePath = selectedFile.getAbsolutePath();
        }

        return filePath;
    }
}
