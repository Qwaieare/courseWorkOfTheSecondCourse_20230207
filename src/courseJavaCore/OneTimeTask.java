package courseJavaCore;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class OneTimeTask extends Task {
    private static String frequencyOnce; // периодичность разовая

    public OneTimeTask(String title, String description, LocalDateTime dateTime, Type type, String frequencyOnce) {
        super(title, description, dateTime, type);
        this.frequencyOnce = frequencyOnce;
    }

    public static String getFrequencyOnce() {
        return frequencyOnce;
    }

    public static void setFrequencyOnce(String frequencyOnce) {
        OneTimeTask.frequencyOnce = frequencyOnce;
    }

    @Override
    public int compareTo(Task otherTask) {
        return super.compareTo(otherTask);
    }

    @Override
    public boolean appearsln(LocalDate localDate) {
        return localDate.equals(this.getDateTime().toLocalDate());
    }

    @Override
    public String toString() {
        return super.toString() + frequencyOnce + " - разовая задача";
    }

}
