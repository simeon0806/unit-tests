import managers.impl.FileManagerImpl;
import services.WikisService;
import services.impl.WikisServiceImpl;

import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("*** Project for 'Validation and Verification of Software Systems' ***");
        System.out.println("Please provide the path to the excel file:");

        Scanner scanner = new Scanner(System.in);
        String excelFilePath = scanner.nextLine();
        WikisService wikisService = new WikisServiceImpl(new FileManagerImpl(excelFilePath));

        OutputBuilder outputBuilder = new OutputBuilder();
        System.out.println(outputBuilder.buildUserFriendlyMenu());
        System.out.println("Option: ");
        String choiceNumber = scanner.next();
        while (!choiceNumber.equals("4")) {
            switch (choiceNumber) {
                case "1" :
                    System.out.println(outputBuilder.buildUserFriendlyAbsAndRelativeOutput(wikisService.getAbsoluteAndRelativeFrequency()));
                    break;
                case "2" :
                    System.out.println("Results:");
                    System.out.println("  --- Median value -> " + wikisService.getMedian() + " ---\n");
                    break;
                case "3" :
                    System.out.println("Results:");
                    System.out.println("  --- Dispersion value -> " + wikisService.getDispersion() + " ---\n");
                    break;
                default:
                    System.out.println("Incorrect option value. Try again: ");
            }
            System.out.println("Option: ");
            choiceNumber = scanner.next();
        }
    }

}
