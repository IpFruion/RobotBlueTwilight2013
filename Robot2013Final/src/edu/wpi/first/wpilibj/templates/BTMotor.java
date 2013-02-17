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
    private boolean successJag = false;
    
    public BTMotor(int port, boolean isCan, boolean isVoltage)
    {
        isCANBus = isCan;
        if (isCANBus) {
            try{
                CANMotor = new CANJaguar(port);
                if (isVoltage)
                {
                    CANMotor.changeControlMode(CANJaguar.ControlMode.kVoltage);
                }
                else
                {
                    CANMotor.changeControlMode(CANJaguar.ControlMode.kPercentVbus);
                }
                successJag = true;
            }
            catch(Exception CANTimeoutException){
                System.out.println("Error initialising CANJaguar at port: " +port);
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
            if (successJag)
            {
                try{
                    CANMotor.setX(x);
                }
                catch(Exception CANTimeoutException){
                    System.out.println("Error setting CANJaguar speed");
                }
            }
        }
        else
        {
            PWMMotor.set(x);
        }
        
    }
}
