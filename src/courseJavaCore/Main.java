package courseJavaCore;
import courseJavaCore.exception.IncorrectArgumentException;
import courseJavaCore.exception.TaskNotFoundException;
import java.util.Scanner;
import java.util.*;
import java.time.LocalDateTime;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.HashMap;
import java.util.function.BiConsumer;
import java.time.LocalDate;


import static courseJavaCore.TaskService.*;

public class Main {

    public static void main(String[] args) {
        Task.currentLocalDateAndTime();
        TaskService service = new TaskService();

        OneTimeTask onrTask1 = new OneTimeTask("AA", "AAAA", LocalDateTime.now(), Type.WORK, Frequency.FREQUENCY_ONCE);
        OneTimeTask onrTask2 = new OneTimeTask("AB", "AABB", LocalDateTime.now(), Type.PERSONAL, Frequency.FREQUENCY_ONCE);
        DailyTask dailyTask1 = new DailyTask("AA", "AAAA", LocalDateTime.now(), Type.WORK, Frequency.FREQUENCY_DAILY);
        DailyTask dailyTask2 = new DailyTask("AB", "AABB", LocalDateTime.now(), Type.PERSONAL, Frequency.FREQUENCY_DAILY);
        WeeklyTask weeklyTask1 = new WeeklyTask("AA", "AAAA", LocalDateTime.now(), Type.WORK, Frequency.FREQUENCY_WEEKLY);
        WeeklyTask weeklyTask2 =new WeeklyTask("AB", "AABB", LocalDateTime.now(), Type.PERSONAL, Frequency.FREQUENCY_WEEKLY);
        MonthlyTask monthlyTask1 = new MonthlyTask("AA", "AAAA", LocalDateTime.now(), Type.WORK, Frequency.FREQUENCY_MONTHLY);
        MonthlyTask monthlyTask2 = new MonthlyTask("AB", "AABB", LocalDateTime.now(), Type.PERSONAL, Frequency.FREQUENCY_MONTHLY);
        YearlyTask yearlyTask1 = new YearlyTask("AA", "AAAA", LocalDateTime.now(), Type.WORK, Frequency.FREQUENCY_ANNUALLY);
        YearlyTask yearlyTask2 =  new YearlyTask("AB", "AABB", LocalDateTime.now(), Type.PERSONAL, Frequency.FREQUENCY_ANNUALLY);

        System.out.println("Добавлены задачи в карту: ");
        TaskService.taskMap.put(onrTask1.getId(), onrTask1);
        TaskService.taskMap.put(onrTask2.getId(), onrTask2);
        TaskService.taskMap.put(dailyTask1.getId(), dailyTask1);
        TaskService.taskMap.put(dailyTask2.getId(), dailyTask2);
        TaskService.taskMap.put(weeklyTask1.getId(), weeklyTask1);
        TaskService.taskMap.put(weeklyTask2.getId(), weeklyTask2);
        TaskService.taskMap.put(monthlyTask1.getId(), monthlyTask1);
        TaskService.taskMap.put(monthlyTask2.getId(), monthlyTask2);
        TaskService.taskMap.put(yearlyTask1.getId(), yearlyTask1);
        TaskService.taskMap.put(yearlyTask2.getId(), yearlyTask2);
        TaskService.findTaskOnDate(); // Вывод всех ключей и значений из карты
        System.out.println();

        TaskService.taskMap.remove(onrTask2.getId(), onrTask2);
        TaskService.taskMap.remove(dailyTask2.getId(), dailyTask2);
        TaskService.taskMap.remove(weeklyTask2.getId(), weeklyTask2);
        TaskService.taskMap.remove(monthlyTask2.getId(), monthlyTask2);
        TaskService.taskMap.remove(yearlyTask2.getId(), yearlyTask2);
        System.out.println("Остались задачи на карте: ");
        TaskService.findTaskOnDate(); // Вывод всех ключей и значений из карты
        System.out.println();

        Set<Integer> keys = TaskService.taskMap.keySet();
        System.out.println("Ключи: " + keys );

        // не собрала удаленные задачи

        System.out.println();
        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                System.out.println("Пожалйста, выберите команду из пунта меню: ");
                printMenu();
                if (scanner.hasNext()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            addTask();
                            break;
                        case 2:
                            remove(scanner);
                            break;
                        case 3:
                            getAllByDate(localDate);
                            break;
                        case 4:
                            getTaskMapForDate();
                            break;
                        case 5:
                            ofDeletedTasks();
                            break;
                        case 6:
                            changeUpTitle();
                            break;
                        case 7:
                            changeUpDescription();
                            break;
                        case 8:
                            toFindTasks();
                            break;
                        case 0:
                            break label;
                }
                } else {
                    scanner.next();
                    System.out.println("Выберите команды меню: ");
                }
            }
            System.out.println("***Итоговый список задач***");

        }
         catch (IncorrectArgumentException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            throw new RuntimeException(e);
        }
        catch (TaskNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
  }

