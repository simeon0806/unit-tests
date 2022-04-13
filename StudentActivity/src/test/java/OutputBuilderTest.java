import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;

import static constrains.TestConstrains.*;
import static org.junit.Assert.assertEquals;

public class OutputBuilderTest {
    OutputBuilder outputBuilder = new OutputBuilder();

    @Test
    public void testBuildUserFriendlyMenu() {
        String expectedMenu = buildExpectedUserFriendlyMenu();

        assertEquals(expectedMenu, outputBuilder.buildUserFriendlyMenu());
    }

    @Test
    public void testBuildUserFriendlyAbsAndRelativeOutput() {
        assertEquals(buildExpectedAbsAndRelativeOutput(getTestMapData()), outputBuilder.buildUserFriendlyAbsAndRelativeOutput(getTestMapData()));
    }

    public String buildExpectedUserFriendlyMenu() {
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

    public String buildExpectedAbsAndRelativeOutput(Map<LocalDateTime, Double[]> data) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Results: \n");

        data.forEach((dateTime, absoluteAndRelativeFrequency) -> {
            stringBuilder.append("  Wiki with DateTime ").append(dateTime).append(": \n");
            stringBuilder.append("    --- Absolute frequency -> ").append(Math.floor(absoluteAndRelativeFrequency[0])).append(", Relative frequency -> ")
                    .append(Math.floor(absoluteAndRelativeFrequency[1])).append(" ---\n");
        });
        return stringBuilder.toString();
    }

    public Map<LocalDateTime, Double[]> getTestMapData() {
        Map<LocalDateTime, Double[]> data = new TreeMap<>();
        data.put(FIRST_DATE, new Double[]{12.0, 16.3});
        data.put(SECOND_DATE, new Double[]{10.5, 18.0});
        data.put(THIRD_DATE, new Double[]{30.0, 21.0});
        return data;
    }
}
