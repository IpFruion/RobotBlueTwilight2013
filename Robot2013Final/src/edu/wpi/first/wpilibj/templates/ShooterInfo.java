/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

/**
 *
 * @author Dlock
 */
public class ShooterInfo {
    
    public boolean isShooterMotorOn;
    public boolean isShooterMotorOff;
    public double shooterMotorSpeed = .5;
    public boolean canShoot;
    public boolean canAim;
    public double pitchMotor;
    public double reloadMotor;
    public boolean reloaded = false;
 
    public int cycles = 0;
}