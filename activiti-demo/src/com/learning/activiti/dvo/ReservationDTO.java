package com.learning.activiti.dvo;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: asmudun
 * Date: May 2, 2014
 * Time: 9:12:15 AM
 */
public class ReservationDTO implements Serializable {

    private static final long serialVersionUID = 11L;

    private String customerName;
    private String cardNumber;
    private String customerAddress;
    private String flightNumber;
    private String totalPrice;
    private String totalHotalRent;
    private String totalCarRent;
    private boolean paymentStatus;

    public boolean isPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getTotalHotalRent() {
        return totalHotalRent;
    }

    public void setTotalHotalRent(String totalHotalRent) {
        this.totalHotalRent = totalHotalRent;
    }

    public String getTotalCarRent() {
        return totalCarRent;
    }

    public void setTotalCarRent(String totalCarRent) {
        this.totalCarRent = totalCarRent;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }


    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String NEW_LINE = System.getProperty("line.separator");

        // Rule Data
        result.append(this.getClass().getName() + " Object {" + NEW_LINE);
        result.append(" customerName:    " + customerName + NEW_LINE);
        result.append(" customerAddress: " + customerAddress + NEW_LINE);
        result.append(" cardNumber:      " + cardNumber + NEW_LINE);
        result.append(" flightNumber:    " + flightNumber + NEW_LINE);
        result.append(" totalPrice:      " + totalPrice + NEW_LINE);
        result.append(" totalHotalRent:      " + totalHotalRent + NEW_LINE);
        result.append(" totalCarRent:      " + totalCarRent + NEW_LINE);
        result.append(" paymentStatus:      " + paymentStatus + NEW_LINE);

        result.append("}");

        return result.toString();
    }

}
