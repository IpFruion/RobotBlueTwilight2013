/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import com.sun.squawk.debugger.Log;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author Dlock
 */
public class BTMotor {
    
    private boolean isCANBus = true;
    private CANJaguar CANMotor;
    private Jaguar PWMMotor;
    
    public BTMotor(int port, boolean isCan)
    {
        isCANBus = isCan;
        if (isCANBus)
        {
            //int maxTries = 0;
            //while(CANMotor == null && maxTries < 10)
            //{
                try{
                    CANMotor = new CANJaguar(port);
                }
                catch(Exception CANTimeoutException){
                    Log.log("Error initialising CANJaguar");
                }
            //}
            //if (maxTries >= 10)
                //Log.log("CANJaguar(" + port + ") failed to initialize.");
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
                Log.log("Error setting CANJaguar speed");
            }
        }
        else
        {
            PWMMotor.set(x);
        }
    }
}
