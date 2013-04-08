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
    private boolean shooterSettingV2 = true, climberSettingV2 = false, driveTrainSettingV2 = true;
    
    private int robotVersion = 2;
    public IShooter createShooter(ControlBoard cb) {
        //add cb.getShooterSetting()
        if(shooterSettingV2) {
            //check for can in radial shooter
            //System.out.println("radial");
            RadialShooter radialshoot = new RadialShooter(cb.getDriveTrainSetting());
            return radialshoot;
        } else {
            //System.out.println("linear");
            LinearShooter linearshoot = new LinearShooter(cb.getDriveTrainSetting());
            return linearshoot;
        }       
    }
    public IClimber createClimber(ControlBoard cb) {
        //add cb.getClimberSetting()
        if(climberSettingV2) {
            HighClimber high = new HighClimber();
            return high;
        } else {
            LowClimber low = new LowClimber();
            return low;
        }
    }
    public IDrivetrain createDriveTrain(ControlBoard cb) {
        //add cb.getDriveTrainSetting()
        if(driveTrainSettingV2) {
            //System.out.println("can");
            DriveTrain can = new DriveTrain(true);
            return can;
        } else {
           //System.out.println("pwm");
            DriveTrain pwm = new DriveTrain(false);
            return pwm;
        }
    }
}
