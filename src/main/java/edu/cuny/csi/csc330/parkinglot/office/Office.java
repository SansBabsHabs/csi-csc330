package edu.cuny.csi.csc330.parkinglot.office;

import edu.cuny.csi.csc330.parkinglot.util.TimeProvider;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Office {

    private TimeProvider timeProvider;
    private double taxRate = 0d;

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    public Receipt createReceipt(String carId) {
        return new Receipt(carId, timeProvider.now());
    }

    public void setTimeProvider(TimeProvider timeProvider) {
        this.timeProvider = timeProvider;
    }

    public Invoice pay(Receipt receipt) {

        Date timeIn = receipt.getTimeIn();
        Date timeout = timeProvider.now();

        long seconds =
                Duration.between(timeIn.toInstant(), timeout.toInstant()).get(ChronoUnit.SECONDS);
        double rate = 2.0;
        double charge = seconds * rate * (1 + taxRate);

        return new Invoice(receipt, timeout, charge);
    }
}
