import java.io.StringWriter;
import java.util.Scanner;
public class Main {
    static void count(int n, double x, double e) {
        double result = 1, temp = 1, sumE = 1, sumDel10 = 1;
        for(int i = 0; i < n; i++) {
            temp *= x;
            result += temp;
        }
        temp = 1;
        for(int i = 0; i < n; i++) {
            if (Math.abs(temp) >= e) {
                temp *= x;
                sumE += temp;
            }
        }
        temp = 1;
        for(int i = 0; i < n; i++) {
            if (Math.abs(temp) >= (e / 10.0)) {
                temp *= x;
                sumDel10 += temp;
            }
        }
        Print (result, sumE, sumDel10, x);

    }

    static void Print (double result, double sumE, double sumDel10, double x) {
        System.out.println("Math: " + (1 / (1 - x)));
        System.out.println("N sum: " + result);
        System.out.println(">e sum: " + sumE);
        System.out.println(">(e/10) sum: " + sumDel10);
    }
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);

        int n = 0;
        double x = 0, e = 0;

        try {
            System.out.print("Enter n: "); n = read.nextInt();
            System.out.print("Enter x (-1<X<1): "); x = read.nextDouble();
            System.out.print("Enter e: "); e = read.nextDouble();
            if(x > 1 || x < -1) {
                System.out.println("Enter RIGHT NUMBERS");
            }
            else {
                count(n, x, e);
            }
        }
        catch(Exception f) {
            System.out.println("Something happened. ERROR!");
        }
        read.close();

    }
}


