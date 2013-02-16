/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

/**
 *
 * @author Sai V.
 */
public class PWMDriveTrain implements Constants, IDrivetrain {
     
    BTMotor left;
    BTMotor right;
    Piston shifter;
    DriveInfo rightInfo;
    DriveInfo leftInfo;
    
    public PWMDriveTrain()
    {
        
        shifter = new Piston(DRIVE_SHIFTER_PORT);
        left = new BTMotor(LEFT_JAG_PORT, false);
        right = new BTMotor(RIGHT_JAG_PORT, false);
        
    }
    public void update(ControlBoard cb)
    {
        rightInfo = cb.getDriveRight();
        leftInfo = cb.getDriveLeft();
        double rightValue = rightInfo.percent;
        double leftValue = leftInfo.percent;
        boolean isShift = leftInfo.shifterSetting;
        
        left.setX(leftValue);
        right.setX(rightValue * -1);
        
        shifter.setPistonState(isShift);
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
