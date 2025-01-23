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

    public List<Car> findCarsWith3BestHpToDisplacementRatios(Company company) {
        listValidationService.validateNullOrEmptyList(company.getEmployeeList());
        List<Car> bestRatioCars = new ArrayList<>(3);
        List<Car> companyCars = getCompanyCars(company);
        companyCars.sort(PowerToDisplacementComparator.INSTANCE);
        for (int i = 0; i < 3; i++) {
            bestRatioCars.add(companyCars.get(i));
        }

        return bestRatioCars;
    }

    public Car getOldestCarInCompany(Company company) {
        listValidationService.validateNullOrEmptyList(company.getEmployeeList());
        List<Car> companyCars = getCompanyCars(company);
        companyCars.sort(Comparator.comparingInt(c -> Integer.parseInt(c.getProductionYear())));
        return companyCars.get(0);
    }

    private static List<Car> getCompanyCars(Company company) {
        List<Car> companyCars = new ArrayList<>();
        for (Employee employee : company.getEmployeeList()) {
            companyCars.addAll(employee.getCars());
        }
        return companyCars;
    }


}
