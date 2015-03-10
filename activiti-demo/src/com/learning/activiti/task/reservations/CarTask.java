package com.learning.activiti.task.reservations;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import com.learning.activiti.dvo.ReservationDTO;

/**
 * Created by IntelliJ IDEA.
 * User: asmudun
 * Date: May 2, 2014
 * Time: 8:36:48 AM
 */
public class CarTask implements JavaDelegate {

    public void execute(DelegateExecution execution) {
        ReservationDTO reservationDTO = ((ReservationDTO) execution.getVariable("reservationDTO"));
        reservationDTO.setTotalCarRent("155");

        System.out.println("---- CarTask ----" + reservationDTO);
        execution.setVariable("reservationDTO", reservationDTO);
    }

}
