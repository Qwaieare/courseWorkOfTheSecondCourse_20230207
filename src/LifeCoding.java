import java.util.Scanner;
import java.util.Iterator;
import java.util.Map;
import java.util.*;

public class LifeCoding {

    public static void main(String[] args) {
        System.out.println("Билет №17");
        System.out.println("Задача на лайв-кодинг - 5");

        System.out.println("Введите слово - ротор"); // введите слово палиндром - ротор, для выполнения программы
        Scanner scanner = new Scanner(System.in);
        String word;
        word = scanner.nextLine().toLowerCase();
        String rev = new StringBuilder(word).reverse().toString();

        if (word.length() == 5) {
            System.out.println("true");
            if (word.charAt(0) == word.charAt(4) &&
                    word.charAt(1) == word.charAt(3)) {
                System.out.println("слово является палиндромом");
                if (word.equalsIgnoreCase(rev)) {
                    System.out.println("слово является палиндромом");
                }
            } else {
                System.out.println("false");
            }
        }

        System.out.println("");
        System.out.println("Задача на лайв-кодинг - 6");

        Map<Integer,String> keyValue = new HashMap<Integer,String>();
        keyValue.put(1, "New");
        keyValue.put(2, "Day");
        keyValue.put(3, "Beautiful day!");
        System.out.println(keyValue.size());
        System.out.println("Цикл While:");
        Iterator iter = keyValue.entrySet().iterator();
        while(iter.hasNext()) {
            Map.Entry qurentMe = (Map.Entry) iter.next();
            System.out.println("Ключ это " + qurentMe.getKey() + " Значение это " + qurentMe.getValue());
        }
        System.out.println("Цикл For:");
        for(Map.Entry qurentMe2: keyValue.entrySet()) {
            System.out.println("Ключ это: " + qurentMe2.getKey() + " Значение это: " + qurentMe2.getValue());
        }
    }


}


