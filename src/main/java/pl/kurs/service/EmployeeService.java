package pl.kurs.service;

import pl.kurs.exceptions.NoEmployeeFoundException;
import pl.kurs.model.Car;
import pl.kurs.model.Company;
import pl.kurs.model.Employee;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class EmployeeService {

    private ListValidationService listValidationService;

    public EmployeeService() {
        this.listValidationService = new ListValidationService();
    }

    public void addEmployeeByScanner(Company company, Scanner scanner) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        while (true) {
            try {
                if (company.getEmployeeList().size() >= 10) {
                    System.out.println("Nie można dodać więcej pracowników. Maksymalna liczba to 10.");
                    return;
                }

                System.out.print("Podaj imię: ");
                String firstName = scanner.nextLine();
                System.out.print("Podaj nazwisko: ");
                String lastName = scanner.nextLine();

                System.out.print("Podaj datę urodzenia (yyyy-MM-dd): ");
                String dateOfBirthStr = scanner.nextLine();
                LocalDate dateOfBirth = LocalDate.parse(dateOfBirthStr, dateFormatter);

                System.out.print("Podaj wypłatę: ");
                double salary = scanner.nextDouble();
                scanner.nextLine();


                Employee employee = new Employee(firstName, lastName, dateOfBirth, salary);


                System.out.print("Czy pracownik posiada samochód? (tak/nie): ");
                String hasCar = scanner.nextLine().trim().toLowerCase();


                if (hasCar.equals("tak")) {
                    System.out.print("Podaj markę samochodu: ");
                    String brand = scanner.nextLine();

                    System.out.print("Podaj model samochodu: ");
                    String model = scanner.nextLine();

                    System.out.print("Podaj rok produkcji samochodu: ");
                    String productionYear = scanner.nextLine();

                    System.out.print("Podaj pojemność silnika (w cm3): ");
                    double engineDisplacement = scanner.nextDouble();

                    System.out.print("Podaj moc (w KM): ");
                    int horsepower = scanner.nextInt();
                    scanner.nextLine();


                    Car car = new Car(brand, model, productionYear, engineDisplacement, horsepower, employee);
                    employee.getCars().add(car);
                    System.out.println("Samochód został dodany do pracownika " + firstName + " " + lastName + ".");
                } else {
                    System.out.println("Pracownik nie posiada samochodu.");
                }


                company.addEmployee(employee);
                System.out.println("Pracownik " + firstName + " " + lastName + " został dodany lub zaktualizowany.");
                break;

            } catch (InputMismatchException e) {
                System.out.println("Nieprawidłowa wartość. Spróbuj ponownie.");
                scanner.nextLine();

            } catch (DateTimeParseException e) {
                System.out.println("Nieprawidłowy format daty. Użyj formatu yyyy-MM-dd.");

            } catch (IllegalStateException e) {
                System.out.println(e.getMessage());

            } catch (Exception e) {
                System.out.println("Wystąpił błąd podczas wprowadzania danych: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }


    public Employee findEmployeeWithMostCars(List<Employee> employeeList) throws NoEmployeeFoundException {
        listValidationService.validateNullOrEmptyList(employeeList);
        employeeList.sort(Comparator.comparingInt(e -> e.getCars().size()));
        if (employeeList.get(0).getCars().size() == employeeList.get(1).getCars().size()) {
            throw new NoEmployeeFoundException("Nie znaleziono pracownika o największej ilości pojazdów!");
        }
        return employeeList.get(0);
    }

    public Employee findOldestEmployee(List<Employee> employeeList) {
        listValidationService.validateNullOrEmptyList(employeeList);
        employeeList.sort(Comparator.comparing(Employee::getBirthDate));
        return employeeList.get(0);
    }




}
