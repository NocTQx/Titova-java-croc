package task10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        int max = 0;
        int count = 0;
        ArrayList<Record> timeList = new ArrayList<>();
        /*Scanner sc = new Scanner(System.in); // если нужен ввод с консоли
        while (sc.hasNextLine()){
            String[] intArr = sc.nextLine().split(",");*/
        try {
            BufferedReader r = new BufferedReader(new FileReader("./examplelogs.log"));
            String line;
            while ((line = r.readLine()) != null) {
                String[] line_split = line.split(",");
                timeList.add(new Record(("start_time"), Integer.parseInt(line_split[0])));
                timeList.add(new Record(("end_time"), Integer.parseInt(line_split[1])));
            }
            timeList.sort(Comparator.comparing(Record::getTime));
            for (Record record : timeList) {
                if (Objects.equals(record.getStatus(), "start_time")) count++;
                else if (Objects.equals(record.getStatus(), "end_time")) count--;
                if (count > max)
                    max = count;
            }
            r.close();

        } finally {
            System.out.println(max);
        }
    }
}
