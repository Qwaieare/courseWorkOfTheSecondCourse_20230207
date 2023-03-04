import java.util.Scanner;
import java.util.Iterator;
import java.util.Map;
import java.util.*;

public class LifeCoding {

    public static void main(String[] args) {
        System.out.println("Билет №17");
        System.out.println("Задача на лайв-кодинг - 5");

        System.out.println("Введите слово:"); // введите слово палиндром - ротор, для выполнения программы
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

        System.out.println();
        System.out.println("Билет №17");
        System.out.println("Задача на лайв-кодинг - 5 - другой вариант");

        System.out.print("Введите слово: "); // введите слово кадрабардак
        Scanner scanner1 = new Scanner(System.in);
        String word1 = scanner1.nextLine();
        int length = word1.length();
        boolean isPalindrome = true;
        for(int i = 0; i < length; i++)
        {
            if(word1.charAt(i) != word1.charAt(length-1-i)) {
                System.out.println("Слово не палиндром.");
                isPalindrome = false;
                break;
            }
        }
        if(isPalindrome) {
            System.out.println("Слово палиндром.");
        }

    }

}


