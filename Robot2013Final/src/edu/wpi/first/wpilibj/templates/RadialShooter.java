/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.camera.AxisCamera;



/**
 *
 * @author Sai V
 * 
 */
public class RadialShooter implements Constants, IShooter {
    
    public BTMotor motShoot;
    public BTMotor pitchMotor;
    public BTMotor collectorMotor;
    public DigitalInput lowSensor;
    public DigitalInput highSensor;
    public AxisCamera a;
    public Piston shooter;
    private ShooterInfo shootInfo;
    boolean isVoltage = false;
    
    public RadialShooter(boolean isCan)
    {
        motShoot = new BTMotor(RADIAL_SHOOTER_MOTOR_PORT, isCan, isVoltage);
        //WRONG!! change pitchmotor to isCan as the 2ND param not false
        pitchMotor = new BTMotor(SHOOTER_PITCH_MOTOR_PORT, isCan, isVoltage);
        collectorMotor = new BTMotor(COLLECTOR_MOTOR_PORT, isCan, isVoltage);
        lowSensor = new DigitalInput(SHOOTER_PITCH_LOW_PORT);
        highSensor = new DigitalInput(SHOOTER_PITCH_HIGH_PORT);
        shooter = new Piston(SHOOTER_EXTEND_PORT, SHOOTER_RETRACT_PORT);
    }
    
    public void update(ControlBoard cb) {
        shootInfo = cb.getShooter();
        
        setSpeed(shootInfo.isShooterMotorOff, shootInfo.isShooterMotorOn, shootInfo.shooterMotorSpeed);
        shoot(shootInfo.canShoot);
        pitch(highSensor.get(), lowSensor.get(), shootInfo.pitchMotor);
        reload(shootInfo.reloadMotor);
        
        shootInfo.cycles--;
        cb.setShooter(shootInfo);
    }
    public void reload(double motorspeed)
    {
        collectorMotor.setX(motorspeed);
    }
    
    public void shoot(boolean canShoot) {
        if(canShoot)
        {
            shooter.setPistonState(false);
        }
        else{
            shooter.setPistonState(true);
        }
     }
    
    public void killShot() {
        motShoot.setX(0);
    }
    
    public void setSpeed(boolean setOff, boolean setOn, double speed)
    {
        if (setOn) {
            motShoot.setX(-speed);
        }
        else if (setOff) {
            killShot();
            
        }
    }
    public void pitch(boolean limitHigh, boolean limitLow, double pitchSpeed)
    {
        if(limitHigh)
        {
            pitchMotor.setX(0);
        }
        else 
        {
            pitchMotor.setX(pitchSpeed);
        }
        if(limitLow)
        {
            pitchMotor.setX(0);
        }
        else 
        {
            pitchMotor.setX(pitchSpeed);
        }
    }
    
}
