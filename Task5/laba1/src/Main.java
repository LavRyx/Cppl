import java.util.Scanner;
public class Main {
    public static void Paint (int h) {
        int check = 0;
        while(check < h) {
            for(int k = 0; k < check; k++)
                System.out.print(" ");

            for(int j = 0; j < h - check; j++) {
                if (j % 2 == 0)
                    System.out.print("\\");
                else
                    System.out.print(" ");
            }
            System.out.print("\n");
            check++;
        }
    }

    public static void main(String[] args) {
        int h=0;
        Scanner read = new Scanner(System.in);
        while (h < 1)
        {
            System.out.print("Height h (>=1) = ");
            try {
                h = read.nextInt();
                if(h < 1) {
                    System.out.println("H should be >= 1! ERROR!");
                }
                else {
                    break;
                }
            }
            catch(Exception e) {
                System.out.println("ERROR! Enter a NUMBER (H>=1)");
                break;
            }
        }
        Paint(h);
        read.close();
    }
}
