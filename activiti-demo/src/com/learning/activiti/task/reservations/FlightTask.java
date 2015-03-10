package com.learning.activiti.task.reservations;

import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.delegate.DelegateExecution;
import com.learning.activiti.dvo.ReservationDTO;

/**
 * Created by IntelliJ IDEA.
 * User: asmudun
 * Date: May 2, 2014
 * Time: 8:36:35 AM
 */
public class FlightTask implements JavaDelegate {

    public void execute(DelegateExecution execution) {
        ReservationDTO reservationDTO = ((ReservationDTO) execution.getVariable("reservationDTO"));
        reservationDTO.setFlightNumber("BA239");
        reservationDTO.setTotalPrice("465.99");

        System.out.println("---- FlightTask ----" + reservationDTO);
        execution.setVariable("reservationDTO", reservationDTO);
    }
}