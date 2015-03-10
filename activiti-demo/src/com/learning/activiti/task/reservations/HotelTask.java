package com.learning.activiti.task.reservations;

import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.delegate.DelegateExecution;
import com.learning.activiti.dvo.ReservationDTO;

/**
 * Created by IntelliJ IDEA.
 * User: asmudun
 * Date: May 2, 2014
 * Time: 8:35:09 AM
 */
public class HotelTask implements JavaDelegate {

    public void execute(DelegateExecution execution) {
        ReservationDTO reservationDTO = ((ReservationDTO) execution.getVariable("reservationDTO"));
        reservationDTO.setTotalHotalRent("890");

        System.out.println("---- HotelTask ----" + reservationDTO);
        execution.setVariable("reservationDTO", reservationDTO);
    }

}
