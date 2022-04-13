package managers.impl;

import managers.FileManager;
import models.StudentActivity;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static managers.impl.FileManagerExceptionConstrains.*;

public class FileManagerImpl extends FileManagerCache implements FileManager {

    private String excelFilePath;

    public FileManagerImpl(String excelFilePath) {
        this.excelFilePath = excelFilePath;
    }

    @Override
    public List<StudentActivity> getStudentActivities() throws IOException {
        try {
            if (excelFilePath == null || excelFilePath.isEmpty()) {
                throw new IllegalArgumentException(EMPTY_FILE_PATH_EXCEPTION_MESSAGE);
            } else {
                return getCacheForFilePath(excelFilePath);
            }
        } catch (IOException | IllegalArgumentException e) {
            throw new IOException("Error reading data from excel file, error message: " + e.getMessage());
        }
    }

    /**
     * Function that reads data from excel file
     *
     * @param excelFilePath
     * @throws IOException
     * @throws IllegalArgumentException
     */
    static List<StudentActivity> readDataFromFile(String excelFilePath) throws IOException, IllegalArgumentException {
        List<StudentActivity> listActivities = new LinkedList<>();

        try (Workbook workbook = new XSSFWorkbook(new FileInputStream(excelFilePath))) {
            Sheet firstSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = firstSheet.iterator();
            if (!iterator.hasNext()) {
                throw new IllegalArgumentException(EMPTY_FILE_DATA_EXCEPTION_MESSAGE);
            }
            iterator.next();

            while (iterator.hasNext()) {
                Row nextRow = iterator.next();
                Iterator<Cell> cellIterator = nextRow.cellIterator();
                StudentActivity activity = new StudentActivity();

                while (cellIterator.hasNext()) {
                    Cell nextCell = cellIterator.next();
                    int columnIndex = nextCell.getColumnIndex();
                    if (nextCell.toString().equals("")) {
                        throw new IllegalArgumentException(INVALID_FILE_DATA_EXCEPTION_MESSAGE);
                    }
                    switch (columnIndex) {
                        case 0:
                            activity.setDateTime(nextCell.getStringCellValue());
                            break;
                        case 1:
                            activity.setEventContext(nextCell.getStringCellValue());
                            break;
                        case 2:
                            activity.setComponent(nextCell.getStringCellValue());
                            break;
                        case 3:
                            activity.setEventName(nextCell.getStringCellValue());
                            break;
                        case 4:
                            activity.setDescription(nextCell.getStringCellValue());
                            break;
                    }
                }

                listActivities.add(activity);
            }
        }

        return listActivities;
    }

}
