package courseJavaCore;
import courseJavaCore.exception.IncorrectArgumentException;
import courseJavaCore.exception.TaskNotFoundException;
import java.util.Scanner;
import java.util.*;
import java.time.LocalDateTime;
import java.time.*;
import java.time.format.DateTimeFormatter;
import static courseJavaCore.TaskService.printMenu;
import static courseJavaCore.TaskService.addTask;
import static courseJavaCore.TaskService.remove;
import static courseJavaCore.TaskService.findTaskOnDate;
import static courseJavaCore.TaskService.getTaskMapForDate;
import static courseJavaCore.TaskService.ofDeletedTasks;
import static courseJavaCore.TaskService.changeUpTitle;
import static courseJavaCore.TaskService.changeUpDescription;
import static courseJavaCore.TaskService.toFindTasks;
public class Main {

    public static void main(String[] args) {
        Task.currentLocalDateAndTime();
        TaskService service = new TaskService();

        service.add(new OneTimeTask("AA", "AAAA", LocalDateTime.now(), Type.WORK, "frequencyOnce"));
        service.add(new OneTimeTask("AB", "AABB", LocalDateTime.now(), Type.PERSONAL, "frequencyOnce"));
        service.add(new DailyTask("AA", "AAAA", LocalDateTime.now(), Type.WORK, "frequencyDaily"));
        service.add(new DailyTask("AB", "AABB", LocalDateTime.now(), Type.PERSONAL, "frequencyDaily"));
        service.add(new WeeklyTask("AA", "AAAA", LocalDateTime.now(), Type.WORK, "frequencyWeekly"));
        service.add(new WeeklyTask("AB", "AABB", LocalDateTime.now(), Type.PERSONAL, "frequencyWeekly"));
        service.add(new MonthlyTask("AA", "AAAA", LocalDateTime.now(), Type.WORK, "frequencyMonthly"));
        service.add(new MonthlyTask("AB", "AABB", LocalDateTime.now(), Type.PERSONAL, "frequencyMonthly"));
        service.add(new YearlyTask("AA", "AAAA", LocalDateTime.now(), Type.WORK, "frequencyAnnually"));
        service.add(new YearlyTask("AB", "AABB", LocalDateTime.now(), Type.PERSONAL, "frequencyAnnually"));

        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                printMenu();
                System.out.println("Пожалйста, выберите команду из пунта меню. Будьте внимательны при выборе: ");
                if (scanner.hasNext()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            addTask();
                            break;
                        case 2:
                            remove();
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
                        default:
                            System.out.println("Пожалуйста, начните сначала!");
                    }
                } else {
                        scanner.next();
                    System.out.println("Выберите команды меню: ");
                }
            }
        } catch (TaskNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IncorrectArgumentException e) {
            throw new RuntimeException(e);
        }


    }
  }

