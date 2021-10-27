package edu.cuny.csi.csc330.parkinglot;

import edu.cuny.csi.csc330.parkinglot.util.TimeProvider;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OfficeTest {

    @Test
    public void testPay_5seconds() {
        Receipt receipt = mock(Receipt.class);

        TimeProvider timeProvider = mock(TimeProvider.class);
        when(receipt.getCarId()).thenReturn("abc");
        when(receipt.getTimeIn()).thenReturn(new Date(10000));
        when(timeProvider.now()).thenReturn(new Date(15000));

        Office office = new Office();
        office.setTimeProvider(timeProvider);
        office.setTaxRate(0);
        Invoice pay = office.pay(receipt);
        assertEquals(
                10,
                pay.getCharge(),
                "fee is calculated correctly 2 dollars per second for 5 seconds with no tax" + pay);
    }
}
