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
public class ControlBoard {
    private DriveInfo left;
    private DriveInfo right;
    public ShooterInfo shoot;
    private BTController controller;
    private ClimberInfo climber;
    DigitalInput shooter_switch;
    DigitalInput climber_switch;
    DigitalInput dt_switch;
    DigitalInput diskInTray;
    
    public ControlBoard() {
        shooter_switch = new DigitalInput(Constants.DIGITAL_INPUT_SHOOTER);
        climber_switch = new DigitalInput(Constants.DIGITAL_INPUT_CLIMBER);
        dt_switch = new DigitalInput(Constants.DIGITAL_INPUT_DRIVE_TRAIN);
        diskInTray = new DigitalInput(Constants.DIGITAL_INPUT_SENSE_DISK);
        controller = new BTController();
        shoot = new ShooterInfo();
        left = new DriveInfo();
        right = new DriveInfo();
        climber = new ClimberInfo();
    }
    
    public void update(){
        
        shoot.canAim = controller.canAim();
        shoot.canShoot = controller.canShoot();
        shoot.reloaded = diskInTray.get();
        shoot.isShooterMotorOn = controller.isShooterMotorOn();
        shoot.isShooterMotorOff = controller.isShooterMotorOff();
        shoot.shooterMotorSpeed = controller.getShooterShifter();
        shoot.reloadMotor = controller.getReloadSpeed();
        
        climber.canClimb = controller.canClimb();
        updateCycles();
    }
    
    public void updateCycles()
    {
        if (left.cycles < 1 && right.cycles < 1)
        {
            left.percent = controller.getLMotorSpeed();
            right.percent = controller.getRMotorSpeed();
            left.cycles = 1;
            right.cycles = 1;
        }

        if (shoot.cycles < 1)
        {
            shoot.pitchMotor = controller.getShooterYaw();
            shoot.cycles = 1;
        }
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
    
    public ClimberInfo getClimber(){
        return climber;
    }
    
    public void setDrive(DriveInfo leftTemp, DriveInfo rightTemp){
        left = leftTemp;
        right = rightTemp;
    }
    
    public void setShooter(ShooterInfo shootInfo){
        shoot = shootInfo;
    }
    
    public ShooterInfo getShooter(){
        return shoot;
    }
}
