/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @version April 20 2013 v1 
 * @author Nathan 
 */
public class BTSmartDashBoard {
    private boolean shootOK = false;
    private Timer time;
    private double rechargeTime = .5;
    private ShooterInfo shoot;
    private ClimberInfo climb;
    private IDrivetrain dts;
    private DriveInfo right;
    private DriveInfo left;
    private IShooter lss;
    
    public BTSmartDashBoard(IDrivetrain dt, IShooter ls)
    {
        time= new Timer();
        dts = dt;
        lss = ls;
        
    }
    
    public void update(ControlBoard cb)
    {
        shoot = cb.getShooter();
        climb = cb.getClimber();
        displayDrive(dts.getInstance().leftInfo, dts.getInstance().rightInfo);
        
        displayShooter(shoot);
        displayClimber(climb);
        //gatherData();
    }
    
    private boolean start = true;
    
    public void gatherData()
    {
        double sliderTest = 0;
        try
        {
            sliderTest = SmartDashboard.getNumber("Slider test");
        }
        catch(Exception e)
        {
            System.out.println("ERROR in dashboard: "+ e);
        }
        if (sliderTest != 0)
            System.out.println("Slider: "+ sliderTest);
    }
    public void displayDrive(DriveInfo left, DriveInfo right)
    {
        SmartDashboard.putNumber("Right Drive Motor Value: ", right.speed);
        SmartDashboard.putNumber("Left Drive Motor Value: ", left.speed);
    }
    //Displaying The shooter outputs
    public void displayShooter(ShooterInfo shoot)
    {
        
        //Turns timer on if shooter motor is on
        if (shoot.isShooterMotorOn && start)
        {
            time.start();
            start = false;
        }
        else if (!shoot.isShooterMotorOn)
        {
            time.stop();
            time.reset();
            start = true;
        }
        
        SmartDashboard.putNumber("Shooter Motor 2 speed", lss.getLinearInst().motShoot1.getBTBusVoltage());
        SmartDashboard.putNumber("Shooter Motor 2 Speed", lss.getLinearInst().motShoot2.getBTBusVoltage());
        
        SmartDashboard.putNumber("Shooter Motor 1 Current", lss.getLinearInst().motShoot1.getBTOutputCurrent());
        SmartDashboard.putNumber("Shooter Motor 2 Current", lss.getLinearInst().motShoot2.getBTOutputCurrent());
        SmartDashboard.putBoolean("Shooter State ", shoot.isShooterMotorOn);
        //Turns the box green after 1/2 second of the last shoot NOT REALLY CONROY!!!!!!!!!
        //Determines wether you can shoot or not
        if (time.get()  > rechargeTime)
        {
            shootOK = true;
        }
        else
        {
            shootOK = false;
        }
        SmartDashboard.putBoolean("OK to Shoot", shootOK);
        if (shoot.canShoot)
        {
            shootOK = false;
            time.stop();
            time.reset();
            time.start();
        }
    }
    
    //Displays the state of the piston.
    public void displayClimber(ClimberInfo climb)
    {
        SmartDashboard.putBoolean("Climber State", climb.canClimb);
    }
}
