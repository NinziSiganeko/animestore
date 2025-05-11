package za.ac.cput.domain;

import java.time.LocalDate;

public abstract class Payment {

    protected int  paymentId;
    protected double paymentAmount;
    protected LocalDate date;
    protected String paymentMethod;

    public abstract String toString();
}
