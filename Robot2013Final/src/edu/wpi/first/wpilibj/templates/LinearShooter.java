/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Relay;

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
    
    public LinearShooter()
    {
        motShoot1 = new BTMotor(LINEAR_SHOOTER_MOTOR1_PORT, false);
        motShoot2 = new BTMotor(LINEAR_SHOOTER_MOTOR2_PORT, false);
        pitchMotor = new Relay(SHOOTER_PITCH_RELAY_PORT);
        shootPiston = new Piston(SHOOTER_EXTEND_PORT, SHOOTER_RETRACT_PORT);
    }
    public void update(ControlBoard cb)
    {
        setSpeed(cb.isShooterMotorOn(), cb.getShootMotorSpeed());
        shoot(cb.canShoot());
    }
    public void setSpeed(boolean speedset, double speed)
    {
        if (speedset)
        {
            motShoot1.setX(speed);
            motShoot2.setX(speed);
        }
        else
        {
            motShoot1.setX(0);
            motShoot2.setX(0);
        }
    }
    public void shoot(boolean canShoot)
    {
            shootPiston.setPistonState(canShoot);
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
