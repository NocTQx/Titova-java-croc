package task2;

import java.util.Scanner;

public class Main {

    public static String printBytes(Double n) {
        Double number = n;
        int i = 0;

        while (number/1024 >= 1){
            number /= 1024;
            i++;
        }

        String str = String.format("%.1f", number);

        if (i == 0) {
            return str + " B";
        }
        else if (i == 1) {
            return str + " KB";
        }
        else if (i == 2) {
            return str + " MB";
        }
        else if (i == 3){
            return str + " GB";
        }
        else if (i == 4) {
            return str + " TB";
        }
        return "Error";
    }

    public static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        String input;
        Double num = Double.parseDouble(in.nextLine());
        input = printBytes(num);
        System.out.print(input);
    }

}
