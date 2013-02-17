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
import edu.wpi.first.wpilibj.DriverStation;

public class BTController implements Constants {

    
    private boolean shifterState = false;
    private boolean lastButtonState = false;
    private boolean motorstate = false;
    Joystick xboxController;
    public BTController()
    {
        
        xboxController = new Joystick(XBOX_CONTROLLER_PORT);
    }
    public double getShooterYaw()
    {
        if (xboxController.getRawButton(LEFT_STICK_BUTTON))
        {
            return PITCH_MOTOR_SPEED;
        }
        else if (xboxController.getRawButton(RIGHT_STICK_BUTTON))
        {
            return -PITCH_MOTOR_SPEED;

        }
        return 0;
    }
    double speed = SHOOT_MOTOR_SPEED_LOW;
    public double getShooterShifter()
    {
        if(xboxController.getRawButton(BACK))
        {
            
            speed = (DriverStation.getInstance().getAnalogIn(1)*20)/100;
            //speed = SHOOT_MOTOR_SPEED_LOW;
        }
        else if (xboxController.getRawButton(START))
        {
            speed = (DriverStation.getInstance().getAnalogIn(2)*20)/100;
            //speed = SHOOT_MOTOR_SPEED_HIGH;
        }
        return speed;
    }
    public boolean getShifterSetting() {
        if(buttonDetector(xboxController.getRawButton(RIGHT_BUMBER)))
        {
            shifterState = !shifterState;
        }
        return shifterState;
    }

    public double getLMotorSpeed() {
        return xboxController.getRawAxis(LEFT_STICK);
    }

    public double getRMotorSpeed() {
        return xboxController.getRawAxis(RIGHT_STICK);
    }

    public boolean canShoot() {
        
        if(xboxController.getRawAxis(TRIGGER_AXIS) == RIGHT_TRIGGER)
        {
            return true;
        }
        return false;
    }
    public boolean canAim(){
        if(xboxController.getRawAxis(TRIGGER_AXIS) == LEFT_TRIGGER)
        {
            return true;
        }
        return false;
    }

    public boolean isShooterMotorOn() {
        return xboxController.getRawButton(Y_BUTTON);
    }
    public boolean isShooterMotorOff()
    {
        return xboxController.getRawButton(X_BUTTON);
    }
    boolean isClimb = false;
    public boolean canClimb()
    {
        if (buttonDetector(xboxController.getRawButton(A_BUTTON)))
        {
            isClimb = !isClimb;
        }
        return isClimb;
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