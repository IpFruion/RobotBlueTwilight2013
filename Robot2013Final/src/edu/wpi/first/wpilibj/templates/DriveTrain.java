/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;


/**
 *
 * @author Alec Pierce
 */
public class DriveTrain implements Constants
{
    BTMotor left;
    BTMotor left_2;
    BTMotor right;
    BTMotor right_2;
    
    Piston shifter;
    public DriveTrain()
    {
        
        shifter = new Piston(SHIFTER_EXTEND_PORT,SHIFTER_RETRACT_PORT);
        left = new BTMotor(LEFT_JAG_PORT, false);
        //left_2 = new BTMotor(LEFT_JAG_PORT_2, true);
        right = new BTMotor(RIGHT_JAG_PORT, false);
        //right_2 = new BTMotor(RIGHT_JAG_PORT_2, true);
    }
    public void update(ControlBoard stick)
    {
        double rightValue = stick.getRMotorSpeed();
        double leftValue = stick.getLMotorSpeed();
        boolean isShift = stick.getShifterSetting();
        
        left.setX(leftValue);
        //left_2.setX(leftValue);
        right.setX(rightValue * -1);
        //right_2.setX(rightValue * -1);
        
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
