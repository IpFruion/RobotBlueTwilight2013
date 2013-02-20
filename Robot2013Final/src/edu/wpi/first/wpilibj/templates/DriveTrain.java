/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;


/**
 *
 * @author Alec Pierce
 */
public class DriveTrain implements Constants, IDrivetrain {
    BTMotor left;
    BTMotor left_2;
    BTMotor right;
    BTMotor right_2;
    Piston shifter;
    DriveInfo rightInfo;
    DriveInfo leftInfo;
    boolean isVoltage = false;
    
    public DriveTrain(boolean isCan)
    {
        
        shifter = new Piston(DRIVE_SHIFTER_PORT);
        left = new BTMotor(LEFT_JAG_PORT, isCan, isVoltage);
        left_2 = new BTMotor(LEFT_JAG_PORT_2, isCan, isVoltage);
        right = new BTMotor(RIGHT_JAG_PORT, isCan, isVoltage);
        right_2 = new BTMotor(RIGHT_JAG_PORT_2, isCan, isVoltage);
    }
    public void update(ControlBoard cb)
    {
        rightInfo = cb.getDriveRight();
        leftInfo = cb.getDriveLeft();
        
        double rightValue = rightInfo.percent;
        double leftValue = leftInfo.percent;
        
        left.setX(leftValue);
        left_2.setX(leftValue);
        right.setX(rightValue * -1);
        right_2.setX(rightValue * -1);
        
        leftInfo.cycles--;
        rightInfo.cycles--;
        
        cb.setDrive(leftInfo, rightInfo);
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
