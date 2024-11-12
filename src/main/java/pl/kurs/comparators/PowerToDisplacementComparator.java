package pl.kurs.comparators;

import pl.kurs.model.Car;

import java.util.Comparator;

public class PowerToDisplacementComparator implements Comparator<Car> {

    public static PowerToDisplacementComparator INSTANCE = new PowerToDisplacementComparator();

    @Override
    public int compare(Car o1, Car o2) {
        double ratio1 = o1.getPowerInHp() / o1.getEngineDisplacement();
        double ratio2 = o2.getPowerInHp() / o2.getEngineDisplacement();
        return Double.compare(ratio1, ratio2);
    }
}
