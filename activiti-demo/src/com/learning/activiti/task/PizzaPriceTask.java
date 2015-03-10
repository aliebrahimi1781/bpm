package com.learning.activiti.task;

import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.delegate.DelegateExecution;
import com.learning.activiti.dvo.PizzaDVO;

/**
 * Created by IntelliJ IDEA.
 * User: asmudun
 * Date: Apr 7, 2014
 * Time: 12:11:37 PM
 */
public class PizzaPriceTask implements JavaDelegate {

    public void execute(DelegateExecution execution) {
        PizzaDVO pizzaOrderDVO = (PizzaDVO) execution.getVariable("pizzaOrderObj");
        String pizzaName = pizzaOrderDVO.getPizzaName();
        Long quantity = pizzaOrderDVO.getQuantity();


        System.out.println(" Calculating price for Pizza: " + pizzaName + " and Quantity: " + quantity);
        
        Long totalPrice = 0L;
        if("margherita".equalsIgnoreCase(pizzaName)) {
            totalPrice = quantity * 10;
        } else {
            totalPrice = quantity * 15;
        }
        pizzaOrderDVO.setTotalPrice(totalPrice);
        pizzaOrderDVO.setBackingStatus(pizzaOrderDVO.getBackingStatus());

        System.out.println("---- pizzaOrderDVO in Price Task ----" + pizzaOrderDVO);
        execution.setVariable("pizzaOrderDVO", pizzaOrderDVO);
    }

}
