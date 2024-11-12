package pl.kurs.service;

import pl.kurs.comparators.PowerToDisplacementComparator;
import pl.kurs.model.Car;
import pl.kurs.model.Company;
import pl.kurs.model.Employee;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CarService {

    public CarService(ListValidationService listValidationService) {
        this.listValidationService = new ListValidationService();
    }

    private ListValidationService listValidationService;

    public List<Car> findCarsWith3BestHpToDisplacementRatios(List<Employee> employeeList) {
        listValidationService.validateEmployeeList(employeeList);
        int AMOUNT_OF_CARS = 3;
        List<Car> bestRatioCars = new ArrayList<>(AMOUNT_OF_CARS);
        List<Car> allCarsList = getAllCarsFromEmployeeList(employeeList);
        allCarsList.sort(PowerToDisplacementComparator.INSTANCE);
        for (int i = 0; i < AMOUNT_OF_CARS; i++) {
            bestRatioCars.add(allCarsList.get(i));
        }

        return bestRatioCars;
    }

    public Car getOldestCarInCompany(Company company){
        listValidationService.validateEmployeeList(company.getEmployeeList());
        List<Car> companyCars = getAllCarsFromEmployeeList(company.getEmployeeList());
        companyCars.sort(Comparator.comparingInt(c -> Integer.parseInt(c.getProductionYear())));
        return companyCars.get(0);
    }

    private List<Car> getAllCarsFromEmployeeList(List<Employee> employeeList) {
        listValidationService.validateEmployeeList(employeeList);
        List<Car> allCarsList = new ArrayList<>();
        for (Employee employee : employeeList) {
            for (Car car : employee.getCarList()) {
                allCarsList.add(car);
            }
        }
        return allCarsList;
    }


}
