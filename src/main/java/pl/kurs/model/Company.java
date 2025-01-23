package pl.kurs.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Company implements Serializable {

    private List<Employee> employeeList;

    private static final int MAX_EMPLOYEE_CAPACITY = 10;

    public Company() {
        this.employeeList = new ArrayList<>(MAX_EMPLOYEE_CAPACITY);
    }

    public void addEmployee(Employee employee) {
        if (employeeList.size() >= Company.MAX_EMPLOYEE_CAPACITY) {
            System.out.println("Nie można dodać więcej pracowników. Firma już ma " + Company.MAX_EMPLOYEE_CAPACITY + " pracowników.");
            throw new IllegalStateException("Osiągnięto maksymalną ilość pracowników!");
        }
        employeeList.add(employee);
    }

    public List<Employee> getEmployeeList() {
        return new ArrayList<>(employeeList);
    }
}
