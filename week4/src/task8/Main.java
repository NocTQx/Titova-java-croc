package task8;

import java.io.*;
// Программа делалась на убунте, поэтому кодировка utf-8!
public class Main {

    public static void main(String[] args) throws IOException {
        int count = 0;
        boolean flag = false;
        try {
            Reader r = new InputStreamReader(
                    new FileInputStream(args[0]), "utf-8");
            int c;
            while ((c = r.read()) != -1) {
                    while ((((char)c == ' ') || ((char)c == '\n')) && (c != -1)){ // пока пробелы считываем дальше
                        c = r.read();
                    }
                    while ((((char)c != ' ') && ((char)c != '\n')) && (c != -1)){ // пока все остальное считываем дальше
                        flag = true;
                        c = r.read();
                    }
                    if (flag) count ++; // вдруг текст на пробел или символ перевода строки заканчивается
            }
            } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(count);
    }
}
