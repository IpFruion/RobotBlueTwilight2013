/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

/**
 *
 * @author alec
 */
public abstract class BTController implements DriveController, ShooterController, ClimberController, Constants {
    public abstract boolean getShiftButton();
    private boolean lastButtonState = false;
    private boolean shifterState = false;
    public boolean getShifterSetting(){
        boolean currentState = getShiftButton();
        if (!lastButtonState&&currentState)
        {
            shifterState = !shifterState; 
        }
        lastButtonState = currentState;
        return shifterState;
    }
    public double getShootMotorSpeed()
    {
        return 1.;
    }
}
