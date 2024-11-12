package pl.kurs.service;

import pl.kurs.model.Company;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class SaveToFileService {

    private final String DEAFULT_FILE_NAME = "objects";

    public void saveToFile(Company company) {
        File directory = createDirectory();

        File file = createFile(directory);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(company);
            System.out.println("Dane zostały zapisane do pliku " + file.getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException("Wystąpił błąd podczas zapisu do pliku: " + e.getMessage(), e);
        }
    }

    private File createFile(File directory) {
        Scanner scanner = new Scanner(System.in);
        File file;
        System.out.print("Podaj nazwę pliku do zapisu (lub naciśnij Enter, aby użyć domyślnej nazwy): ");
        try {
            String fileName = scanner.nextLine();
            if (fileName == null || fileName.isBlank()) {
                file = new File(directory, DEAFULT_FILE_NAME + ".obj");
            } else {
                file = new File(directory, fileName + ".obj");
            }
        } catch (Exception e) {
            System.out.println("Wystąpił błąd podczas pobierania nazwy pliku: " + e.getMessage());
            file = new File(directory, DEAFULT_FILE_NAME + ".obj");
        }
        return file;
    }

    private File createDirectory() {
        File directory = new File("src/main/java/pl/kurs/savedData");
        if (!directory.exists()) {
            directory.mkdirs();
        }
        return directory;
    }
}
