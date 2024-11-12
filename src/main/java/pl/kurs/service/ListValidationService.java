package pl.kurs.service;

import pl.kurs.model.Employee;

import java.util.List;

public class ListValidationService {

    public <T> void validateEmployeeList(List<T> employeeList) {
        if (employeeList == null || employeeList.isEmpty()) {
            throw new IllegalStateException("Podana lista jest pusta!");
        }
    }
}
