package task9;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class MyFileVisitor implements FileVisitor<Path> {
    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs){
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
        if (!((path.toString().endsWith(".log")) || (path.toString().endsWith(".trace")))) { // нужны только файлы .log и .trace, иначе продолжаем обход
            return FileVisitResult.CONTINUE;
        }
        ManipulatesWithFiles.CopyToEndFile(Path.of(path.toString())); // найденный файл копируем в конец нового файла со всеми записями
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc){
        System.err.println(exc);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc){
        return FileVisitResult.CONTINUE;
    }

}
