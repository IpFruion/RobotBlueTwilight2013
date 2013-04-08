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
    public boolean pitchPiston;
    public double reloadMotor;
    public boolean reloaded = false;
    public boolean abortAim;
    public boolean isReverseShoot;
 
    public int cycles = 0;
}