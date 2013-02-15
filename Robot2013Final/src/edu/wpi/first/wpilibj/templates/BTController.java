/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;


/**
 *
 * @author Dlock
 */
import edu.wpi.first.wpilibj.Joystick;

public class BTController {

    private boolean shifterState = false;
    private boolean lastButtonState = false;
    private boolean motorstate = false;
    Joystick xboxController;
    public BTController()
    {
        xboxController = new Joystick(1);
    }
    public double getShooterYaw()
    {
        if (xboxController.getRawButton(11))
        {
            return .5;
        }
        else if (xboxController.getRawButton(12))
        {
            return -.5;

        }
        return 0;
    }
    public boolean getShifterSetting() {
        if(buttonDetector(xboxController.getRawButton(6)))
        {
            shifterState = !shifterState;
        }
        return shifterState;
    }

    public double getLMotorSpeed() {
        return xboxController.getRawAxis(2);
    }

    public double getRMotorSpeed() {
        return xboxController.getRawAxis(5);
    }

    public boolean canShoot() {
        
        if(xboxController.getRawAxis(3) == -1)
        {
            return true;
        }
        return false;
    }
    public boolean canAim(){
        if(xboxController.getRawAxis(3) == 1)
        {
            return true;
        }
        return false;
    }

    public boolean isShooterMotorOn() {
        if(xboxController.getRawButton(4))
        {
            motorstate = !motorstate;
        }
        return motorstate;
    }
    public boolean canClimb()
    {
        if (xboxController.getRawButton(1))
        {
            return true;
        }
        return false;
    }
    public boolean buttonDetector(boolean isButton)
    {
        boolean currentState = isButton;
        if (!lastButtonState&&currentState)
        {
            return true;
        }
        lastButtonState = currentState;
        return false;
    }
    
}