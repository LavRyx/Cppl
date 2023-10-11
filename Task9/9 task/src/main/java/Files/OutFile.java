package Files;

import java.io.*;
import java.util.ArrayList;

public class OutFile {
    public ArrayList output(ArrayList<String> str, File f1)
    {
        try (BufferedReader br = new BufferedReader(new FileReader(f1)))
        {
            String line;
            int i=0;
            while ((line = br.readLine()) != null) {
                str.add(line);
                i++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return str;
    }

    public void output2 (ArrayList<String> str, Integer[][] ar)
    {
        for (int i = 0; i < str.size(); i++) {
            String s = str.get(i);
            String[] mas_str = s.split("");
            for (int j = 0; j < mas_str.length; j++) {
                ar[i][j] = Integer.parseInt(mas_str[j]);
            }
        }
    }
}
