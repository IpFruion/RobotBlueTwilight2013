/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;


/**
 *
 * @author Dlock
 */
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;


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
        if (xboxController.getRawButton(A_BUTTON))
        {
            return PITCH_MOTOR_SPEED;
        }
        else if (xboxController.getRawButton(B_BUTTON))
        {
            return -PITCH_MOTOR_SPEED;

        }
        return 0;
    }
    public double getReloadSpeed()
    {
        if (xboxController.getRawButton(LEFT_BUMPER))
        {
            return RELOAD_SPEED;
        }
        else if (xboxController.getRawButton(RIGHT_BUMPER))
        {
            return -RELOAD_SPEED;
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
        if (xboxController.getRawButton(LEFT_STICK_BUTTON))
        {
            isClimb = true;
        }
        if (xboxController.getRawButton(RIGHT_STICK_BUTTON))
        {
            isClimb = false;
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