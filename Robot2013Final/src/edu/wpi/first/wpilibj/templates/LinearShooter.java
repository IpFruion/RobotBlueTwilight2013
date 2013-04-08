/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import com.sun.squawk.debugger.Log;
import edu.wpi.first.wpilibj.*;

/**
 *
 * @author alec
 */
public class LinearShooter implements Constants, IShooter {
    
    public BTMotor motShoot1;
    public BTMotor motShoot2;
    public BTMotor pitchMotor;
    public Piston shootPiston;
    public DigitalInput lowSensor;
    public DigitalInput highSensor;
    private ShooterInfo shootInfo;
    private BTMotor collectorMotor;
    boolean isVoltage = true;
    private int reloadCycles = 0;
    public LinearShooter(boolean isCan)
    {
        motShoot1 = new BTMotor(LINEAR_SHOOTER_MOTOR1_PORT, isCan, isVoltage);
        motShoot2 = new BTMotor(LINEAR_SHOOTER_MOTOR2_PORT, isCan, isVoltage);
        pitchMotor = new BTMotor(SHOOTER_FLIP_MOTOR_PORT, isCan, false);
        shootPiston = new Piston(PITCH_EXTEND_PORT, PITCH_RETRACT_PORT);
        lowSensor = new DigitalInput(SHOOTER_PITCH_LOW_PORT);
        highSensor = new DigitalInput(SHOOTER_PITCH_HIGH_PORT);
        collectorMotor = new BTMotor(RAD_PISTON_MOTOR_PORT, isCan, isVoltage);
    }
    public void update(ControlBoard cb)
    {
        shootInfo = cb.getShooter();
        
        if (shootInfo.cycles > 0)
        {
            setSpeed(shootInfo.isShooterMotorOff, shootInfo.isShooterMotorOn, shootInfo.shooterMotorSpeed);
            shoot(shootInfo.canShoot);
            //pitch(highSensor.get(), lowSensor.get(), shootInfo.pitchPiston);
            reload(shootInfo.reloadMotor);
            
            shootInfo.cycles--;
        }
        cb.setShooter(shootInfo);
    }
    public void reload(double motorspeed)
    {
        if (!shootInfo.reloaded)
        {
            collectorMotor.setX(motorspeed);
            reloadCycles = reloadCycles - reloadCycles;
        }
    }
    public void shoot(boolean canShoot)
    {
        if (canShoot && shootInfo.reloaded) {            
            shootPiston.setPistonState(true);
        }
        else if (!canShoot && shootInfo.reloaded)
        {
            shootPiston.setPistonState(false);
        }
        else if (!shootInfo.reloaded)
        {
            shootPiston.setPistonState(false);
            reloadCycles++;
        }

    }
    
    public void killShot(){
        motShoot1.setX(0);
        motShoot2.setX(0);
    }
    
    public void setSpeed(boolean setOff, boolean setOn, double speed) {
        if (setOn) {
            motShoot1.setX(speed);
            motShoot2.setX(speed);
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

    public RadialShooter getRadialInst() {
        return null;
    }

    public LinearShooter getLinearInst() {
        return this;
    }
}
