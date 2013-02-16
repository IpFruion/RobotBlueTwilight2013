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
    public DigitalInput lowSensor;
    public DigitalInput highSensor;
    public AxisCamera a;
    public Piston shooter;
    private ShooterInfo shootInfo;
    
    public RadialShooter()
    {
        motShoot = new BTMotor(RADIAL_SHOOTER_MOTOR_PORT, true);
        pitchMotor = new BTMotor(SHOOTER_PITCH_MOTOR_PORT, true);
        lowSensor = new DigitalInput(SHOOTER_PITCH_LOW_PORT);
        highSensor = new DigitalInput(SHOOTER_PITCH_HIGH_PORT);
        shooter = new Piston(SHOOTER_EXTEND_PORT, SHOOTER_RETRACT_PORT);
    }
    
    public void update(ControlBoard cb) {
        shootInfo = cb.getShooter();
        
        setSpeed(shootInfo.isShooterMotorOff, shootInfo.isShooterMotorOn, shootInfo.shooterMotorSpeed);
        shoot(shootInfo.canShoot);
        pitch(highSensor.get(), lowSensor.get(), shootInfo.pitchMotor);
        
        shootInfo.cycles--;
        cb.setShooter(shootInfo);
    }
    
    public void shoot(boolean canShoot) {
        shooter.setPistonState(canShoot);
     }
    
    public void killShot() {
        motShoot.setX(0);
    }
    
    public void setSpeed(boolean setOff, boolean setOn, double speed)
    {
        if (setOn) {
            motShoot.setX(speed);
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
/**    public boolean pitchSet(int centerY)    //TODO: Fix and implement this
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
*/
    
}
