package constrains;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestConstrains {

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d/MM/yy, HH:mm");

    // test DateTimes for first results from TEST_ACTIVITY_1 and TEST_ACTIVITY_2
    public static final LocalDateTime FIRST_DATE = LocalDateTime.parse("1/01/21, 15:54", FORMATTER);
    public static final LocalDateTime SECOND_DATE = LocalDateTime.parse("2/01/21, 15:55", FORMATTER);
    public static final LocalDateTime THIRD_DATE = LocalDateTime.parse("3/01/21, 20:16", FORMATTER);
    public static final LocalDateTime FOURTH_DATE = LocalDateTime.parse("4/02/20, 12:33", FORMATTER);

    // excel file paths
    public static final String INVALID_EXCEL_FILE_PATH = "src\\main\\resources\\files\\Logs_Course A_StudentsActivitiesErr.xlsx";
    public static final String INVALID_EXCEL_DATA_FILE = "src\\main\\resources\\files\\invalidExcelFile.xlsx";
    public static final String VALID_EXCEL_DATA_FILE = "src\\main\\resources\\files\\Logs_Course A_StudentsActivities.xlsx";
    public static final String EMPTY_DATA_FILE = "src\\main\\resources\\files\\emptyFile.xlsx";
    public static final String VALID_EXCEL_DATA_FILE_1 = "src\\main\\resources\\files\\TEST_ACTIVITY_1.xlsx";
    public static final String VALID_EXCEL_DATA_FILE_2 = "src\\main\\resources\\files\\TEST_ACTIVITY_2.xlsx";


    // exception messages
    public static final String EMPTY_FILE_PATH_EXCEPTION_MESSAGE = "Provided .xsls file path must not be empty or null.";
    public static final String INVALID_FILE_DATA_EXCEPTION_MESSAGE = "Error reading data from excel file, error message: Provided .xsls file does not contain the correct data.";
    public static final String EMPTY_FILE_DATA_EXCEPTION_MESSAGE = "Error reading data from excel file, error message: Provided .xsls file data must not be empty.";
    public static final String INVALID_FILE_PAH_EXCEPTION_MESSAGE = "Error reading data from excel file, error message: " + INVALID_EXCEL_FILE_PATH +
            " (The system cannot find the file specified)";

}
