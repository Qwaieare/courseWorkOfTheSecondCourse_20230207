package courseJavaCore;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class MonthlyTask extends Task {

    public MonthlyTask(String title, String description, LocalDateTime dateTime, Type type, Frequency frequency) {
        super(title, description, dateTime, type, frequency);
    }

    @Override
    public int compareTo(Task otherTask) {
        return super.compareTo(otherTask);
    }

    @Override
    public boolean appearsln(LocalDate localDate) {
        LocalDate date = this.getDateTime().toLocalDate();
        return date.equals(localDate) || (localDate.isAfter(date) &&
                localDate.getDayOfMonth() == date.getDayOfMonth());
    }

    @Override
    public String toString() {
        return super.toString() + " - ежемесячная задача";
    }
}
