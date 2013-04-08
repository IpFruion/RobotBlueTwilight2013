/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.camera.AxisCamera;



/**
 *
 * @author Sai V
 * 
 */
public class RadialShooter implements Constants, IShooter {
    
    public BTMotor motShoot1;
    public BTMotor motShoot2;
    //public BTMotor flipMotor;
    public BTMotor shooter;
    //public DigitalInput lowSensor;
    //public DigitalInput highSensor;
    public AxisCamera a;
    public Piston pitch;
    private ShooterInfo shootInfo;
    boolean isVoltage = false;
    
    public RadialShooter(boolean isCan)
    {
        motShoot1 = new BTMotor(RADIAL_SHOOTER_MOTOR1_PORT, isCan, true);
        motShoot2 = new BTMotor(RADIAL_SHOOTER_MOTOR2_PORT, isCan, true);
        //WRONG!! change pitchmotor to isCan as the 2ND param not false
        //flipMotor = new BTMotor(SHOOTER_FLIP_MOTOR_PORT, isCan, isVoltage);
        shooter = new BTMotor(RAD_PISTON_MOTOR_PORT, isCan, true);
        //lowSensor = new DigitalInput(SHOOTER_PITCH_LOW_PORT);
        //highSensor = new DigitalInput(SHOOTER_PITCH_HIGH_PORT);
        pitch = new Piston(PITCH_EXTEND_PORT, PITCH_RETRACT_PORT, RADIAL_PITCH_PRESSURE_READER);
    }
    public void update(ControlBoard cb) {
        shootInfo = cb.getShooter();
        
        if (shootInfo.cycles > 0)
        {
            setSpeed(shootInfo.isShooterMotorOff, shootInfo.isShooterMotorOn, shootInfo.shooterMotorSpeed);
            shoot(shootInfo.canShoot);
            pitchSwitch(shootInfo.pitchPiston);
            
            shootInfo.cycles--;
        }
        cb.setShooter(shootInfo);
    }
    
    /*public void shoot(boolean canShoot) {
        if (shootInfo.reloaded)
        {
            if(canShoot)
            {
                pitch.setPistonState(false);
            }
            else
            {
                pitch.setPistonState(true);
            }
        }
        else
        {
            pitch.setPistonState(true);
        }
     }
    */
    public void shoot(boolean canShoot) {
        if (shootInfo.reloaded)
        {
            if(canShoot)
            {
                shooter.setX(6);
            }
            else
            {
                shooter.setX(0);
            }
        }
        else
        {
            shooter.setX(0);
        }
     }
    public void killShot() {
        motShoot1.setX(0);
        motShoot2.setX(0);
    }
    
    public void setSpeed(boolean setOff, boolean setOn, double speed)
    {
        if (setOn) {
            motShoot1.setX(-speed);
            motShoot2.setX(-speed);
        }
        
        else if (setOff) {
            killShot();
            
        }
    }
    public void pitchSwitch(boolean pitchSetting)
    {
        
    }

    public RadialShooter getRadialInst() {
        return this;
    }

    public LinearShooter getLinearInst() {
        return null;
    }
    
}
