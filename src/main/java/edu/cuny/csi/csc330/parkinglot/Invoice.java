package edu.cuny.csi.csc330.parkinglot;

import java.util.Date;
import java.util.Objects;

public class Invoice extends Receipt {

    private final Date timeOut;
    private final double charge;

    public Invoice(Receipt receipt, Date timeOut, double charge) {
        super(receipt.getCarId(), receipt.getTimeIn());
        Objects.requireNonNull(timeOut);
        this.timeOut = timeOut;
        this.charge = charge;
    }

    @Override
    public String toString() {
        return "Invoice{" + "timeOut=" + timeOut + "} " + super.toString();
    }

    public Date getTimeOut() {
        return new Date(timeOut.getTime());
    }

    public double getCharge() {
        return charge;
    }
}
