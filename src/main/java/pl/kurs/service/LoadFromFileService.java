package pl.kurs.service;

import pl.kurs.model.Company;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.util.Scanner;

public class LoadFromFileService {

    public Company loadFromFile() throws Throwable {
        System.out.print("Podaj nazwę pliku do wczytania: ");
        Scanner scanner = new Scanner(System.in);

        String fileNameLoad = getFileNameLoad(scanner);

        File file = new File("src/main/java/pl/kurs/savedData/" + fileNameLoad + ".obj");

        checkIfFileExists(file, fileNameLoad);

        try (FileInputStream fileInputStream = new FileInputStream(file);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            scanner.close();
            return (Company) objectInputStream.readObject();
        }


    }
    private void checkIfFileExists(File file, String fileNameLoad) throws FileNotFoundException {
        if (!file.exists() || !file.isFile()) {
            System.out.println("Plik o nazwie " + fileNameLoad + " nie istnieje.");
            throw new FileNotFoundException();
        }
    }

    private String getFileNameLoad(Scanner scanner) {
        String fileNameLoad;
        try {
            fileNameLoad = scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Wystąpił błąd podczas wczytywania nazwy pliku: " + e.getMessage());
            throw e;
        }
        return fileNameLoad;
    }
}
