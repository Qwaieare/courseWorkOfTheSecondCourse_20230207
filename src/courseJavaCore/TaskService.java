package courseJavaCore;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;
import courseJavaCore.exception.IncorrectArgumentException;
import courseJavaCore.exception.TaskNotFoundException;

import java.time.format.DateTimeFormatter;


public class TaskService {


    public static Map<Integer, Task> taskMap = new HashMap<>();
    public static Set<Task> setTask = new HashSet<>();
    public static Task task;

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
        Scanner scanner = new Scanner(System.in);
        for (Task task : getALlTaskMap()) {
        }
        while (true) {
            try {
                System.out.println("Выберите задачу для удаления: ");
                String idline = scanner.nextLine();
                int id = Integer.parseInt(idline);
                TaskService.removeId(id, setTask);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Не правильный id");
            } catch (TaskNotFoundException e) {
                System.out.println("Задача для удаления не найдена!");
             }
        }
    }



    private static void removeId(int id, Set<Task> setTask) throws TaskNotFoundException {
        if (taskMap.containsKey(id)) {
            taskMap.remove(id);
            setTask.add(taskMap.remove(id));
        } else {
            throw new TaskNotFoundException("id не найден");
        }
    }

    static void ofDeletedTasks() {
        if (setTask.size() > 0) {
            System.out.println("В архиве количество удаленных задач: " + setTask.size());
            for (Task task : setTask) {
                System.out.println("Список удаленных задач: " + task);
            }
        } else {
            System.out.println("В архиве нет удаленных задач");
        }
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
     static void addTask() {
         while (true) {
             Scanner scanId = new Scanner(System.in);
             int id = scanId.nextInt();
             TaskService.addId();
             TaskService.selectType(taskMap);
             try {
                 TaskService.selectFrequency(taskMap);
             } catch (IncorrectArgumentException e) {
                 throw new RuntimeException(e);
             }

             TaskService.comeUpTitle();
             TaskService.comeUpDescription();
             TaskService.askDateTime();

         }
     }

    private static void addId() { // добавить id
        Scanner scanner = new Scanner(System.in);
        int getId = scanner.nextInt();
        System.out.println("Номер задачи по id = " + getId);
    }

    public void add(Task task) {
        taskMap.put(task.getId(), task);
    }


    private static void selectType(Map<Integer, Task> taskMap) {
        System.out.println("Выберите задачу: \n 1 - Рабочая \n 2 - Личная");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
               switch (id) {
            case 1:
                taskMap.get(Type.WORK.hashCode());
                System.out.println("Выбрана рабочая задача!");
                break;
            case 2:
                taskMap.get(Type.PERSONAL.hashCode());
                System.out.println("Выбрана личная задача!");
                break;
            default:
                System.out.println("Нет такой задачи!");
                break;
        }
    }


    private static void selectFrequency(Map<Integer, Task> taskMap) throws IncorrectArgumentException {
        System.out.println("Выберите повторяемость задачи: " +
                "\n 1 – одноразовая " +
                "\n 2 - ежедневно " +
                "\n 3 - еженедельно " +
                "\n 4 - каждый месяц " +
                "\n 5 - каждый год ");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
            switch (id) {
                case 1:
                    taskMap.get(OneTimeTask.getFrequencyOnce());
                    System.out.println("Выбрана одноразовая задача!");
                    break;
                case 2:
                    taskMap.get(DailyTask.getFrequencyDaily());
                    System.out.println("Выбрана ежедневная задача!");
                    break;
                case 3:
                    taskMap.get(WeeklyTask.getFrequencyWeekly());
                    System.out.println("Выбрана еженедельная задача!");
                    break;
                case 4:
                    taskMap.get(MonthlyTask.getFrequencyMonthly());
                    System.out.println("Выбрана ежемесячная задача!");
                    break;
                case 5:
                    taskMap.get(YearlyTask.getFrequencyAnnually());
                    System.out.println("Выбрана ежегодная задача!");
                    break;
                default:
                    System.out.println ("Пожалуйста, укажите повторяемость своей задачи!");
                    break;
            }
    }


    private static void comeUpTitle() {
        while (true) {
        System.out.println("Добавьте название задачи!");
        Scanner scan = new Scanner(System.in).useDelimiter("\\n");
         String title = scan.next();
        System.out.print("Введено название задачи: " + title );
         Scanner scanId = new Scanner(System.in);
         int id = scanId.nextInt();
              try {
               taskMap.putIfAbsent(id, task);
               System.out.println(" id задачи: " + id + ", название задачи: " + task.getTitle());
           } catch (InputMismatchException e) {
                System.out.println("Введите название задачи");
            } break;
        }
    }



    private static void comeUpDescription() {
        while (true) {
            System.out.println("Добавьте краткое описание задачи!");
            Scanner scanner = new Scanner(System.in).useDelimiter("\\n");
            String description = scanner.next();
            System.out.println("Введено краткое описание задачи!");
            Scanner scanId = new Scanner(System.in);
            int id = scanId.nextInt();
            try {
                taskMap.putIfAbsent(id, task);
                System.out.println("Добавлено описание задачи: " + taskMap.get(id).getDescription() + description);
            } catch (InputMismatchException e) {
                System.out.println("Введите описание задачи");
            }
        }
    }


     static void changeUpTitle() {
         System.out.println("Чтобы изменить название задачи, найлите id: ");
         Scanner scanId = new Scanner(System.in);
         int id = scanId.nextInt();
         try {
             Scanner scanner = new Scanner(System.in).useDelimiter("\\n");
             System.out.print("Измените название задачи: ");
             String title = scanner.next();
             taskMap.get(id).setTitle(title);
             System.out.println("Добавлено название задачи: " + id + taskMap.get(id).getTitle());
         } catch (IncorrectArgumentException e) {
             throw new RuntimeException(e);
         }
     }


    static void changeUpDescription() {
        System.out.println("Чтобы изменить описание задачи, найлите id: ");
        Scanner scanId = new Scanner(System.in);
        int id = scanId.nextInt();
        try {
            Scanner scanner = new Scanner(System.in).useDelimiter("\\n");
            System.out.println("Измените описание задачи: ");
            String description = scanner.next();
            taskMap.get(id).setDescription(description);
            System.out.println("Добавлено описание задачи: " + id + taskMap.get(id).getDescription());
        } catch (IncorrectArgumentException e) {
            throw new RuntimeException(e);
        }
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
                System.out.println("Заполните дату в указанном формате: " + "dd.MM.yyyy HH:mm");
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





