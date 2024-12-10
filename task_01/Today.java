import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Today {
    public static void print() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Not formatted date and time: " + now);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        System.out.println("Formatted date and time: " + formattedDateTime);
    }
}
