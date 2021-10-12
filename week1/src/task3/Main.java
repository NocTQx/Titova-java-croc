package task3;
import java.util.Scanner;

public class Main {
    public static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        String[] arr = in.nextLine().split(" ");
        int min, max, imax = 0, imin = 0, len = arr.length;
        int[] ans = new int[len];

        for (int i = 0; i < len; i++) {
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
        if (imin != 0){
            ans[imin] = ans[0];
            ans[imax] = ans[len - 1];
            ans[0] = min;
            ans[len-1] = max;
        }

        for (int i = 0; i < len;  i++) {
            System.out.print(ans[i] + " ");
        }
    }
}