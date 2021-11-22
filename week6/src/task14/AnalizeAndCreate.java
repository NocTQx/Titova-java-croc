package task14;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public interface AnalizeAndCreate {

    private static int CountFilms(Integer film, Map usersList){

        int views = 0;
        for (int i = 0; i < usersList.size(); i++){
            Integer[] user = (Integer[]) usersList.get(i+1);
            for (int j = 0; j < user.length; j++)
                if (Objects.equals(user[j], film)) views++;
        }
        return views;
    }

    private static Map<Integer, Integer[]> RetSet() throws IOException {

        Map<Integer, Integer[]> usersList = new HashMap<>();
        int count = 1;
        try (BufferedReader r1 = new BufferedReader(new FileReader("./usersList2.txt"))){
            String line;
            while ((line = r1.readLine()) != null) {
                String[] newList = line.split(",");
                Integer[] newIntList = new Integer[newList.length];
                for (int l = 0; l < newList.length; l++)
                    newIntList[l] = Integer.parseInt(newList[l]);
                for (int k = 0; k < newList.length; k++)
                    usersList.put(count, newIntList);
                count++;
            }
        }
        return usersList;
    }

    private static Map<Integer, Boolean> RetMap() {

        Scanner in = new Scanner(System.in);
        String[] inputStr = in.nextLine().split(",");
        int len = inputStr.length;
        Map<Integer, Boolean> userWatchList = new HashMap<>();
        for (int i = 0; i < len; i ++){
            userWatchList.put(Integer.parseInt(inputStr[i]), false);
        }
        return userWatchList;
    }

    private static Map<Integer, String> RetMapFilms() throws IOException {

        Map<Integer, String> films = new HashMap<>();
        try(BufferedReader r2 = new BufferedReader(new FileReader("./filmsList2.txt"))) {
            String line;
            while ((line = r2.readLine()) != null){
                String[] line_split = line.split(",",2);
                films.put(Integer.parseInt(line_split[0]), line_split[1]);
            }
        }
        return films;
    }

    public static int getRandom(int index){
        int rnd = new Random().nextInt(index);
        return rnd;
    }
    static String CreateRecommendation() throws IOException {
        Map<Integer, Integer[]> usersList = RetSet();
        Map<Integer, Boolean> userWatchList = RetMap();
        Map<Integer, String> films = RetMapFilms();
        Map<Integer, Boolean> curList = new HashMap<>();
        int numFilms = films.size();

        for (int j = 0; j < numFilms; j++) curList.put(j + 1, false);
        int count = 0;
        int numUserFilms = userWatchList.size();

        for (int i = 0; i < usersList.size(); i++) {
            Integer[] user = usersList.get(i + 1);  // список фильмов юзера под номером i+1
            for (int k = 0; k < user.length; k++)  // считаем сколько фильмов совпало
                if (userWatchList.containsKey(user[k]))
                    count++;
            if (count * 2 >= numUserFilms) {  // если больше половины совпало
                for (int k = 0; k < user.length; k++) {
                    if (!userWatchList.containsKey(user[k]))  // для всех несовпавших добавляем их в список всех рекомендованных фильмов
                        curList.put(user[k], true);
                }
            }
            count = 0;  // счетчик совпавших фильмов
        }
        // количество просмотров по всем пользователям
        if (curList.containsValue(true)){
        List<Integer> randomBox = new ArrayList<>(); // номер фильма с максимальным количеством просмотров
        int max = 0;  // максимальное количество просмотров
        int curViews = 0;  // текущее
        for(int i = 0; i < numFilms; i++){
            if (curList.get(i+1)){
                curViews = AnalizeAndCreate.CountFilms(i+1, usersList);
                if (curViews > max){
                    max = curViews;
                    randomBox.clear();
                }
                if (curViews == max)
                    randomBox.add(i+1);
            }
        }
        if (randomBox == null){
            return "Sorry, your preferences are too strange";
        }

        int recommendation = randomBox.get(getRandom(randomBox.size()));

        return films.get(recommendation);
    }
        else return "Sorry, we have no films for watching";
    }
}
