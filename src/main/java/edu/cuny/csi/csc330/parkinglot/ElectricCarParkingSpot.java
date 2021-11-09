package edu.cuny.csi.csc330.parkinglot;

import edu.cuny.csi.csc330.parkinglot.car.ElectricCar;
import edu.cuny.csi.csc330.parkinglot.car.Vehicle;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ElectricCarParkingSpot extends ParkingSpot {
    private ElectricCar electricCar;
    private ScheduledExecutorService powerGridScheduler;
    private ScheduledFuture<?> tasks;

    public void setPowerGridScheduler(ScheduledExecutorService powerGridScheduler) {
        this.powerGridScheduler = powerGridScheduler;
    }

    public ElectricCarParkingSpot parkVehicle(Vehicle electricCar) {

        this.electricCar = (ElectricCar) electricCar;

        ScheduledFuture<?> scheduledFuture =
                powerGridScheduler.scheduleWithFixedDelay(
                        () -> {
                            this.electricCar.charge(5);
                        },
                        5,
                        3,
                        TimeUnit.SECONDS);
        this.tasks = scheduledFuture;

        return this;
    }

    public Vehicle removeVehicle() {
        Vehicle v = this.electricCar;

        this.electricCar = null;
        this.tasks.cancel(false);

        this.tasks = null;
        return v;
    }
}
