package courseJavaCore;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class MonthlyTask extends Task {
    private static String frequencyMonthly; // периодичность ежемесячно

    public MonthlyTask(String title, String description, LocalDateTime dateTime, Type type, String frequencyMonthly) {
        super(title, description, dateTime, type);
        this.frequencyMonthly = frequencyMonthly;
    }

    public static String getFrequencyMonthly() {
        return frequencyMonthly;
    }

    public static void setFrequencyMonthly(String frequencyMonthly) {
        MonthlyTask.frequencyMonthly = frequencyMonthly;
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
        return super.toString() + frequencyMonthly + " - ежемесячная задача";
    }
}
