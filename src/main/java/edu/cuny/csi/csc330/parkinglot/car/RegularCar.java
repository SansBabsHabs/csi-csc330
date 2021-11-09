package edu.cuny.csi.csc330.parkinglot.car;

public class RegularCar implements Vehicle {

    private final String plateNumber;

    public RegularCar(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    @Override
    public String getPlateNumber() {
        return this.plateNumber;
    }

    public boolean isElectric() {

        return false;
    }

    public boolean isLong() {

        return false;
    }

    public boolean isHandicap() {

        return false;
    }
}
