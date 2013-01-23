/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2013. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;
//hi, alec

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SimpleRobot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate extends SimpleRobot implements Constants{

    public Joystick leftStick, rightStick;
    public DriveTrain drive;


    public void robotInit()
    {
        leftStick = new Joystick(LEFT_STICK_PORT);
        rightStick = new Joystick(RIGHT_STICK_PORT);

    }
    /**
     * This function is called once each time the robot enters autonomous mode.
     */
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
