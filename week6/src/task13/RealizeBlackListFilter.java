package task13;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class RealizeBlackListFilter implements BlackListFilter {

    public void filterComments(List<String> comments, Set<String> blackList) {

        List<String> allComments = new ArrayList<>(comments);  // Создаем копию списка комментов, чтобы удалять сразу
        for (String allComment : allComments){  // Каждый коммент
            String originalComment = allComment.toLowerCase();
            List <String> nextComm = List.of(originalComment.split("[\\s+.,:!?;\\n]+"));  // разбиваем на слова
            if (!Collections.disjoint(nextComm, blackList)) comments.remove(allComment);  // если есть совпадение в коллекциях, удаляем коммент из оригинального списка
        }
    }
}
