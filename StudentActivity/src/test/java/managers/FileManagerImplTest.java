package managers;

import managers.impl.FileManagerImpl;
import models.StudentActivity;
import org.junit.Rule;
import org.junit.Test;

import static constrains.TestConstrains.*;
import static org.junit.Assert.assertEquals;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.util.List;

public class FileManagerImplTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testGetStudentDataWithInvalidExcelFilePath() throws IOException {
        FileManager fileManager = new FileManagerImpl(INVALID_EXCEL_FILE_PATH);
        expectedException.expectMessage(INVALID_FILE_PAH_EXCEPTION_MESSAGE);
        expectedException.expect(IOException.class);
        fileManager.getStudentActivities();
    }

    @Test
    public void testGetStudentDataWithEmptyExcelFilePath() throws IOException {
        String emptyExcelFilePath = "";
        expectedException.expectMessage(EMPTY_FILE_PATH_EXCEPTION_MESSAGE);
        expectedException.expect(IOException.class);
        FileManager fileManager = new FileManagerImpl(emptyExcelFilePath);
        fileManager.getStudentActivities();
    }

    @Test
    public void testGetStudentDataWithEmptyFileData() throws IOException {
        expectedException.expectMessage(EMPTY_FILE_DATA_EXCEPTION_MESSAGE);
        expectedException.expect(IOException.class);
        FileManager fileManager = new FileManagerImpl(EMPTY_DATA_FILE);
        fileManager.getStudentActivities();
    }

    @Test
    public void testGetStudentDataWithInvalidFileData() throws IOException {
        expectedException.expectMessage(INVALID_FILE_DATA_EXCEPTION_MESSAGE);
        expectedException.expect(IOException.class);
        FileManager fileManager = new FileManagerImpl(INVALID_EXCEL_DATA_FILE);
        fileManager.getStudentActivities();
    }

    @Test
    public void testGetStudentData() throws IOException {
        FileManager fileManager = new FileManagerImpl(VALID_EXCEL_DATA_FILE);
        List<StudentActivity> studentActivities = fileManager.getStudentActivities();
        assertEquals(28089, studentActivities.size());
    }

}
