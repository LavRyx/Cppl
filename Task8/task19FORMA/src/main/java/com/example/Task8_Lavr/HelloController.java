package com.example.Task8_Lavr;
import Files.OutFile;
import java.util.Scanner;
import java.util.Iterator;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.io.File;

import javafx.stage.FileChooser;
import javafx.stage.DirectoryChooser;


public class HelloController {

    @FXML
    private TextField Answer_field;
    @FXML
    private TextArea textbox_task2;
    @FXML
    private Button button_answer;
    @FXML
    private Button Browse_button;

    @FXML
    private Button Browse_dir_button;

    @FXML
    private TextField Dir_Field;

    @FXML
    private TextField Path_Field;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea Lab1;

    @FXML
    private TextArea Lab2;

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private TextArea textbox1;

    String file_path, dir_path;


    public void MaxPloshad(int[] high, ArrayList<Integer> mas_save, int stroka, Integer[][] array, int ppp [], int newmax) {

        int dlin = high.length;
        int [] newHIGH = new int [dlin];
        int max = 0;
        newHIGH[0] = high[0];
        int saveShirina=0;
        int stolb=0;

        for (int i = 1; i < dlin; i++)
        {
            newHIGH[i] = high[i];
            for (int j = i - 1; j >= 0; j--)
            {
                if (newHIGH[j] > newHIGH[i])//если предыдущая высота > нынешней
                {
                    if (newHIGH[j] * (i - j) > max)//если площадь > максимальной
                    {
                        max = newHIGH[j] * (i - j);
                        stolb=j;
                        saveShirina=i - j;
                    }
                    newHIGH[j] = newHIGH[i];
                }
                else
                {
                    break;
                }
            }
        }

        for (int i = 0; i < dlin; i++) // чтобы найти новые площади
        {
            if (newHIGH[i] * (dlin - i) > max)
            {
                max = newHIGH[i] * (dlin - i);
                saveShirina=dlin - i;
                stolb=i;
            }
        }

        int visota;
        if (saveShirina==0)
        {
            visota = 0;
        }
        else
        {
            visota = max / saveShirina;
        }


        Integer[][] DOPAMAS = new Integer[visota+2][saveShirina+2];

        int iz=stroka-1, jz;

        for (int i = 0; i < DOPAMAS.length; i++) {
            jz=stolb-1;
            for (int j = 0; j < DOPAMAS[0].length; j++) {
                try
                {
                    DOPAMAS[i][j] = array[iz][jz];
                }
                catch (Exception ee)
                {
                    DOPAMAS[i][j] = 0;
                }
                jz++;
            }
            iz++;
        }


        int PROVERKANA1=0;
        //------------------------------------------------
        int i=0;
        for (int j = 0; j < DOPAMAS[0].length; j++)
        {
            if (DOPAMAS[i][j]!=0)
            {
                PROVERKANA1++;
            }
        }

        i=DOPAMAS.length-1;
        for (int j = 0; j < DOPAMAS[0].length; j++)
        {
            if (DOPAMAS[i][j]!=0)
            {
                PROVERKANA1++;
            }
        }
        //-------------------------------------------------------
        int j=0;
        for (int i2 = 0; i2 < DOPAMAS.length; i2++)
        {
            if (DOPAMAS[i2][j]!=0)
            {
                PROVERKANA1++;
            }
        }

        j=DOPAMAS[0].length-1;
        for (int i2 = 0; i2 < DOPAMAS.length; i2++)
        {
            if (DOPAMAS[i2][j]!=0)
            {
                PROVERKANA1++;
            }
        }

        if ((PROVERKANA1>0)||((saveShirina==0)&&(visota==0)))
        {
            max=-20;
        }

        if (ppp[0]==0) {
            mas_save.add(max);
        }

        if (ppp[0]==1) {
            if (max==newmax) {
                ppp[0]=5;

                //-------------------------------------------------------------
                if ((saveShirina==0)&&(visota==0))
                {

                        dir_path = Path_Field.getText();
                        File file2 = new File(dir_path + "19TASK_OTVETY.txt");
                        try (FileWriter writer = new FileWriter(file2, false)) {
                            writer.append("(-1,-1,-1,-1)");
                        } catch (IOException e3) {
                            throw new RuntimeException(e3);
                        }



                }
                else {

                        dir_path = Dir_Field.getText();
                        File file2 = new File(dir_path + "19TASK_OTVETY.txt");
                        try (FileWriter writer = new FileWriter(file2, false)) {
                            writer.append("-----------------------------------");
                            writer.append("\nAnswer = (" + stroka + ", " + stolb + ", " + visota + ", " + saveShirina + ")");
                            writer.append("\nRow =" + stroka);
                            writer.append("\nColumn =" + stolb);
                            writer.append("\nHeight =" + visota);
                            writer.append("\nWidth =" + saveShirina);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }


                }

                //------------------------------------------------------------

                    dir_path = Dir_Field.getText();
                    OutFile file = new OutFile();
                    ArrayList<String> masOut2 = new ArrayList<String>();
                    File f3 = new File(dir_path + "19TASK_OTVETY.txt");
                    file.output(masOut2,f3);

                    String ooo2 = "";
                    for (int i2 = 0; i2 < masOut2.size(); i2++) {
                        ooo2+=masOut2.get(i2);
                        ooo2+="\n";
                    }
                    Lab2.setText(ooo2);


            }
        }
    }

    public static Integer[][] gista(Integer[][] array){

        Integer[][] newArray= new Integer[array.length][array[0].length];

        for(int i=0;i<array.length;i++){
            for(int j=0;j<array[0].length;j++){
                int height=0;
                for(int k=i;k<array.length;k++){
                    if(array[k][j]==1)
                    {
                        height+=1;
                    }
                    else
                    {
                        break;
                    }
                }
                newArray[i][j]=height;
            }
        }
        return newArray;
    }

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

        button2.setOnAction(actionEvent ->
        {
            try {

                    Lab1.setText("Array entered");
                    Lab2.setText("Push button:\n<Get Answer>");
                    String size2 = textbox1.getText();
                    file_path = Path_Field.getText();
                    File file6 = new File(file_path);
                    try (FileWriter writer = new FileWriter(file6, false))
                    {
                        writer.append(size2);
                    } catch (IOException e3) {
                        throw new RuntimeException(e3);
                    }


            }
            catch (Exception e6)
            {
                Lab1.setText("Array is not entered!");
                Lab2.setText("---");
            }
        });
        button1.setOnAction(actionEvent ->
        {
            try {
                //-----------------------------------------------------------------

                OutFile file = new OutFile();

                ArrayList<String> masOut1 = new ArrayList<String>();

                    file_path = Path_Field.getText();
                    File f1 = new File(file_path);
                    file.output(masOut1, f1);

                    Integer[][] array = new Integer[masOut1.size()][masOut1.get(0).length()];
                    file.output2(masOut1, array);
                    //-----------------------------------------------------------------
                    for (int i = 0; i < array.length; i++) {
                        for (int j = 0; j < array[0].length; j++) {
                            if ((array[i][j]!=0)&&(array[i][j]!=1))
                            {
                                array[i][j]=1;
                            }
                        }
                    }
                    //-----------------------------------------------------------------
                    String ooo="";
                    for (int i = 0; i < array.length; i++) {
                        ooo += "[";
                        for (int j = 0; j < array[0].length; j++) {
                            ooo += array[i][j];
                            ooo += ", ";
                        }
                        ooo += "]\n";
                    }
                    Lab1.setText(ooo);

                    Integer[][] arrayrebuild = gista(array);

                    int n = array[0].length;
                    int[] mas = new int[n];//для строк
                    ArrayList<Integer> mas_save = new ArrayList();//для ответов

                    //-------------------------------------------------------
                    Integer[][] PROVER = new Integer[5000][masOut1.get(0).length()];
                    ArrayList<Integer> strokii = new ArrayList();

                    for (int i = 0; i < PROVER.length; i++)//забиваем нулями
                    {
                        for (int j = 0; j < PROVER[0].length; j++)
                        {
                            PROVER[i][j]=0;
                        }
                    }

                    int st=0;
                    for (int i = 0; i < arrayrebuild.length; i++)
                    {
                        for (int j = 0; j < arrayrebuild[0].length; j++)
                        {
                            if (arrayrebuild[i][j]!=0)
                            {
                                PROVER[st][j]=arrayrebuild[i][j];
                            }
                            if (j!=0)
                            {
                                if ((arrayrebuild[i][j] == 0) && (arrayrebuild[i][j - 1] != 0))
                                {
                                    strokii.add(i);
                                    st++;
                                }
                            }
                        }
                        strokii.add(i);
                        st++;
                    }

                    for (int h=0; h<5000; h++)
                    {
                        strokii.add(5000);
                    }

                    int [] VIVOD = new int[1];
                    VIVOD[0]=0;
                    //------------------------------------------------------
                    int savestroka=50;
                    for (int i = 0; i < PROVER.length; i++) {
                        for (int j = 0; j < PROVER[0].length; j++) {
                            mas[j] = PROVER[i][j];
                        }
                        savestroka=strokii.get(i);

                        MaxPloshad(mas, mas_save, savestroka, array, VIVOD, -33);
                    }
                    //-------------------------------------------
                    int ploshad = Collections.max(mas_save);

                    if (ploshad!=-20)
                    {
                        VIVOD[0] = 1;
                        for (int i = 0; i < PROVER.length; i++) {
                            for (int j = 0; j < PROVER[0].length; j++) {
                                mas[j] = PROVER[i][j];
                            }
                            savestroka = strokii.get(i);

                            MaxPloshad(mas, mas_save, savestroka, array, VIVOD, ploshad);
                        }
                    }
                    else
                    {
                        int s = 5/0;
                    }



            }
            catch (Exception e2)
            {
                dir_path  = Dir_Field.getText();
                File file2 = new File(dir_path + "19TASK_OTVETY.txt");
                try (FileWriter writer = new FileWriter(file2, false)) {
                    writer.append("(-1,-1,-1,-1)");
                } catch (IOException e3) {
                    throw new RuntimeException(e3);
                }
                OutFile file3 = new OutFile();
                ArrayList<String> masOut2 = new ArrayList<String>();
                File f3 = new File(dir_path + "19TASK_OTVETY.txt");
                file3.output(masOut2,f3);

                String ooo2 = "";
                for (int i2 = 0; i2 < masOut2.size(); i2++) {
                    ooo2+=masOut2.get(i2);
                    ooo2+="\n";
                }
                Lab2.setText(ooo2);
                Lab1.setText("Enter an array again!");
            }
        });
        button_answer.setOnAction(actionEvent -> {


            if(textbox_task2.getText().isEmpty()) {
                try {
                    ////////////////////// из файла в текст
                    File file = new File(Path_Field.getText());
                    Scanner scn = new Scanner(file);
                    ArrayList<String[]> nums = new ArrayList<>();

                    while (scn.hasNext()) {
                        nums.add(scn.nextLine().split(" "));
                    }

                    int columns = nums.get(0).length;
                    int[][] arr = new int[nums.size()][columns];
                    Iterator<String[]> iter = nums.iterator();
                    for (int i = 0; i < arr.length; i++) {
                        String[] s = iter.next();
                        for (int j = 0; j < columns; j++) {
                            arr[i][j] = Integer.parseInt(s[j]);
                        }
                    }

                    for (int i = 0; i < arr.length; i++) {
                        for (int j = 0; j < columns; j++) {
                            textbox_task2.appendText(Integer.toString(arr[i][j]) + " ");
                        }
                        textbox_task2.appendText("\n");
                    }
                    Answer_field.setText("Array Entered");
                    //////////////////



                } catch (Exception e6) {
                    Answer_field.setText("Array is not entered!");
                }

            }
            else {
                ////////////////////////////////Из текста в файл
                int[][] array = getArray(); // считывание массива из JTextArea
                saveArray(array); // запись массива в файл
                ///////////////////////////////
            }

            int[][] array_count = getArray();

            int rows = array_count.length, columns = array_count[1].length;
            boolean check = true;
            int temp = -150;

            int[][] matrix = array_count;
            //1-ое строка, второе - столбец

            short i=0,j=0;
            short size = (short)(rows * columns);
            boolean downleft = true;
            boolean changedirection = false;


            for (int k = 1; k<=size; k++) {

                if(!check_yp(temp, matrix[i][j]))
                    check = false;
                temp = matrix[i][j];

                if (downleft) {
                    i++;
                    j--;
                    if (i == rows) {
                        i--;
                        j += 2;
                        changedirection = true;
                    }
                    else {
                        if (j < 0) {
                            j++;
                            changedirection = true;
                        }
                    }
                }
                if (!downleft) {
                    i--;
                    j++;
                    if (j == columns) {
                        j--;
                        i += 2;
                        changedirection = true;
                    }
                    else {
                        if (i < 0) {
                            i++;
                            changedirection = true;
                        }
                    }
                }
                if (changedirection) {
                    downleft = !downleft;
                }
                changedirection = false;
            }

            if(check)
                Answer_field.setText("The array is ordered");
            else Answer_field.setText("The array not is ordered");

        });

    }

    public int[][] getArray() {
        String[] rows = textbox_task2.getText().split("\n"); // разбиение текста на строки
        int numRows = rows.length;
        int numCols = rows[0].split(" ").length;
        int[][] array = new int[numRows][numCols]; // создание двумерного массива
        for (int i = 0; i < numRows; i++) {
            String[] cols = rows[i].split(" "); // разбиение строки на столбцы
            for (int j = 0; j < numCols; j++) {
                array[i][j] = Integer.parseInt(cols[j]); // преобразование строки в число
            }
        }
        return array;
    }

    public void saveArray(int[][] array) {
        try {
            FileWriter writer = new FileWriter(file_path); // создание объекта FileWriter
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[i].length; j++) {
                    writer.write(String.valueOf(array[i][j])); // запись числа в файл
                    if (j < array[i].length - 1) {
                        writer.write(" "); // добавление пробела между числами в строке
                    }
                }
                writer.write("\n"); // добавление символа новой строки
            }
            writer.close(); // закрытие файла
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static boolean check_yp(int a, int b) {
        if(a < b)
            return true;
        return false;
    }



}
