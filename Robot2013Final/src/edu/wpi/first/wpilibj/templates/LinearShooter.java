/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.*;

/**
 *
 * @author alec
 */
public class LinearShooter extends BTShooter implements Constants {
    
    public BTMotor motShoot1;
    public BTMotor motShoot2;
    public Relay pitchMotor;
    public boolean currentState = false;
    public Piston shootPiston;
    
    public void Shooter()
    {
        motShoot1 = new BTMotor(LINEAR_SHOOTER_MOTOR1_PORT, true);
        motShoot2 = new BTMotor(LINEAR_SHOOTER_MOTOR2_PORT, true);
        pitchMotor = new Relay(SHOOTER_PITCH_RELAY_PORT);
        shootPiston = new Piston(SHOOTER_EXTEND_PORT, SHOOTER_RETRACT_PORT);
    }
    public void update(ControlBoard cb)
    {
        shoot(cb);
    }
    public void shoot(ControlBoard cb)
    {
        
        if(cb.canShoot())
        {
            currentState = !currentState;
        }
        if (currentState)
        {            
           motShoot1.setX(cb.getShootMotorSpeed());
           motShoot2.setX(cb.getShootMotorSpeed());
           shootPiston.setPistonState(true);
           shootPiston.setPistonState(false);
        }
        else
        {
            try{
                motShoot1.setX(0);
                motShoot1.setX(0);}
            catch(Exception e)
            {}
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
