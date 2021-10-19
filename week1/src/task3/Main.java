package task3;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ManipulateWithArray obj = new ManipulateWithArray();
        int myArr[] = obj.CreateArr();
        int MinMax[] = obj.find(myArr);
        int resultArr[] = obj.Swap(MinMax[0], MinMax[1], MinMax[2], MinMax[3], myArr);
        obj.PrintArr(resultArr);

    }

    public static class ManipulateWithArray {

        // ввод массива
        public int[] CreateArr() {
            Scanner in = new Scanner(System.in);
            String[] inputArr = in.nextLine().split(" ");
            int len = inputArr.length;
            int[] arr = new int[len];

            for (int i = 0; i < len; i++) {
                arr[i] = Integer.parseInt(inputArr[i]);
            }
            return arr;
        }

        // поиск минимального и максимального элементов, и их индексов
        public int[] find(int[] tmpArr) {
            int min, max, imax = 0, imin = 0;
            int len = tmpArr.length;

            min = tmpArr[0];
            max = min;

            for (int i = 0; i < len; i++) {
                if (tmpArr[i] > max) {
                    imax = i;
                    max = tmpArr[i];
                }

                if (tmpArr[i] < min) {
                    imin = i;
                    min = tmpArr[i];
                }
            }

            int retValue[] = new int[4];
            retValue[0] = min;
            retValue[1] = imin;
            retValue[2] = max;
            retValue[3] = imax;

            return retValue;
        }

        // преобразование нового массива
        public int[] Swap(int min, int iMin, int max, int iMax, int[] Arr) {
            int len = Arr.length;
            if ((iMin == 0) && (iMax == len - 1)) { // если минимальный и максимальный элементы на своих местах
                return Arr;
            }
            if ((iMax == 0) && (iMin == len - 1)) { //если минимальный и максимальный элементы на местах друг друга
                Arr[0] = min;
                Arr[len - 1] = max;
                return Arr;
            }

            if (iMax > iMin) {
                Arr[iMin] = Arr[0];
                Arr[0] = min;
                Arr[iMax] = Arr[len - 1];
                Arr[len - 1] = max;
            } else if (iMax < iMin) {
                Arr[iMax] = Arr[len - 1];
                Arr[len - 1] = max;
                if (iMin == len - 1) iMin = iMax;
                Arr[iMin] = Arr[0];
                Arr[0] = min;

            }
            return Arr;
        }

        // вывод массива
        public void PrintArr(int[] Arr) {
            int len = Arr.length;
            for (int i = 0; i < len; i++) {
                System.out.print(Arr[i] + " ");
            }
        }
    }
}