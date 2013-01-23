/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.can.CANTimeoutException;

/**
 *
 * @author Tim
 */
public class DriveTrain implements Constants
{
    CANJaguar left;
    CANJaguar right;
    Piston shift = new Piston(SHIFTER_EXTEND_PORT, SHIFTER_RETRACT_PORT);
    public DriveTrain()
    {
        try
        {
            left = new CANJaguar(LEFT_JAG_PORT);
            right = new CANJaguar(RIGHT_JAG_PORT);
        }
        catch (CANTimeoutException e)
        {}
    }
    public void update(DriveController stick)
    {
        double rightValue = stick.getRMotorSpeed();
        double leftValue = stick.getLMotorSpeed();
        shift.set(stick.getShifterSetting());
        try{
            left.setX(leftValue);
            right.setX(rightValue * -1);
        }
        catch(CANTimeoutException e){}
    }
}
