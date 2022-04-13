package managers;

import models.StudentActivity;

import java.io.IOException;
import java.util.List;

public interface FileManager {

    List<StudentActivity> getStudentActivities() throws IOException;

}
