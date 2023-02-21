package courseJavaCore;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class DailyTask extends Task {
    private static String frequencyDaily; // периодичность ежедневно

    public DailyTask(String title, String description, LocalDateTime dateTime, Type type, String frequencyDaily) {
        super(title, description, dateTime, type);
        this.frequencyDaily = frequencyDaily;
     }

    public static String getFrequencyDaily() {
        return frequencyDaily;
    }

    public static void setFrequencyDaily(String frequencyDaily) {
        DailyTask.frequencyDaily = frequencyDaily;
    }

    @Override
    public int compareTo(Task otherTask) {
        return super.compareTo(otherTask);
    }

    @Override
    public boolean appearsln(LocalDate localDate) {
        LocalDate date = this.getDateTime().toLocalDate();
        return localDate.equals(date) || localDate.isAfter(date);
    }

    @Override
    public String toString() {
        return super.toString() + frequencyDaily + " - ежедневная задача";
    }
}
