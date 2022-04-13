package services;

import models.StudentActivity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface WikisService {

    List<StudentActivity> getFilteredActivities();

    Map<LocalDateTime, Long> getWikisNumberOfDateTime();

    Map<LocalDateTime, Double[]> getAbsoluteAndRelativeFrequency();

    double getMedian();

    double getDispersion();

}
