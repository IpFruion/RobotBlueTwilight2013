/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DigitalInput;

/**
 *
 * @author Alec 
 */
public class ControlBoard extends BTController {
    private DriveInfo left;
    private DriveInfo right;

    DigitalInput shooter_switch;
    DigitalInput climber_switch;
    DigitalInput dt_switch;
    
    public ControlBoard() {
        // 
        shooter_switch = new DigitalInput(1);
        climber_switch = new DigitalInput(2);
        dt_switch = new DigitalInput(3);
    }
    
    public boolean getShooterSetting() {
        if(shooter_switch.get()) {
            return true; // radial shooter
        }
        return false; // linear shooter
    }
    
    public boolean getClimberSetting() {
        if(climber_switch.get()) {
            return true; // high climber
        }
        return false; // low climber
    }
    
    public boolean getDriveTrainSetting() {
        if(dt_switch.get()) {
            return true; // CAN Drive Train
        }
        return false; // PWM Drive Train
    }
    
   
    public double getShootMotorSpeed() {
        //add slider for adjust speed
        return -1.0;
    }
    
    public DriveInfo getDriveLeft(){
        return left;
    }
    
    public DriveInfo getDriveRight(){
        return right;
    }
    public void setDrive(DriveInfo leftTemp, DriveInfo rightTemp){
        left = leftTemp;
        right = rightTemp;
    }
}
