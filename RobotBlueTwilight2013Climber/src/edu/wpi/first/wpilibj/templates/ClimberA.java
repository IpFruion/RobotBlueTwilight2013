/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.can.CANTimeoutException;

/**
 *
 * @author alec
 */
public class ClimberA extends Climber{
    
    CANJaguar leftGear;
    CANJaguar leftSpeed;
    CANJaguar rightGear;
    CANJaguar rightSpeed;
    
    public void ClimberA(){
        try{
            leftGear = new CANJaguar(LEFT_GEAR_PORT);
            leftSpeed = new CANJaguar(LEFT_SPEED_PORT);
            rightGear = new CANJaguar(RIGHT_GEAR_PORT);
            rightSpeed = new CANJaguar(RIGHT_SPEED_PORT);
        }
        catch (CANTimeoutException e){}
    }
    public void run(ClimberController cc)
    {
        boolean isActive = false;
        isActive = cc.isClimb();
    }
    
}
