package pl.kurs.app;

import pl.kurs.model.Car;
import pl.kurs.model.Company;
import pl.kurs.model.Employee;
import pl.kurs.service.*;

import java.util.Scanner;

public class CompanyController {

    private final EmployeeService employeeService;
    private final CarService carService;
    private final SaveToFileService saveToFileService;
    private final LoadFromFileService loadToFileService;

    private final ListValidationService listValidationService;
    private Company company;

    public CompanyController() {
        this.employeeService = new EmployeeService();
        this.listValidationService = new ListValidationService();
        this.carService = new CarService(this.listValidationService);
        this.saveToFileService = new SaveToFileService();
        this.loadToFileService = new LoadFromFileService();
        this.company = new Company();
    }


    public void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                displayMenu();

                int choice = getUserChoice(scanner);

                switch (choice) {
                    case 1:
                        addEmployee(scanner);
                        break;
                    case 2:
                        loadCompanyData();
                        break;
                    case 3:
                        displayEmployeeAndCarInfo();
                        break;
                    case 4:
                        saveCompanyData();
                        break;
                    case 5:
                        analyzeData();
                        break;
                    case 0:
                        System.out.println("Przed wyjściem dokonaj zapisu, wprowadź nazwę pliku.");
                        saveCompanyData();
                        return;
                    default:
                        System.out.println("Niepoprawny wybór, spróbuj ponownie.");
                }
            }
        }
    }

    private void displayMenu() {
        System.out.println("1. Dodaj pracownika");
        System.out.println("2. Wczytaj dane pracowników i pojazdów z pliku");
        System.out.println("3. Wyświetl informacje o pracownikach i ich pojazdach");
        System.out.println("4. Zapisz dane pracowników i pojazdów do pliku");
        System.out.println("5. Analizuj dane");
        System.out.println("0. Exit");
    }

    private int getUserChoice(Scanner scanner) {
        int choice = -1;
        while (choice == -1) {
            System.out.print("Wybierz opcję: ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Niepoprawna wartość. Spróbuj ponownie.");
                scanner.nextLine();
            }
        }
        return choice;
    }

    private void addEmployee(Scanner scanner) {
        employeeService.addEmployeeByScanner(company, scanner);
    }

    private void loadCompanyData() {
        try {
            company = loadToFileService.loadFromFile();
            System.out.println("Dane zostały pomyślnie wczytane.");
        } catch (Exception e) {
            System.out.println("Błąd podczas wczytywania danych: " + e.getMessage());
        }
    }

    private void displayEmployeeAndCarInfo() {
        for (Employee employee : company.getEmployeeList()) {
            System.out.println("Pracownik: " + employee.getFirstName() + " " + employee.getLastName());
            for (Car car : employee.getCars()) {
                System.out.println("  Pojazd: " + car.getProducer() + " " + car.getModel() + ", Rok: " + car.getProductionYear());
            }
        }
    }

    private void saveCompanyData() {
        try {
            saveToFileService.saveToFile(company);
            System.out.println("Dane zapisane pomyślnie.");
        } catch (Exception e) {
            System.out.println("Błąd podczas zapisu danych: " + e.getMessage());
        }
    }

    private void analyzeData() {
        System.out.println("Analiza danych:");

        try {
            System.out.println("1. Pracownik z największą liczbą pojazdów: " + employeeService.findEmployeeWithMostCars(company.getEmployeeList()));
        } catch (Exception e) {
            System.out.println("Błąd podczas analizy pracownika z największą liczbą pojazdów: " + e.getMessage());
        }

        try {
            System.out.println("2. Najstarszy pracownik: " + employeeService.findOldestEmployee(company.getEmployeeList()));
        } catch (Exception e) {
            System.out.println("Błąd podczas analizy najstarszego pracownika: " + e.getMessage());
        }

        try {
            System.out.println("3. Top 3 pojazdy z najlepszym stosunkiem pojemności do mocy: " + carService.findCarsWith3BestHpToDisplacementRatios(company));
        } catch (Exception e) {
            System.out.println("Błąd podczas analizy pojazdów z najlepszym stosunkiem pojemności do mocy: " + e.getMessage());
        }

        try {
            System.out.println("4. Najstarszy pojazd w firmie: " + carService.getOldestCarInCompany(company));
        } catch (Exception e) {
            System.out.println("Błąd podczas analizy najstarszego pojazdu: " + e.getMessage());
        }
    }
}
