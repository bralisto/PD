package pl.kurs.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Employee extends Person implements Serializable {

    private final double salary;

    public Employee(String firstName, String lastName, LocalDate birthDate, double salary) {
        super(firstName, lastName, birthDate);
        this.salary = salary;
    }

    @Override
    public double getIncome() {
        return salary;
    }
}
