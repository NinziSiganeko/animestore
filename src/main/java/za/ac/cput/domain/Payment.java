package za.ac.cput.domain;

import java.time.LocalDate;
/*
*
* Author S Ninzi(222522569)
*
* */

public abstract class Payment {

    protected int  paymentId;
    protected double paymentAmount;
    protected LocalDate date;
    protected String paymentMethod;

    public abstract String toString();
}
