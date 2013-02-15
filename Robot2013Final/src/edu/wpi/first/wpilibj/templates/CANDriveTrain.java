/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;


/**
 *
 * @author Alec Pierce
 */
public class CANDriveTrain implements Constants, IDrivetrain {
    BTMotor left;
    BTMotor left_2;
    BTMotor right;
    BTMotor right_2;
    Piston shifter;
    DriveInfo rightInfo;
    DriveInfo leftInfo;
    
    public CANDriveTrain()
    {
        
        shifter = new Piston(SHIFTER_EXTEND_PORT,SHIFTER_RETRACT_PORT);
        left = new BTMotor(LEFT_JAG_PORT, true);
        left_2 = new BTMotor(LEFT_JAG_PORT_2, true);
        right = new BTMotor(RIGHT_JAG_PORT, true);
        right_2 = new BTMotor(RIGHT_JAG_PORT_2, true);
    }
    public void update(ControlBoard cb)
    {
        rightInfo = cb.getDriveRight();
        leftInfo = cb.getDriveLeft();
        double rightValue = rightInfo.percent;
        double leftValue = leftInfo.percent;
        boolean isShift = leftInfo.shifterSetting;
        
        left.setX(leftValue);
        left_2.setX(leftValue);
        right.setX(rightValue * -1);
        right_2.setX(rightValue * -1);
        
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
