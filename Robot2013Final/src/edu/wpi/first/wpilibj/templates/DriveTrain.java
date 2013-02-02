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
    BTMotor left;
    BTMotor right;
    
    Piston shifter;
    public DriveTrain()
    {
        
        shifter = new Piston(SHIFTER_EXTEND_PORT,SHIFTER_RETRACT_PORT);
        left = new BTMotor(LEFT_JAG_PORT, true);
        right = new BTMotor(RIGHT_JAG_PORT, true);
    }
    public void update(ControlBoard stick)
    {
        double rightValue = stick.getRMotorSpeed();
        double leftValue = stick.getLMotorSpeed();
        boolean isShift = stick.getShifterSetting();
        
        left.setX(leftValue);
        right.setX(rightValue * -1);
        
        shifter.setPistonState(isShift);
        
        
    }
    public boolean yawSet(int centerX)
    {
        boolean isCenter = false;
        if(centerX < -.1)
        {
            
        }
        else if(centerX > .1)
        {
            
        }
        else
        {
            isCenter = true;
        }
        return isCenter;
    }
}
