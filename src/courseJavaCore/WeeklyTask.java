package courseJavaCore;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class WeeklyTask extends Task {
    private static String frequencyWeekly; // периодичность еженедельно

    public WeeklyTask(String title, String description, LocalDateTime dateTime, Type type, String frequencyWeekly) {
        super(title, description, dateTime, type);
        this.frequencyWeekly = frequencyWeekly;
    }

    public static String getFrequencyWeekly() {
        return frequencyWeekly;
    }

    public static void setFrequencyWeekly(String frequencyWeekly) {
        WeeklyTask.frequencyWeekly = frequencyWeekly;
    }

    @Override
    public int compareTo(Task otherTask) {
        return super.compareTo(otherTask);
    }

    @Override
    public boolean appearsln(LocalDate localDate) {
        LocalDate date = this.getDateTime().toLocalDate();
        return localDate.equals(date) || (localDate.isAfter(date) &&
                localDate.getDayOfWeek().equals(date.getDayOfWeek()));
    }

    @Override
    public String toString() {
        return super.toString() + frequencyWeekly + " - еженедельная задача";
    }
}
