package edu.cuny.csi.csc330.parkinglot.car;

public abstract class BaseCar implements Vehicle {
    private final String plateNumber;
    private boolean isLong;
    private boolean isHandicap;

    public BaseCar(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    @Override
    public boolean isElectric() {
        return false;
    }

    @Override
    public boolean isLong() {
        return this.isLong;
    }

    @Override
    public boolean isHandicap() {
        return isHandicap;
    }

    @Override
    public String getPlateNumber() {
        return plateNumber;
    }
}
