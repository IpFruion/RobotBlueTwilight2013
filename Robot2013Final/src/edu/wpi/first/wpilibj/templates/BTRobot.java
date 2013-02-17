
/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;




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
    public BTFactory btf;
    public CompressorInit comp;
    private IDrivetrain drive;
    private IShooter shoot;
    private IClimber climb;
    private BTVision vision;
    private BTAutonomous auto;
    /**
     * This is the Robot starting command
     */
    public void robotInit()
    {
        cb = new ControlBoard();
        btf = new BTFactory();
        drive = btf.createDriveTrain(cb);
        shoot = btf.createShooter(cb);
        climb = btf.createClimber(cb);
        comp = new CompressorInit();
        //vision = new BTVision();
        auto = new BTAutonomous();
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
            cb.update();
            //vision.update(cb);
            drive.update(cb);
            shoot.update(cb);
            climb.update(cb);
        }
        comp.stop();
    }
}
