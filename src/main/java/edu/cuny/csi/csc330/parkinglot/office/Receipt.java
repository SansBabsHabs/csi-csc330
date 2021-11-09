package edu.cuny.csi.csc330.parkinglot.office;

import java.util.Date;
import java.util.Objects;

public class Receipt {
    private final String carId;
    private final Date timeIn;

    public Receipt(String carId, Date timeIn) {
        Objects.requireNonNull(carId);
        Objects.requireNonNull(timeIn);
        this.carId = carId;
        this.timeIn = timeIn;
    }

    @Override
    public String toString() {
        return "Receipt{" + "carId='" + carId + '\'' + ", timeIn=" + timeIn + '}';
    }

    public String getCarId() {
        return carId;
    }

    public Date getTimeIn() {
        return new Date(timeIn.getTime());
    }
}
