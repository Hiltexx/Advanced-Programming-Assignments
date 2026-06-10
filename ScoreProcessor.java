package com.onboarding;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScoreProcessor {

    public int processScoreFile(String filePath)
            throws FileNotFoundException {

        Scanner scanner = null;

        try {

            File file = new File(filePath);

            scanner = new Scanner(file);

            String content = scanner.nextLine();

            int score = Integer.parseInt(content);

            int result = score * 10;

            System.out.println(result);

            return result;

        } catch (FileNotFoundException e) {

            System.out.println("Error: File not found");

            throw e;

        } catch (NumberFormatException e) {

            System.out.println("Error: Invalid number format");

            throw e;

        } finally {

            if (scanner != null) {
                scanner.close();
            }

            System.out.println("File cleanup completed");
        }
    }
}
