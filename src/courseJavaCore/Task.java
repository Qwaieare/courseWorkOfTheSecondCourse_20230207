package courseJavaCore;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.*;
import java.util.Objects;
import courseJavaCore.exception.IncorrectArgumentException;

public abstract class Task implements Comparable<Task> {
   private static int idGenerator = 0; // счетчик
   private int id;
   private String title; // заголовок
   private String description; // описание
   private  LocalDateTime dateTime ; // время создания задачи
   private  Type type; // тип задачи

    public Task(String title, String description, LocalDateTime dateTime, Type type) {
        id = idGenerator++;
        this.title = title;
        this.description = description;
        this.dateTime = dateTime;
        this.type = type;
    }

    public static int getIdGenerator() {
        return idGenerator;
    }

    public static void setIdGenerator(int idGenerator) {
        Task.idGenerator = idGenerator;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws IncorrectArgumentException {
        if (title == null && title.isEmpty() && title.isBlank()) {
            throw new IncorrectArgumentException("Вы не ввели название");
        } else {
            this.title = title;
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws IncorrectArgumentException {
        if (description == null && description.isEmpty() && description.isBlank()) {
            throw new IncorrectArgumentException("Вы не ввели описание");
        } else {
            this.description = description;
        }
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Type getType() {
        return type;
    }

    // методы
    public abstract boolean appearsln(LocalDate localDate);

    // текущая дата
    static void currentLocalDateAndTime() {
        ZoneId zid = ZoneId.of("Europe/Moscow");
        LocalDateTime today = LocalDateTime.now(zid);
        System.out.println("Текущая дата : " + today);

    }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Task task = (Task) o;
            return id == task.id && Objects.equals(title, task.title) &&
                    Objects.equals(description, task.description) &&
                    Objects.equals(dateTime, task.dateTime) && type == task.type;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, title, description, dateTime, type);
        }

        @Override
        public int compareTo(Task otherTask) {
            if (otherTask == null) {
                return 1;
            }
            return this.dateTime.toLocalTime().compareTo(otherTask.dateTime.toLocalTime());
        }


    @Override
    public String toString() {
        return "Зфдфча № " + id +
                ", название: " + title + '\'' +
                ", краткое описание: " + description + '\'' +
                ", дата: " + dateTime +
                ", тип задачи: " + type;
    }
}














