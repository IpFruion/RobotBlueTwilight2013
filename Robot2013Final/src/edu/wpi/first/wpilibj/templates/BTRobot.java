
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
public class BTRobot extends SimpleRobot {
    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public ControlBoard cb;
    public DriveTrain drive;
    public CompressorInit comp;
    public RadialShooter shoot;
    public HighClimber hc;
    public LowClimber lc;
    public BTAutonomous auto;

    public void robotInit()
    {
        cb = new ControlBoard();
        drive = new DriveTrain();
        comp = new CompressorInit();
        hc = new HighClimber();
        lc = new LowClimber();
        auto = new BTAutonomous();
        shoot = new RadialShooter();
    }
    public void autonomous() { 
    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() {
        comp.run();
        while(isOperatorControl())
        {
            drive.update(cb);
            if (cb.xboxController.getRawButton(1))
            {
                shoot.update(cb);
            }
        }
        comp.stop();
    }
}