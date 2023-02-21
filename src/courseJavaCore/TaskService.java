package courseJavaCore;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.Collections;
import java.util.function.Function;
import java.util.stream.Collectors;
import courseJavaCore.exception.IncorrectArgumentException;
import courseJavaCore.exception.TaskNotFoundException;

import javax.swing.*;
import java.time.format.DateTimeFormatter;


public class TaskService {


    public static Map<Integer, Task> taskMap = new HashMap<>();

    private static Collection<Task> getALlTaskMap() { // коллекция задач
        Collection<Task> values = taskMap.values();
        return values;
    }

    static Collection<Task> getTaskMapForDate() { // для меню: список задач на указанную дату
        TreeSet<Task> taskForDate = new TreeSet<>();
        for (Task task : taskMap.values()) {
             if (task.appearsln(dateFor())) {
                taskForDate.add(task);
            }
            if (taskForDate.isEmpty()) {
                System.out.println("Сегодня все задачи выполнены");
            }
        }
        return taskForDate;
    }

    static void printMenu() { // для меню:
        System.out.println("Доступные команды: " +
                "\n 1 - Добавить задачу " +
                "\n 2 - Удалить задачу " +
                "\n 3 - Получить задачу на указанную дату " +
                "\n 4 - Показать список задач на указанную дату " +
                "\n 5 - Показать удаленные задачи " +
                "\n 6 - Обновить название задачи " +
                "\n 7 - Обновить описание задачи " +
                "\n 8 - Найти задачу " +
                "\n 0 - Выйти из меню");
    }
    static void remove() throws IncorrectArgumentException {   // для меню: удалить задачу по id
        System.out.println("Все задачи: ");
        Scanner scanRemove = new Scanner(System.in);
        for (Task task : getALlTaskMap()) {
            System.out.println("%d. %s [%s] (%s) %n" +
                    task.getId() +
                    Task.getTitle() +
                    Task.getDescription() +
                    Task.getType());
        }
        while (true) {
            try {
                System.out.println("Выберите задачу для удаления: ");
                String idline = scanRemove.nextLine();
                int id = Integer.parseInt(idline);
                TaskService.removeId(id);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Не правильный id");
            } catch (TaskNotFoundException e) {
                System.out.println("Задача для удаления не найдена!");
             }
        }
    }

    private static void removeId(int id) throws TaskNotFoundException {
        if (taskMap.containsKey(id)) {
            taskMap.remove(id);
        } else {
            throw new TaskNotFoundException("id не найден");
        }
    }

    static void ofDeletedTasks() {
        System.out.println("Список удаленных задач: ");
        taskMap = taskMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));
        System.out.println("Карта отсортирована по значениям: " + taskMap);
    }

    static void findTaskOnDate() throws TaskNotFoundException { // для меню: найти задачу по дате
        for (Map.Entry<Integer, Task> taskMap : taskMap.entrySet()) {
            LocalDate localDate = taskMap.getValue().getDateTime().toLocalDate();
            if (localDate.equals(TaskService.dateFor())) {
                System.out.println(taskMap.getKey() + " " + taskMap.getValue());
            }
            if (localDate.isAfter(localDate) && taskMap.getValue().appearsln(localDate)) {
                System.out.println(taskMap.getKey() + " " + taskMap.getValue());
            } else throw new TaskNotFoundException("Нет задачи по выбранной дате: ");
        }
    }


    // п.1 - Добавить задачу
     static void addTask()  {
      TaskService.addId();
      TaskService.selectType();
        try {
            TaskService.selectFrequency();
         } catch (IncorrectArgumentException e) {
             throw new RuntimeException(e);
         }
        TaskService.comeUpTitle();
        TaskService.comeUpDescription();
        TaskService.askDateTime();
         System.out.print("Добавлена задача: " + Task.getTitle() + " " +
                 Task.getIdGenerator() + " " + Task.getType() + " " +
                 Task.getDateTime() + " " + Task.getIdGenerator());
      }

    private static void addId() { // добавить id
        Scanner scanner = new Scanner(System.in);
        int getId = scanner.nextInt();
        System.out.println("Номер задачи по id = " + getId);
    }
    public void add(Task task) {
        taskMap.put(task.getId(), task);
    }



    private static void selectType() {
        System.out.println("Выберите задачу: \n 1 - Рабочая \n 2 - Личная");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
               switch (option) {
            case 1:
                Type.WORK.hashCode();
                System.out.println("Выбрана рабочая задача!");
                break;
            case 2:
               Type.PERSONAL.hashCode();
                System.out.println("Выбрана личная задача!");
                break;
            default:
                System.out.println("Нет такой задачи!");
        }
    }

    private static void selectFrequency() throws IncorrectArgumentException {
        System.out.println("Выберите повторяемость задачи: " +
                "\n 1 – одноразовая " +
                "\n 2 - ежедневно " +
                "\n 3 - еженедельно " +
                "\n 4 - каждый месяц " +
                "\n 5 - каждый год ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                   OneTimeTask.getFrequencyOnce();
                    System.out.println("Выбрана одноразовая задача!");
                    break;
                case 2:
                    DailyTask.getDescription();
                    System.out.println("Выбрана ежедневная задача!");
                    break;
                case 3:
                    WeeklyTask.getDescription();
                    System.out.println("Выбрана еженедельная задача!");
                    break;
                case 4:
                    MonthlyTask.getDescription();
                    System.out.println("Выбрана ежемесячная задача!");
                    break;
                case 5:
                    YearlyTask.getDescription();
                    System.out.println("Выбрана ежегодная задача!");
                    break;
                default:
                    System.out.println ("Пожалуйста, укажите повторяемость своей задачи!");
            }
    }
    private static void comeUpTitle() {
        System.out.println("Добавьте название задачи!");
        Scanner scanner = new Scanner(System.in).useDelimiter("\\n");
        String title = scanner.next();
        System.out.println(title);
    }
    private static void comeUpDescription() {
        System.out.println("Добавьте краткое описание задачи!");
        Scanner scanner = new Scanner(System.in).useDelimiter("\\n");
        String description = scanner.next();
        System.out.println(description);
    }

    static void changeUpTitle() {
        System.out.println("Найлите задачу по id: ");
        Scanner scanId = new Scanner(System.in);
        int id = scanId.nextInt();
        Scanner scanTitle = new Scanner(System.in).useDelimiter("\\n");
        String title = scanTitle.next();
        System.out.println("Измените название задачи: ");
        System.out.println(id + title);
    }


    static void changeUpDescription() {
        System.out.println("Найлите задачу по id: ");
        Scanner scanId = new Scanner(System.in);
        int id = scanId.nextInt();
        Scanner scanDescription = new Scanner(System.in).useDelimiter("\\n");
        String description = scanDescription.next();
        System.out.println("Измените описание задачи: ");
        System.out.println(id + description);
    }

    static void toFindTasks() {
        System.out.println("Найти задачу: ");
        Comparator<Map.Entry<Integer, Task>> comparator = Comparator.comparing(o->o.getValue().getDateTime());
        taskMap = taskMap.entrySet()
                .stream()
                .sorted(comparator)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> {
                            throw new AssertionError();
                        },
                        LinkedHashMap::new
                ));
        taskMap.entrySet().forEach(System.out :: println);
    }

    private static void askDateTime() {

        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Заполните дату в указаном формате: " + "dd.MM.yyyy HH:mm");
                String askDate = scanner.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
                LocalDateTime dateTime = LocalDateTime.parse(askDate, formatter);
                System.out.println("Введена дата:" + dateTime);
            } catch (DateTimeParseException e) {
                System.out.println("Дата введена в неправильном формате!");
            }
        }
     }


    private static LocalDate dateFor() {
        Scanner scanDateY = new Scanner(System.in);
        LocalDate dateForY = LocalDate.parse(scanDateY.next());
        return dateForY;
    }

}





