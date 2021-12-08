package task16;

import java.util.*;

class StringComparator implements Comparator<String>
{
    @Override
    public int compare(String s1, String s2)
    {
        int value1 = Integer.parseInt(s1.replaceAll("[^0-9]", ""));
        int value2 = Integer.parseInt(s2.replaceAll("[^0-9]", ""));
        if (value1 < value2) return 1;
        if (value1 == value2){
            return s1.compareTo(s2);
        }
        return -1;
    }
}

public class Main {
    // создаем группы исходя из указанной последовательности
    private static String generateKey(Integer[] groups, int numGroup){
        if (numGroup == 0)
            return "0-" + groups[numGroup] + ":";
        else if (numGroup == groups.length)
            return (groups[numGroup - 1] + 1) + "+";
        else
            return (groups[numGroup-1] + 1) + "-" + groups[numGroup] + ":";
    }

    // поиск названия нужной группы исходя из возраста
    private static String groupChoice(Integer[] groups, Integer age) {
        if (age <= groups[0])
            return "0-" + groups[0] + ":";
        else if (age > groups[groups.length - 1])
            return (groups[groups.length - 1] + 1) + "+";
        else
            for (int i = 1; i < groups.length; i++)
                if ((age > groups[i-1]) && (age <= groups[i]))
                    return (groups[i-1] + 1) + "-" + groups[i] + ":";
        return null;
    }

    // значение имя + возраст
    private static String generateValue(String name, String age){
        return name + " (" + age + ")";
    }

    public static void main(String[] args) {
        // сортировка по убыванию, список имен по возрастанию
        // ключ это имя группы, значение - список ФИО и соответствующенр возраста, сорт по возрастанию
        HashMap<String, TreeSet<String>> peoples = new LinkedHashMap<>();

        String[] inpt = args[0].split(" ");
        int numGroups = inpt.length;
        Integer[] groups = new Integer[numGroups];
        for (int i = 0; i < inpt.length; i++)
            groups[i] = Integer.parseInt(inpt[i]);

        for (int i = numGroups; i >= 0; i--){
            peoples.put(generateKey(groups, i), new TreeSet<>(new StringComparator()));  // создаем основу с ключами-группами
        }

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String s = scanner.nextLine();
                if (s.equals("END")) {
                    break;
                }
                String[] input = s.split(",");
                Person person = new Person(input[0], Integer.parseInt(input[1]));
                // добавляем в нужную группу полученные имя и возраст
                peoples.get(groupChoice(groups, person.getAge())).add(generateValue(person.getName(), person.getAge().toString()));
            }
        }

        for (Map.Entry entry : peoples.entrySet()){
            int l = entry.getValue().toString().length();
            if (l > 2)  // пустые не выводим
                System.out.println(entry.getKey() + " " + (entry.getValue().toString()).substring(1, l-1));
        }
    }
}
