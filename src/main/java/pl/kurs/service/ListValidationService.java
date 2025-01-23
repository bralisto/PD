package pl.kurs.service;

import java.util.List;

public class ListValidationService {

    public <T> void validateNullOrEmptyList(List<T> employeeList) {
        if (employeeList == null || employeeList.isEmpty()) {
            throw new IllegalStateException("Podana lista jest pusta!");
        }
    }
}
