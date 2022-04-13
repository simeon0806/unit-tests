package managers.impl;

import models.StudentActivity;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileManagerCache {

    private static final Map<String, List<StudentActivity>> listActivitiesCache = new HashMap<>();

    protected List<StudentActivity> getCacheForFilePath(String filepath) throws IOException {
        if (listActivitiesCache.containsKey(filepath)) {
            return listActivitiesCache.get(filepath);
        } else {
            List<StudentActivity> listActivities = FileManagerImpl.readDataFromFile(filepath);
            listActivitiesCache.put(filepath, listActivities);
            return listActivities;
        }
    }

}
