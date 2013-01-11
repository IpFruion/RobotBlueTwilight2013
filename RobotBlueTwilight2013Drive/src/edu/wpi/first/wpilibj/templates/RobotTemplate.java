
/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SimpleRobot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
//Alec Pierce and Derrick Lockwood
public class RobotTemplate extends SimpleRobot implements Constants {
    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public Controller leftStick, rightStick;
    public DriveTrain drive;

    
    public void robotInit()
    {
        leftStick = new Controller(LEFT_STICK_PORT, true);
        rightStick = new Controller(RIGHT_STICK_PORT, true);

    }
    public void autonomous() {
        
    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() {
        while(isOperatorControl())
        {
            drive.update(leftStick, rightStick);
        }
    }
}