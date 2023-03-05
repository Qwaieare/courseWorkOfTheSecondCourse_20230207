package courseJavaCore;
import java.awt.*;
import java.security.Key;
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
    private static Set<Task> setTask = new HashSet<>(); // для удаленных задач

    private static Scanner scanner = new Scanner(System.in).useDelimiter("\\n");
    private static Task task;
    static LocalDate localDate;




    private static Collection<Task> getALlTaskMap() { // коллекция задач
        Collection<Task> values = taskMap.values(); // вызван метод values() интерфейса Map, вернет Collection объектов значения, содержащихся в Map
        return values;
    }

    static Collection<Task> getTaskMapForDate() { // список задач на указанную дату
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

    public static void getAllByDate(LocalDate localDate) throws TaskNotFoundException { // найти задачу на указанную дату
        for (Map.Entry<Integer, Task> taskMap : taskMap.entrySet()) {
            LocalDate date = taskMap.getValue().getDateTime().toLocalDate();
            try {
                if (localDate.equals(localDate)) {
                    System.out.println(taskMap.getKey() + " " + taskMap.getValue());
                }
            } catch (NullPointerException e) {
                System.out.println("Нет задачи по выбранной дате: ");
            }
                 if (localDate.isAfter(date) && taskMap.getValue().appearsln(localDate)) {
                    System.out.println(taskMap.getKey() + " " + taskMap.getValue());
                }
            }
        }






    static void printMenu() { // для меню:
        System.out.println();
        System.out.println("Доступные команды: " +
                "\n 1 - Добавить задачу " +
                "\n 2 - Удалить задачу " +
                "\n 3 - Получить задачу на указанную дату " +
                "\n 4 - Показать список задач на указанную дату " +
                "\n 5 - Показать удаленные задачи " +
                "\n 6 - Изменить название задачи " +
                "\n 7 - Изменить описание задачи " +
                "\n 8 - Найти задачу " +
                "\n 0 - Выйти из меню");
    }


    static void remove(Scanner scanner) throws IncorrectArgumentException {   //  удалить задачу по id
        System.out.println("Введите id задачи для удаления: ");
         for (Task task : getALlTaskMap()) {
        }
        while (true) {
            try {
                System.out.println("Выберите задачу для удаления: ");
                String idline = scanner.nextLine();
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
            TaskService.setTask.add(taskMap.remove(id));
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
    private static void MM(int id) { // удаленные задачи
        Task task = taskMap.get(id);
        setTask.add(task);
        taskMap.remove(id);
    }


    static void findTaskOnDate() { // задачи: ключи и значения
        for (Map.Entry<Integer, Task> taskMap : taskMap.entrySet()) {
            System.out.println(taskMap.getKey()+ " " + taskMap.getValue());
        }
    }


    // п.1 - Добавить задачу
     static void addTask() throws IncorrectArgumentException {
         while (true) {
             try {
                 int id = task.getId();
                 taskMap.put(task.getId(), task);
             } catch (NullPointerException e) {
                 System.out.println();
             }
             TaskService.selectType(scanner);
             TaskService.selectFrequency(scanner);
             TaskService.comeUpTitle(scanner);
             TaskService.comeUpDescription(scanner);
             TaskService.askDateTime(scanner);
             try {
                 System.out.println("Задача добавлена");
                 System.out.println(task.toString());
             } catch (NullPointerException e) {
                 System.out.println();
             }
         }
     }

    public void add(Task task) {
        taskMap.put(task.getId(), task);
    }


    private static void selectType(Scanner scanner) {
        try {
            System.out.print("Выберите задачу: \n 1 - Рабочая \n 2 - Личная");
            Type type;
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    type = Type.WORK;
                    System.out.println("Выбрана рабочая задача!");
                    break;
                case 2:
                    type = Type.PERSONAL;
                    System.out.println("Выбрана личная задача!");
                    break;
                default:
                    System.out.println("Нет такой задачи!");
                    break;
            }
            task.getType();
            taskMap.put(task.getId(), task);
        } catch (NullPointerException e) {
            System.out.println();
        }
    }


    private static void selectFrequency(Scanner scanner) {
        try {
            System.out.println("Выберите повторяемость задачи: " +
                    "\n 1 – одноразовая " +
                    "\n 2 - ежедневно " +
                    "\n 3 - еженедельно " +
                    "\n 4 - каждый месяц " +
                    "\n 5 - каждый год ");
            Frequency frequency;
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    frequency = Frequency.FREQUENCY_ONCE;
                    System.out.println("Выбрана одноразовая задача!");
                    break;
                case 2:
                    frequency = Frequency.FREQUENCY_DAILY;
                    System.out.println("Выбрана ежедневная задача!");
                    break;
                case 3:
                    frequency = Frequency.FREQUENCY_WEEKLY;
                    System.out.println("Выбрана еженедельная задача!");
                    break;
                case 4:
                    frequency = Frequency.FREQUENCY_MONTHLY;
                    System.out.println("Выбрана ежемесячная задача!");
                    break;
                case 5:
                    frequency = Frequency.FREQUENCY_ANNUALLY;
                    System.out.println("Выбрана ежегодная задача!");
                    break;
                default:
                    System.out.println("Пожалуйста, укажите повторяемость своей задачи!");
                    break;
            }
            task.getFrequency();
            taskMap.put(task.getId(), task);
        } catch (NullPointerException e) {
            System.out.println();
        }
    }



    private static void comeUpTitle(Scanner scanner) throws IncorrectArgumentException {
        System.out.print("Добавьте название задачи! ");
        String title = scanner.next();
        if (title == null && title.isEmpty() && title.isBlank()) {
            throw new IncorrectArgumentException("Вы не ввели название: ");
        } else {
            System.out.println("Название задачи: " + title);
        }
        try {
            task.getTitle();
            taskMap.put(task.getId(), task);
        } catch (NullPointerException e) {
            System.out.println();
        }
    }

    private static void comeUpDescription(Scanner scanner) throws IncorrectArgumentException {
        System.out.print("Добавьте краткое описание задачи! ");
        String description = scanner.next();
        if (description == null && description.isEmpty() && description.isBlank()) {
            throw new IncorrectArgumentException("Вы не ввели описание задачи: ");
        } else {
            System.out.println("Введено краткое описание задачи: " + description);
        }
        try {
           task.getDescription();
        taskMap.put(task.getId(), task);
        } catch (NullPointerException e) {
            System.out.println();
        }
    }


     static void changeUpTitle() {
         System.out.print("Введите id, чтобы изменить название задачи: ");
         int id = scanner.nextInt();
         System.out.println("Введен id: " + id);
         try {
             System.out.print("Измените название: ");
             String title = scanner.next();
             taskMap.get(id).setTitle(title);
             System.out.println("Добавлено название задачи: " + id + taskMap.get(id).getTitle());
         } catch (IncorrectArgumentException e) {
             throw new RuntimeException(e);
         }
     }


    static void changeUpDescription() {
        System.out.print("Введите id, чтобы изменить описание задачи: ");
        int id = scanner.nextInt();
        System.out.println("Введен id: " + id);
        try {
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


    private static void askDateTime(Scanner scanner) {
        while (true) {
            try {
                System.out.println("Заполните дату в указанном формате: " + "dd.MM.yyyy HH:mm");
                Scanner scanner1 = new Scanner(System.in);
                String askDate = scanner1.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
                LocalDateTime dateTime = LocalDateTime.parse(askDate, formatter);
                System.out.println("Введена дата:" + dateTime);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Дата введена в неправильном формате! ");
            }
        }
     }


    private static LocalDate dateFor() {
        Scanner scanDateY = new Scanner(System.in);
        LocalDate dateForY = LocalDate.parse(scanDateY.next());
        return dateForY;
    }

}





