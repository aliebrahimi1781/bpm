package com.learning.activiti.task;

import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.delegate.DelegateExecution;
import com.learning.activiti.dvo.PizzaDVO;

/**
 * Created by IntelliJ IDEA.
 * User: asmudun
 * Date: Apr 7, 2014
 * Time: 11:52:29 AM
 */
public class PizzaOrderTask implements JavaDelegate {

    public void execute(DelegateExecution execution) {
        PizzaDVO pizzaOrder = new PizzaDVO();
        String pizzaName = (String) execution.getVariable("pizzaName");
        String address = (String) execution.getVariable("address");
        Long quantity = (Long) execution.getVariable("quantity");

        Boolean validOrder = true;
        if(pizzaName == null && address == null) {
           validOrder = false;
        }

        if(validOrder) {
          pizzaOrder.setBackingStatus(true);
        } else {
          pizzaOrder.setBackingStatus(false);
        }

        pizzaOrder.setPizzaName(pizzaName);
        pizzaOrder.setAddress(address);
        pizzaOrder.setQuantity(quantity);
        pizzaOrder.setValidOrder(validOrder);

        System.out.println("------ pizzaOrder ------" + pizzaOrder);
        execution.setVariable("pizzaOrderObj", pizzaOrder);
    }

}
