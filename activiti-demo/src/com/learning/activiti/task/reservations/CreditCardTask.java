package com.learning.activiti.task.reservations;

import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.delegate.DelegateExecution;
import com.learning.activiti.dvo.ReservationDTO;

/**
 * Created by IntelliJ IDEA.
 * User: asmudun
 * Date: May 2, 2014
 * Time: 8:37:17 AM
 */
public class CreditCardTask implements JavaDelegate {

    public void execute(DelegateExecution execution) {
        ReservationDTO reservationDTO = ((ReservationDTO) execution.getVariable("reservationDTO"));
        reservationDTO.setPaymentStatus(true);

        System.out.println("---- reservationDTO ----" + reservationDTO);
        execution.setVariable("reservationDTO", reservationDTO);
    }

}