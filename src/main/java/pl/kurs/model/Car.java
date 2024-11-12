package pl.kurs.model;

import java.io.Serializable;

public class Car implements Serializable {

    private final String producer;

    private final String model;

    private final String productionYear;

    private final double engineDisplacement;

    private final double powerInHp;

    private final Person owner;

    public Car(String producer, String model, String productionYear, double engineDisplacement, double powerInHp, Person owner) {
        this.producer = producer;
        this.model = model;
        this.productionYear = productionYear;
        this.engineDisplacement = engineDisplacement;
        this.powerInHp = powerInHp;
        this.owner = owner;
    }

    public String getProducer() {
        return producer;
    }

    public String getModel() {
        return model;
    }

    public String getProductionYear() {
        return productionYear;
    }

    public double getEngineDisplacement() {
        return engineDisplacement;
    }

    public double getPowerInHp() {
        return powerInHp;
    }

    public Person getOwner() {
        return owner;
    }
}
