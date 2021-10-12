package task3;
import java.util.Scanner;

public class Main {
    public static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        String[] arr = in.nextLine().split(" ");
        int min , max, imax = 0, imin = 0,  len = arr.length;
        int[] ans = new int[len];

        for (int i = 0; i < len; i++){
            ans[i] = Integer.parseInt(arr[i]);
        }
        min = ans[0];
        max = min;

        for (int i = 0; i < len; i++) {
            if (ans[i] > max) {
                imax = i;
                max = ans[i];
            }

            if (ans[i] < min) {
                imin = i;
                min = ans[i];
            }
        }

        int[] res = new int [len];

        for (int i = 0; i < len-1; i++){
                if (i == imin) {
                    res[i] = ans[0];
                    res[0] = min;
                }
                else if (i == imax) {
                    res[i] = ans[len-1];
                    res[len-1] = max;
                }
                else {
                    res[i] = ans[i];
            }
        }

        for (int i = 0; i < len;  i++) {
            System.out.print(res[i] + " ");
        }
    }
}