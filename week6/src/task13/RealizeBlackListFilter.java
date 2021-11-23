package task13;

import java.util.*;

public class RealizeBlackListFilter implements BlackListFilter {

    public void filterComments(List<String> comments, Set<String> blackList) {

        ListIterator<String> listIter = comments.listIterator();
        while (listIter.hasNext()) {
                String originalComment = listIter.next().toLowerCase();
                List<String> nextComm = List.of(originalComment.split("[\\s+.,:!?;\\n]+"));  // разбиваем на слова
                if (!Collections.disjoint(nextComm, blackList))
                    listIter.remove();  // если есть совпадение в коллекциях, удаляем коммент из списка
        }
    }
}
