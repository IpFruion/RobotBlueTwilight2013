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
public class LinearShooter extends BTShooter implements Constants {
    
    public BTMotor motShoot1;
    public BTMotor motShoot2;
    public Relay pitchMotor;
    public Piston shootPiston;
    
    public LinearShooter()
    {
        motShoot1 = new BTMotor(LINEAR_SHOOTER_MOTOR1_PORT, true);
        motShoot2 = new BTMotor(LINEAR_SHOOTER_MOTOR2_PORT, true);
        pitchMotor = new Relay(SHOOTER_PITCH_RELAY_PORT);
        shootPiston = new Piston(SHOOTER_EXTEND_PORT, SHOOTER_RETRACT_PORT);
    }
    public void update(ControlBoard cb)
    {
        setSpeed(true, cb.getShootMotorSpeed());
        shoot(cb.canShoot());
    }
    public void shoot(boolean canShoot)
    {
        if (canShoot) {            
           shootPiston.setPistonState(true);
           shootPiston.setPistonState(false);
        }
        else {
            try {
                killShot();
            } catch(Exception e) {
                Log.log("Cannot set linear shooter to 0");
            }
        }
    }
    
    public void killShot(){
        motShoot1.setX(0);
        motShoot2.setX(0);
    }
    
    public void setSpeed(boolean set, double speed) {
        if (set) {
            motShoot1.setX(speed);
            motShoot2.setX(speed);
        }
        else {
            killShot();
        }
    }
    public void pitch(boolean hitLimit)
    {
        if(hitLimit)
        {
            pitchMotor.set(Relay.Value.kOff);
        }
        else
        {
            pitchMotor.set(Relay.Value.kForward);
        }
    }
}
