package courseJavaCore;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class YearlyTask extends Task {

    public YearlyTask(String title, String description, LocalDateTime dateTime, Type type, Frequency frequency) {
        super(title, description, dateTime, type, frequency);
    }

    @Override
    public int compareTo(Task otherTask) {
        return super.compareTo(otherTask);
    }

    @Override
    public boolean appearsln(LocalDate localDate) {
        LocalDate date = getDateTime().toLocalDate();
        return localDate.equals(date) || (localDate.isAfter(date) &&
                localDate.getDayOfMonth() == date.getDayOfMonth() &&
                localDate.getMonth().equals(date.getMonth()));
    }

    @Override
    public String toString() {
        return super.toString() + " - ежегодная задача";
    }
}
