/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

/**
 *
 * @author alec
 */
public class VisionController implements ShooterController, DriveController, Constants {

    public double getShootMotorSpeed() {
        return 1.;
    }

    public double getLMotorSpeed() {
        return 0.;
    }

    public double getRMotorSpeed() {
        return 0.;
    }

    public boolean getShifterSetting() {
        return false;
    }
    
}
