package task9;

import java.io.*;
import java.nio.file.Path;
import java.util.*;

import static java.lang.Long.parseLong;

public class ManipulatesWithFiles {

    static void CopyToEndFile(Path log_filename) throws IOException {
        File allLogs = new File("allLogs.log");
        allLogs.createNewFile(); // создаем файл
        try {
            BufferedReader r = new BufferedReader(new FileReader(String.valueOf(log_filename))); // buffreader для найденного файла(чтение)
            BufferedWriter wr = new BufferedWriter(new FileWriter(allLogs, true)); // buffreader для созданного файла(запись)
            String line;
            while ((line = r.readLine()) != null) {
                wr.write(line + "\n"); // построчно копируем из найденного файла в созданный
            }
            r.close();
            wr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void Sort() throws IOException {
       // File allLogsSorted = new File("allLogsSorted.log");
       // allLogsSorted.createNewFile();
        HashMap map = new HashMap(); // создаем коллекцию для сортировки
        BufferedReader r = new BufferedReader(new FileReader("allLogs.log")); // buffreader для созданного файла (чтение)

        String line;
        while ((line = r.readLine()) != null){
            String[] line_split = line.split(" ", 2);
            map.put(parseLong(line_split[0]), line_split[1]); // представляем данные из файла в виде ключ + значение построчно
        }
        TreeMap treeMap = new TreeMap(map); // ключ + значение в отсортированном порядке
        Set set = treeMap.entrySet();
        Iterator i = set.iterator();

       // FileWriter wr = new FileWriter(allLogsSorted);
        while (i.hasNext()){
            Map.Entry e = (Map.Entry)i.next();
            System.out.println(e.getKey() + " " + e.getValue()); // вывод отсортированного дерева
        }

        File allLogs = new File("allLogs.log");
        allLogs.delete(); // удаляем созданный файл

        r.close();
       // wr.close();
    }
}

