package edu.cuny.csi.csc330.parkinglot.car;

public class ElectricCarImpl extends BaseCar implements Vehicle, ElectricCar {
    private final double maxBattey;
    private double currentBattery;

    public ElectricCarImpl(String plateNumber, int maxBattey, int currentBattery) {
        super(plateNumber);
        this.maxBattey = maxBattey;
        this.currentBattery = currentBattery;
    }

    @Override
    public boolean isElectric() {
        return true;
    }

    public double getBatteryPercent() {

        if (currentBattery == maxBattey) {
            return 100;
        }
        return (currentBattery / maxBattey) * 100;
    }

    @Override
    public void charge(double electricity) {
        System.out.println(
                "before charge " + this.getPlateNumber() + " " + this.getBatteryPercent());
        this.currentBattery += electricity;
        if (currentBattery > maxBattey) {
            this.currentBattery = maxBattey;
        }
        System.out.println(
                "after charge " + this.getPlateNumber() + " " + this.getBatteryPercent());
    }
}
