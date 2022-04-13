package services;

import managers.FileManager;
import managers.impl.FileManagerImpl;
import models.StudentActivity;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import services.impl.WikisServiceImpl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static constrains.TestConstrains.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class WikisServiceImplTest {

    @Test
    public void testGetFilteredActivities() {
        FileManager fileManager = new FileManagerImpl(VALID_EXCEL_DATA_FILE_1);
        WikisService wikisService = new WikisServiceImpl(fileManager);

        List<StudentActivity> firstFourActivities = wikisService.getFilteredActivities().stream().limit(4).collect(Collectors.toList());

        assertEquals(36, wikisService.getFilteredActivities().size());
        assertEquals(getExpectedFilteredActivities().toString(), firstFourActivities.toString());
    }

    @Test
    public void testGetFilteredActivitiesWithEmptyFilePath() {
        FileManager fileManager = new FileManagerImpl("");
        WikisService wikisService = new WikisServiceImpl(fileManager);

        wikisService.getFilteredActivities();
        assertNull(wikisService.getFilteredActivities());
    }

    @Test
    public void testGetWikisNumberOfDateTime() {
        FileManager fileManager = new FileManagerImpl(VALID_EXCEL_DATA_FILE_1);
        WikisService wikisService = new WikisServiceImpl(fileManager);

        assertEquals(8, wikisService.getWikisNumberOfDateTime().size());
        assertEquals(getExpectedDateTimesToWikisMap(), wikisService.getWikisNumberOfDateTime());
    }

    @Test
    public void testGetAbsoluteAndRelativeFrequency() {
        FileManager fileManager = new FileManagerImpl(VALID_EXCEL_DATA_FILE_1);
        WikisService wikisService = new WikisServiceImpl(fileManager);

        Map<LocalDateTime, Double[]> resultMap = wikisService.getAbsoluteAndRelativeFrequency();
        Map<LocalDateTime, Double[]> expectedMap = getExpectedPredefinedMap();

        assertEquals(Arrays.toString(expectedMap.get(FIRST_DATE)), Arrays.toString(roundDoublesToTwoDecimals(resultMap.get(FIRST_DATE))));
        assertEquals(Arrays.toString(expectedMap.get(SECOND_DATE)), Arrays.toString(roundDoublesToTwoDecimals(resultMap.get(SECOND_DATE))));
        assertEquals(Arrays.toString(expectedMap.get(THIRD_DATE)), Arrays.toString(roundDoublesToTwoDecimals(resultMap.get(THIRD_DATE))));
        assertEquals(Arrays.toString(expectedMap.get(FOURTH_DATE)), Arrays.toString(roundDoublesToTwoDecimals(resultMap.get(FOURTH_DATE))));
    }

    @Test
    public void testGetMedianOdd() {
        FileManager fileManager = new FileManagerImpl(VALID_EXCEL_DATA_FILE_2);
        WikisService wikisService = new WikisServiceImpl(fileManager);

        assertEquals(4.0, wikisService.getMedian(), 0.01);
    }

    @Test
    public void testGetMedianEven() {
        FileManager fileManager = new FileManagerImpl(VALID_EXCEL_DATA_FILE_1);
        WikisService wikisService = new WikisServiceImpl(fileManager);

        assertEquals(4.5, wikisService.getMedian(), 0.01);
    }

    @Test
    public void testGetDispersion() {
        FileManager fileManager = new FileManagerImpl(VALID_EXCEL_DATA_FILE_1);
        WikisService wikisService = new WikisServiceImpl(fileManager);

        assertEquals(5.25, wikisService.getDispersion(), 0.01);
    }

    private Double[] roundDoublesToTwoDecimals(Double[] num) {
        Double[] resDoubles = new Double[num.length];
        for (int i = 0; i < num.length; i++) {
            resDoubles[i] = Math.floor(num[i] * 100.0) / 100.0;
        }
        return resDoubles;
    }

    private Map<LocalDateTime, Double[]> getExpectedPredefinedMap() {
        Map<LocalDateTime, Double[]> expectedResult = new TreeMap<>();
        expectedResult.put(FIRST_DATE, new Double[]{1.0, 2.77});
        expectedResult.put(SECOND_DATE, new Double[]{2.0, 5.55});
        expectedResult.put(THIRD_DATE, new Double[]{3.0, 8.33});
        expectedResult.put(FOURTH_DATE, new Double[]{4.0, 11.11});
        return expectedResult;
    }

    private Map<LocalDateTime, Long> getExpectedDateTimesToWikisMap() {
        Map<LocalDateTime, Long> expectedResult = new TreeMap<>();
        expectedResult.put(FIRST_DATE, 1l);
        expectedResult.put(SECOND_DATE, 2l);
        expectedResult.put(THIRD_DATE, 3l);
        expectedResult.put(FOURTH_DATE, 4l);
        expectedResult.put(LocalDateTime.parse("5/02/20, 12:33", FORMATTER), 5l);
        expectedResult.put(LocalDateTime.parse("6/12/20, 09:58", FORMATTER), 6l);
        expectedResult.put(LocalDateTime.parse("7/12/20, 20:54", FORMATTER), 7l);
        expectedResult.put(LocalDateTime.parse("8/01/21, 02:10", FORMATTER), 8l);
        return expectedResult;
    }

    private List<StudentActivity> getExpectedFilteredActivities() {
        List<StudentActivity> filteredActivities = new ArrayList<>();
        StudentActivity studentActivity1 = new StudentActivity("1/01/21, 15:54", "Wiki: Курсова задача/ реферат (30 т.)",
                "Wiki", "Course module viewed", "The user with id '8419' viewed the 'wiki' activity with course module id '5136'.");
        StudentActivity studentActivity2 = new StudentActivity("2/01/21, 15:55", "Wiki: Курсова задача/ реферат (30 т.)",
                "Wiki", "Wiki page viewed", "The user with id '8419' viewed the page with id '286' for the wiki with course module id '5136'.");
        StudentActivity studentActivity3 = new StudentActivity("2/01/21, 15:55", "Wiki: Курсова задача/ реферат (30 т.)",
                "Wiki", "Wiki page viewed", "The user with id '8419' viewed the page with id '286' for the wiki with course module id '5136'.");
        StudentActivity studentActivity4 = new StudentActivity("3/01/21, 20:16", "Wiki: Курсова задача/ реферат (30 т.)",
                "Wiki", "Course module viewed", "The user with id '8424' viewed the 'wiki' activity with course module id '5136'.");
        filteredActivities.add(studentActivity1);
        filteredActivities.add(studentActivity2);
        filteredActivities.add(studentActivity3);
        filteredActivities.add(studentActivity4);
        return filteredActivities;
    }
}
