import java.beans.JavaBean;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HwF {

    public static void main(String[] args) {
        System.out.println("Задача на функциональное программирование:");

        String words = "yourapp the quick brown fox jumps over the lazy dog";
        System.out.println("TOP10");
        Arrays.stream(words.split(" "))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(java.util.Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder())
                        .thenComparing(Map.Entry.<String, Long>comparingByKey()))
                .limit(10)
                .forEach(e -> System.out.println(e.getKey() + " - " + e.getValue()));


    }

}
