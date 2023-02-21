package courseJavaCore;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class YearlyTask extends Task {
    private static String frequencyAnnually; // периодичность ежегодно

    public YearlyTask(String title, String description, LocalDateTime dateTime, Type type, String frequencyAnnually) {
        super(title, description, dateTime, type);
        this.frequencyAnnually = frequencyAnnually;
    }

    public static String getFrequencyAnnually() {
        return frequencyAnnually;
    }

    public static void setFrequencyAnnually(String frequencyAnnually) {
        YearlyTask.frequencyAnnually = frequencyAnnually;
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
        return super.toString() + frequencyAnnually + " - ежегодная задача";
    }
}
