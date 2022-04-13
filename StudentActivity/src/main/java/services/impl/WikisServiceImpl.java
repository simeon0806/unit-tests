package services.impl;

import managers.FileManager;
import models.StudentActivity;
import services.WikisService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class WikisServiceImpl implements WikisService {

    private long numberOfWikis;
    private static final String WIKI = "Wiki";
    private FileManager fileManager;

    public WikisServiceImpl(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    /**
     * Get only activities where Component is 'Wiki'
     *
     * @return List of StudentActivity
     */
    @Override
    public List<StudentActivity> getFilteredActivities() {
        try {
            List<StudentActivity> filteredActivities = fileManager.getStudentActivities()
                    .stream()
                    .filter(sa -> sa.getComponent().equals(WIKI))
                    .collect(Collectors.toList());
            this.numberOfWikis = filteredActivities.size();
            return filteredActivities;

        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Map datetime to list of StudentActivity with same datetime
     *
     * @return
     */
    @Override
    public Map<LocalDateTime, Long> getWikisNumberOfDateTime() {
        List<StudentActivity> filteredActivities = getFilteredActivities();
        Map<LocalDateTime, Long> wikisForSpecificDateTime = new TreeMap<>();

        Set<LocalDateTime> dateTimes = filteredActivities.stream().map(StudentActivity::getDateTime).collect(Collectors.toSet());

        dateTimes.forEach(dt -> {
            long wikisCountForSpasticDateTime = filteredActivities.stream().filter(studentActivity -> studentActivity.getDateTime().equals(dt)).count();
            wikisForSpecificDateTime.put(dt, wikisCountForSpasticDateTime);
        });

        return wikisForSpecificDateTime;
    }


    /**
     * Calculate and get absolute and relative frequency
     *
     * @return
     */
    @Override
    public Map<LocalDateTime, Double[]> getAbsoluteAndRelativeFrequency() {
        Map<LocalDateTime, Long> wikisNumberOfDateTime = getWikisNumberOfDateTime();
        Map<LocalDateTime, Double[]> absAndRelFrequencyMap = new TreeMap<>();

        wikisNumberOfDateTime.forEach((dateTime, count) -> {
            double countDouble = count.doubleValue();
            absAndRelFrequencyMap.put(dateTime, new Double[]{countDouble, (countDouble / numberOfWikis) * 100});
        });

        return absAndRelFrequencyMap;
    }

    /**
     * Calculate and get median value
     *
     * @return double
     */
    @Override
    public double getMedian() {
        List<Long> wikisNumber = getWikisNumber();

        int totalElements = wikisNumber.size();
        long middleElement = wikisNumber.get(totalElements / 2);
        if (totalElements % 2 == 0) {
            long sumOfMiddleElements = middleElement +
                    wikisNumber.get(totalElements / 2 - 1);
            return ((double) sumOfMiddleElements) / 2;
        } else {
            return (double) middleElement;
        }
    }

    /**
     * Calculate and get the dispersion of the data
     * @return
     */
    @Override
    public double getDispersion() {
        List<Long> wikisNumber = getWikisNumber();
        int totalElements = wikisNumber.size();

        double mean = (double) wikisNumber.stream().mapToLong(e -> e).sum() / totalElements;
        return wikisNumber.stream().mapToDouble(e -> Math.pow(e - mean, 2)).sum() / totalElements;
    }

    private List<Long> getWikisNumber(){
        return getWikisNumberOfDateTime().values()
                .stream()
                .sorted()
                .collect(Collectors.toList());
    }

}
