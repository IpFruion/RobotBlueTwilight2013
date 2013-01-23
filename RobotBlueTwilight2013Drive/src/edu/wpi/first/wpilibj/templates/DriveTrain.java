/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.can.CANTimeoutException;



/**
 *
 * @author Tim
 */
public class DriveTrain implements Constants
{
    CANJaguar left;
    CANJaguar right;
    
    public DriveTrain()
    {
        try{
            left = new CANJaguar(LEFT_JAG_PORT);
            right = new CANJaguar(RIGHT_JAG_PORT);
        }
        catch (CANTimeoutException e)
        {
            
        }
    }
    public void update(DriveController stick)
    {
        double rightValue = stick.getRMotorSpeed();
        double leftValue = stick.getLMotorSpeed();
        try{
            left.setX(leftValue);
            right.setX(rightValue * -1);}
        catch (CANTimeoutException e)
        {
            
        }
    }
}
