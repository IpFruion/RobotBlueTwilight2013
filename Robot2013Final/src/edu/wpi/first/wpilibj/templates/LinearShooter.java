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
    private ShooterInfo shootInfo;
    
    public LinearShooter()
    {
        motShoot1 = new BTMotor(LINEAR_SHOOTER_MOTOR1_PORT, true);
        motShoot2 = new BTMotor(LINEAR_SHOOTER_MOTOR2_PORT, true);
        pitchMotor = new BTMotor(SHOOTER_PITCH_MOTOR_PORT, true);
        shootPiston = new Piston(SHOOTER_EXTEND_PORT, SHOOTER_RETRACT_PORT);
    }
    public void update(ControlBoard cb)
    {
        shootInfo = cb.getShooter();
        setSpeed(shootInfo.isShooterMotorOff, shootInfo.isShooterMotorOn, shootInfo.shooterMotorSpeed);
        shoot(shootInfo.canShoot);
        pitch(shootInfo.pitchTopLimit, shootInfo.pitchBottomLimit, shootInfo.pitchMotor);
        shootInfo.cycles--;
        cb.setShooter(shootInfo);
    }
    public void shoot(boolean canShoot)
    {
        if (canShoot) {            
           shootPiston.setPistonState(true);
           shootPiston.setPistonState(false);
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
}
