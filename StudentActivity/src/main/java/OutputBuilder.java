import java.time.LocalDateTime;
import java.util.Map;

public class OutputBuilder {

    public String buildUserFriendlyMenu() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" --------------------------------------------------------------\n");
        stringBuilder.append("| Please choose a service by providing a number from the menu: |\n");
        stringBuilder.append("|   Option 1 - Calculate Absolute and Relative Frequency       |\n");
        stringBuilder.append("|   Option 2 - Calculate Median value                          |\n");
        stringBuilder.append("|   Option 3 - Calculate Dispersion                            |\n");
        stringBuilder.append("|   Option 4 - End                                             |\n");
        stringBuilder.append(" -------------------------------------------------------------- \n");
        return stringBuilder.toString();
    }

    public String buildUserFriendlyAbsAndRelativeOutput(Map<LocalDateTime, Double[]> resultValues) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Results: \n");

        resultValues.forEach((dateTime, absoluteAndRelativeFrequency) -> {
            stringBuilder.append("  Wiki with DateTime ").append(dateTime).append(": \n");
            stringBuilder.append("    --- Absolute frequency -> ").append(Math.floor(absoluteAndRelativeFrequency[0])).append(", Relative frequency -> ")
                    .append(Math.floor(absoluteAndRelativeFrequency[1])).append(" ---\n");
        });

        return stringBuilder.toString();
    }

}
