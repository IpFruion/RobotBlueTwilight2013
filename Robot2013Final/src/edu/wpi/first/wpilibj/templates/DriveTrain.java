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
    double deadband = .1, lowrange = .55, midrange = .6, lowY = .3, highY = .8;
    
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
        if (rightInfo.cycles > 0 && leftInfo.cycles > 0)
        {
            double rightValue = rightInfo.percent;
            double leftValue = leftInfo.percent;
            leftValue = calcPower(leftValue);
            rightValue = calcPower(rightValue);

            left.setX(leftValue);
            left_2.setX(leftValue);
            right.setX(rightValue * -1);
            right_2.setX(rightValue * -1);

            leftInfo.cycles--;
            rightInfo.cycles--;
        }
        
        cb.setDrive(leftInfo, rightInfo);
    }
    public double calcPower(double input)
    {
        double templeft = 0;
        if (Math.abs(input) > deadband)
        {
            templeft = (Math.abs(input) - deadband) / (1-deadband);
            if (templeft <lowrange)
            {
                templeft = lowY;
            }
            else if (templeft < midrange)
            {
                templeft = ((highY-lowY)/(midrange-lowrange))* (templeft - lowrange) + lowrange;
            }
            else
            {
                templeft = ((1-highY)/(1-midrange)) * (templeft - midrange) + midrange;
            }
            if (input < 0)
            {
                templeft *= -1;
            }
        }
        return templeft;
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
