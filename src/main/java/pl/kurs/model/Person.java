package pl.kurs.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Person implements Serializable {

    private final String firstName;

    private final String lastName;

    private final LocalDate birthDate;

    private List<Car> carList;

    public Person(String firstName, String lastName, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.carList = new ArrayList<>();
    }

    public abstract double getIncome();

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public List<Car> getCarList() {
        return carList;
    }
}
