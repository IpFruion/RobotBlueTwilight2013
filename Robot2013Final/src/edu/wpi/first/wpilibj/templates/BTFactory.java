/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

/**
 *
 * @author Sai V.
 */
public class BTFactory {
    
    public IShooter createShooter(ControlBoard cb) {
        if(cb.getShooterSetting()) {
            System.out.println("radial");
            //check for can in radial shooter
            RadialShooter radialshoot = new RadialShooter(cb.getDriveTrainSetting());
            return radialshoot;
        } else {
            System.out.println("linear");
            LinearShooter linearshoot = new LinearShooter(cb.getDriveTrainSetting());
            return linearshoot;
        }       
    }
    public IClimber createClimber(ControlBoard cb) {
        if(cb.getClimberSetting()) {
            HighClimber high = new HighClimber();
            return high;
        } else {
            LowClimber low = new LowClimber();
            return low;
        }
    }
    public IDrivetrain createDriveTrain(ControlBoard cb) {
        if(cb.getDriveTrainSetting()) {
            System.out.println("can");
            CANDriveTrain can = new CANDriveTrain();
            return can;
        } else {
            System.out.println("pwm");
            PWMDriveTrain pwm = new PWMDriveTrain();
            return pwm;
        }
    }
}
