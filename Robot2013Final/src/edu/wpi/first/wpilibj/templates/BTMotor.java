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
    
    private boolean isCANBus;
    private CANJaguar CANMotor;
    private Jaguar PWMMotor;
    
    public BTMotor(int port, boolean isCan)
    {
        if( port<=0 )
        {
            port = 1;
        }
        if (isCan)
        {
            try{
                CANMotor = new CANJaguar(port);
            }
            catch(Exception e){}
        }
        else
        {
            PWMMotor = new Jaguar(port);
        }
        isCANBus = isCan;
        
    }
    public void setX(double x)
    {
        if(isCANBus)
        {
            try{
                CANMotor.setX(x);
            }
            catch(Exception e){}
            
        }
        else
        {
            PWMMotor.set(x);
        }
    }
}
