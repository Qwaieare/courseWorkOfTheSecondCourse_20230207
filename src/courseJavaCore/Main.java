package courseJavaCore;
import courseJavaCore.exception.IncorrectArgumentException;
import courseJavaCore.exception.TaskNotFoundException;
import java.util.Scanner;
import java.util.*;
import java.time.LocalDateTime;
import java.time.*;
import java.time.format.DateTimeFormatter;

import static courseJavaCore.TaskService.*;

public class Main {

    public static void main(String[] args) {
        Task.currentLocalDateAndTime();
        TaskService service = new TaskService();

        service.add(new OneTimeTask("AA", "AAAA", LocalDateTime.now(), Type.WORK, Frequency.FREQUENCY_ONCE));
        service.add(new OneTimeTask("AB", "AABB", LocalDateTime.now(), Type.PERSONAL, Frequency.FREQUENCY_ONCE));
        service.add(new DailyTask("AA", "AAAA", LocalDateTime.now(), Type.WORK, Frequency.FREQUENCY_DAILY));
        service.add(new DailyTask("AB", "AABB", LocalDateTime.now(), Type.PERSONAL, Frequency.FREQUENCY_DAILY));
        service.add(new WeeklyTask("AA", "AAAA", LocalDateTime.now(), Type.WORK, Frequency.FREQUENCY_WEEKLY));
        service.add(new WeeklyTask("AB", "AABB", LocalDateTime.now(), Type.PERSONAL, Frequency.FREQUENCY_WEEKLY));
        service.add(new MonthlyTask("AA", "AAAA", LocalDateTime.now(), Type.WORK, Frequency.FREQUENCY_MONTHLY));
        service.add(new MonthlyTask("AB", "AABB", LocalDateTime.now(), Type.PERSONAL, Frequency.FREQUENCY_MONTHLY));
        service.add(new YearlyTask("AA", "AAAA", LocalDateTime.now(), Type.WORK, Frequency.FREQUENCY_ANNUALLY));
        service.add(new YearlyTask("AB", "AABB", LocalDateTime.now(), Type.PERSONAL, Frequency.FREQUENCY_ANNUALLY));

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
                            findTaskOnDate();
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

        } catch (TaskNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IncorrectArgumentException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            throw new RuntimeException(e);
        }
    }
  }

