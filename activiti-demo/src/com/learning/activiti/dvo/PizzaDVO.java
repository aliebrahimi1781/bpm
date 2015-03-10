package com.learning.activiti.dvo;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: asmudun
 * Date: Apr 7, 2014
 * Time: 12:00:15 PM
 */
public class PizzaDVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String pizzaName;
    private String address;
    private Long quantity;
    private Boolean validOrder;
    private Long totalPrice;
    private boolean backingStatus;

    public boolean getBackingStatus() {
        return backingStatus;
    }

    public void setBackingStatus(boolean backingStatus) {
        this.backingStatus = backingStatus;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Boolean getValidOrder() {
        return validOrder;
    }

    public void setValidOrder(Boolean validOrder) {
        this.validOrder = validOrder;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String NEW_LINE = System.getProperty("line.separator");

        // Rule Data
        result.append(this.getClass().getName() + " Object {" + NEW_LINE);
        result.append(" pizzaName: " + pizzaName + NEW_LINE);
        result.append(" address: " + address + NEW_LINE);
        result.append(" quantity: " + quantity + NEW_LINE);
        result.append(" validOrder: " + validOrder + NEW_LINE);
        result.append(" totalPrice: " + totalPrice + NEW_LINE);
        result.append(" backingStatus: " + backingStatus + NEW_LINE);

        result.append("}");

        return result.toString();
    }    

}
