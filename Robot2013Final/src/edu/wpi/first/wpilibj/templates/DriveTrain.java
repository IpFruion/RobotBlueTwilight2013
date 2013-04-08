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
    double deadband = .1, lowrange = .3, lowY = .3, highY = .8, slope = 1.27, y_intercept = .27;
    
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
            right.setX(-rightValue);
            right_2.setX(-rightValue);

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
            templeft = Math.abs(input);
            if (templeft <lowrange)
            {
                templeft = lowY;
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

    public DriveTrain getInstance() {
        return this;
    }
}
