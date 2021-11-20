package task13;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        List<String> comments = new ArrayList<>();  // Комментарии
        comments.add("Коммент номер один");
        comments.add("Коммент номер два");
        comments.add("Не важно");
        comments.add("Проверка \n проверяет");
        comments.add("Этот коммент хороший");
        comments.add("А этот коммент плохой");
        comments.add("Коммент очень плохой");
        comments.add("Проверка, знаков препинания");
        comments.add("Проверка! знаков препинания 2");
        comments.add("Проверка? знаков препинания");
        comments.add("Проверка: знаков препинания");
        comments.add("знаков препинания");

        Set<String> blackList = new HashSet<>();  // Запрещенные слова
        blackList.add("плохой");
        blackList.add("Проверка");
        blackList.add("важно");

        BlackListFilter.filterComments(comments, blackList);
        System.out.println(comments);
    }
}
