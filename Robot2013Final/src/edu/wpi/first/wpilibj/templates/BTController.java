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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


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
    boolean shieldState;
    public boolean getShooterShield()
    {
        if (xboxController.getRawButton(A_BUTTON))
        {
            shieldState = true;
        }
        else if (xboxController.getRawButton(B_BUTTON))
        {
            shieldState = false;

        }
        return shieldState;
    }
    /*public boolean getShifterSetting()
    {
        if (xboxController.getRawButton(LEFT_BUMPER))
        {
            shifterState = false;
        }
        else if (xboxController.getRawButton(RIGHT_BUMPER))
        {
            shifterState = true;
        }
        return shifterState;
    }
    * 
    */
    public boolean getShifterSetting1()
    {
        if (xboxController.getRawButton(RIGHT_BUMPER))
        {
            return true;
        }
        else
        {
            return false;
        }
              
    }
    public boolean getShifterSetting2()
    {
        if(xboxController.getRawButton(LEFT_BUMPER))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    private final double speedConst = 2.4;
    double speed = DriverStation.getInstance().getAnalogIn(1)*speedConst;
    //double speed = SmartDashboard.getNumber("Shooter Speed", 12.0);
    boolean inShooterCycle = true;
    public double getShooterSetting(boolean isAutonomous)
    {
        if (!isAutonomous)
        {
            if(inShooterCycle)
            {
                speed = DriverStation.getInstance().getAnalogIn(1)*speedConst;
                //speed = SmartDashboard.getNumber("Shooter Speed", 12.0);
                inShooterCycle = false;
            }
            if(xboxController.getRawButton(BACK))
            {
            
                //speed = SmartDashboard.getNumber("Shooter Speed", 12.0);
                speed = (DriverStation.getInstance().getAnalogIn(1)*speedConst);
                //System.out.println("speed of first analog "+speed);
                //speed = SHOOT_MOTOR_SPEED_LOW;
            }
            else if (xboxController.getRawButton(START))
            {
                //speed = SmartDashboard.getNumber("Shooter Speed", 12.0);
                speed = (DriverStation.getInstance().getAnalogIn(2)*speedConst);
                //System.out.println("speed of second analog "+speed);
                //speed = SHOOT_MOTOR_SPEED_HIGH;
            }
        }
        else
        {
            //speed = SmartDashboard.getNumber("Shooter Speed", 12.0);
            speed = DriverStation.getInstance().getAnalogIn(1)*speedConst;
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
    private boolean shooterstate = false;
    public boolean isShooterMotorValue() {
        if (xboxController.getRawButton(Y_BUTTON))
            shooterstate = true;
        if (xboxController.getRawButton(X_BUTTON))
            shooterstate = false;
        return shooterstate;
    }
    public boolean isShooterMotorOn() {
        if (xboxController.getRawButton(Y_BUTTON))
          shooterstate = xboxController.getRawButton(Y_BUTTON);
        return shooterstate;
    }
    public boolean isShooterMotorOff()
    {
        if (xboxController.getRawButton(X_BUTTON))
          shooterstate = xboxController.getRawButton(X_BUTTON);
        return shooterstate;
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