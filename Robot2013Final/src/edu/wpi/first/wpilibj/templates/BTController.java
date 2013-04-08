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
        speed = (DriverStation.getInstance().getAnalogIn(1)*2.4);
    }
    boolean pitchState;
    public boolean getShooterPitch()
    {
        if (xboxController.getRawButton(A_BUTTON))
        {
            pitchState = true;
        }
        else if (xboxController.getRawButton(B_BUTTON))
        {
            pitchState = false;

        }
        return pitchState;
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
    double speed = DriverStation.getInstance().getAnalogIn(1)*2.4;
    boolean inShooterCycle = true;
    public double getShooterSetting(boolean isAutonomous)
    {
        if (!isAutonomous)
        {
            if(inShooterCycle)
            {
                speed = DriverStation.getInstance().getAnalogIn(1)*2.4;
                inShooterCycle = false;
            }
            if(xboxController.getRawButton(BACK))
            {
            
                speed = (DriverStation.getInstance().getAnalogIn(1)*2.4);
                //System.out.println("speed of first analog "+speed);
                //speed = SHOOT_MOTOR_SPEED_LOW;
            }
            else if (xboxController.getRawButton(START))
            {
                speed = (DriverStation.getInstance().getAnalogIn(2)*2.4);
                //System.out.println("speed of second analog "+speed);
                //speed = SHOOT_MOTOR_SPEED_HIGH;
            }
        }
        else
        {
            speed = DriverStation.getInstance().getAnalogIn(1)*2.4;
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
    boolean shooterSwitch = false;
    public boolean isReverseShooter()
    {
        if (xboxController.getRawButton(LEFT_BUMPER))
            shooterSwitch = true;
        if (xboxController.getRawButton(RIGHT_BUMPER))
            shooterSwitch = false;
        return shooterSwitch;
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