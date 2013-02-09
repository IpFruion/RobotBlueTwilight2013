/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Jaguar;

/**
 *
 * @author Dlock
 */
public class BTMotor {
    
    private boolean isCANBus = false;
    private CANJaguar CANMotor;
    private Jaguar PWMMotor;
    
    public BTMotor(int port, boolean isCan)
    {
        isCANBus = isCan;
        if (isCANBus) {
            try{
                CANMotor = new CANJaguar(port);
            }
            catch(Exception CANTimeoutException){
                System.out.println("Error initialising CANJaguar for Shooter");
            }
        }
        else
        {
            PWMMotor = new Jaguar(port);
        }
    }
    public void setX(double x)
    {
        if(isCANBus)
        {
            try{
                CANMotor.setX(x);
            }
            catch(Exception CANTimeoutException){
                System.out.println("Error setting CANJaguar speed");
            }
        }
        else
        {
            PWMMotor.set(x);
        }
    }
}
