package task9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {
        Path toCopy = Paths.get(args[0]); // путь к директории логов
        Files.walkFileTree(toCopy, new MyFileVisitor()); // метод обхода дерева файлов
        ManipulatesWithFiles.Sort(); // сортировка и вывод полученных данных
    }
}
