package pl.kurs.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Employee extends Person implements Serializable {

    private final double salary;

    public Employee(String firstName, String lastName, LocalDate birthDate, double salary) {
        super(firstName, lastName, birthDate);
        this.salary = salary;
    }

    public List<Car> getAllCarsFromEmployeeList() {
        return new ArrayList<>(getCars());
    }

    @Override
    public double getIncome() {
        return salary;
    }
}
