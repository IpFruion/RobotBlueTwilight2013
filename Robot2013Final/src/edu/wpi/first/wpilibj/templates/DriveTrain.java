/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Encoder;
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
    Encoder shiftSpeed;
    boolean isVoltage = true;
    double deadband = .1, lowrange = .3, lowY = .3, highY = .8, slope = 1.27, y_intercept = .27;
    double distance = 1./(1943/18.84);
    private double ramprate = 50;
    public DriveTrain(boolean isCan)
    {
        
        shifter = new Piston(SHIFTER_EXTEND_PORT, SHIFTER_RETRACT_PORT);
        left = new BTMotor(LEFT_JAG_PORT, isCan, isVoltage);
        left_2 = new BTMotor(LEFT_JAG_PORT_2, isCan, isVoltage);
        right = new BTMotor(RIGHT_JAG_PORT, isCan, isVoltage);
        right_2 = new BTMotor(RIGHT_JAG_PORT_2, isCan, isVoltage);
        shiftSpeed = new Encoder(DRIVE_ENCODER_A_PORT, DRIVE_ENCODER_B_PORT, true, CounterBase.EncodingType.k1X);
        shiftSpeed.setDistancePerPulse(distance);
        shiftSpeed.start();
        shiftSpeed.reset();
        
        left.setBTVoltageRampRate(ramprate);
        left_2.setBTVoltageRampRate(ramprate);
        right.setBTVoltageRampRate(ramprate);
        right_2.setBTVoltageRampRate(ramprate);
    }
    int cycle = 0;
    public void update(ControlBoard cb)
    {

        rightInfo = cb.getDriveRight();
        leftInfo = cb.getDriveLeft();
        if (rightInfo.cycles > 0 && leftInfo.cycles > 0)
        {
            double rightValue = rightInfo.speed;
            double leftValue = leftInfo.speed;
            leftValue = calcPower(leftValue)*12.;
            rightValue = calcPower(rightValue)*12.;
            
            //checkAmp(leftValue,rightValue);
            
            left.setX(leftValue);
            left_2.setX(leftValue);
            right.setX(-rightValue);
            right_2.setX(-rightValue);
            //shifter.setPistonState(autoShift());
            getShifting(rightInfo.shifterSetting1, rightInfo.shifterSetting2);
            
            leftInfo.cycles--;
            rightInfo.cycles--;
        }
        
        
   
        /*if (rightInfo.shifterSetting1)
        {
            shiftSpeed.reset();
        }
        * 
        */
        
        cb.setDrive(leftInfo, rightInfo);
    }
    public boolean shiftState = false;
    public void getShifting(boolean rightbump, boolean leftbump)
    {
        if (rightbump)
            shiftState = true;
        else if (leftbump)
            shiftState = false;
        
        if (shiftState)
           shifter.setPistonState(autoShift());
        else
           shifter.setPistonState(false);
    }
    private double leftPrev, rightPrev;
    public void checkAmp(double leftv, double rightv)
    {
        if ((leftv > 0 && leftPrev < leftv) || (leftv < 0 && leftPrev > leftv))
            sleep(50);
        leftPrev = leftv;
        rightPrev = rightv;
    }
    public void sleep(double time){try{Thread.sleep((long)time);}catch(Exception e){}}
    
    private boolean shiftSetting;
    public boolean autoShift()
    {
        
        double speed = shiftSpeed.getRate();
        
        if (speed > 60 || speed < -60)
            shiftSetting = false;
        else if (speed < 50 && speed> -50)
            shiftSetting = true;
        return shiftSetting;
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
