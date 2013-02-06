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
public class RadialShooter extends BTShooter implements Constants {
    
    public BTMotor motShoot;
    public Relay pitchMotor;
    public DigitalInput lowSensor;
    public DigitalInput highSensor;
    public AxisCamera a;
    public Piston shooter;
    
    public void RadialShooter()
    {
        motShoot = new BTMotor(SHOOTER_MOTOR_PORT, true);
        pitchMotor = new Relay(SHOOTER_PITCH_RELAY_PORT);
        lowSensor = new DigitalInput(SHOOTER_PITCH_HIGH_PORT);
        highSensor = new DigitalInput(SHOOTER_PITCH_HIGH_PORT);
        shooter = new Piston(SHOOTER_EXTEND_PORT, SHOOTER_RETRACT_PORT);
    }
    public void update(ControlBoard cb)
    {
        setSpeed(cb.isShooterMotorOn(), cb.getShootMotorSpeed());
        shoot(cb.canShoot());
    }
    public void shoot(boolean canShoot)
    {
        shooter.setPistonState(canShoot);
    }
    public void setSpeed(boolean speedset, double speed)
    {
        if (speedset)
        {
            motShoot.setX(speed);
        }
        else
        {
            motShoot.setX(0);
        }
    }
    public boolean pitchSet(int centerY)
    {
        boolean isCenter = false;
        if (centerY < .1 && !lowSensor.get() && !highSensor.get())
        {
            pitchMotor.set(Relay.Value.kForward);
        }
        else if (centerY > 100 && !lowSensor.get() && !highSensor.get())
        {
            pitchMotor.set(Relay.Value.kReverse);
        }
        else
        {
            isCenter = true;
            pitchMotor.set(Relay.Value.kOff);
        }
        return isCenter;
    }
}
