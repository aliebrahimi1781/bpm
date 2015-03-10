package com.learning.activiti.task.reservations;

import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.delegate.DelegateExecution;
import com.learning.activiti.dvo.ReservationDTO;

/**
 * Created by IntelliJ IDEA.
 * User: asmudun
 * Date: May 2, 2014
 * Time: 8:32:27 AM
 */
public class ReservationsTask implements JavaDelegate {

    public void execute(DelegateExecution execution) {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setCustomerName((String) execution.getVariable("customerName"));
        reservationDTO.setCardNumber((String) execution.getVariable("cardNumber"));
        reservationDTO.setCustomerAddress((String) execution.getVariable("customerAddress"));

        System.out.println("---- reservationDTO ----" + reservationDTO);
        execution.setVariable("reservationDTO", reservationDTO);
    }
    
}
