/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

/**
 *
 * @author Dlock
 */
public class BTAutonomous {
    private BTRobot btr;
    private BTMotor shoot1;
    private BTMotor shoot2;
    BTMotor left;
    BTMotor left_2;
    BTMotor right;
    BTMotor right_2;
    private double speed = 0;
    private Piston trigger;
    private BTController xbox;
    private final int SPEED_UP_INCREMENT = 3;
    private final int SHOOT_INCREMENT = 5;
    
    public BTAutonomous(BTRobot btrtemp, IDrivetrain dt, IShooter rs)
    {
        btr = btrtemp;
        shoot1 = rs.getLinearInst().motShoot1;
        shoot2 = rs.getLinearInst().motShoot2;
        xbox = new BTController();
        trigger = rs.getLinearInst().shootPiston;
        left = dt.getInstance().left;
        left_2 = dt.getInstance().left_2;
        right = dt.getInstance().right;
        right_2 = dt.getInstance().right_2;
        
    }
    
    public void update()
    {
        MiddleLocation();
    }
    public void LeftLocation()
    {
        backward();
        sleep(1000);
        stop();
        turnLeft();
        sleep(1000);
        stop();
        
        setMotorSpeed();
        shootMotorOn();
        for(int i = 0; i < SPEED_UP_INCREMENT; i++)
        {
            setMotorSpeed();
            sleep(500);
        }
        
        setMotorSpeed();
        
        for (int i = 0; i < SHOOT_INCREMENT; i++)
        {
            shootDisk();
        }
        shootMotorOff();
    }
    public void MiddleLocation()
    {
        setMotorSpeed();
        shootMotorOn();
        sleep(1000);
        shootMotorOn();
        sleep(1000);
        shootMotorOn();
        sleep(1000);
        for (int i = 0; i < SHOOT_INCREMENT; i++)
        {
            shootMotorOn();
            shootDisk();
        }
        shootMotorOff();
    }
    public void RightLocation()
    {
        
    }
    public void backward()
    {
        if (btr.isEnabled())
        {
            left.setX(-.5);
            left_2.setX(-.5);
            right.setX(-.5);
            right_2.setX(-.5);
        }
    }
    public void turnRight()
    {
        if (btr.isEnabled())
        {
            left.setX(.5);
            left_2.setX(.5);
            right.setX(-.5);
            right_2.setX(-.5);
        }
    }
    public void turnLeft()
    {
        if (btr.isEnabled())
        {
            left.setX(-.5);
            left_2.setX(-.5);
            right.setX(.5);
            right_2.setX(.5);
        }
    }
    public void stop()
    {
        if (btr.isEnabled())
        {
            left.setX(0);
            left_2.setX(0);
            right.setX(0);
            right_2.setX(0);
        }
    }
    public void forward()
    {
        if (btr.isEnabled())
        {
            left.setX(.5);
            left_2.setX(.5);
            right.setX(.5);
            right_2.setX(.5);
        }
    }
    public void shootMotorOn() {
        if (btr.isEnabled())
        {
            for (int i = 0; i<SPEED_UP_INCREMENT; i++)
            {
                shoot1.setX(-speed);
                shoot2.setX(-speed);
            }
        }
    }
    
    public void setMotorSpeed() {
        if (btr.isEnabled())
        {
            for (int i = 0; i<SPEED_UP_INCREMENT; i++)
            {
                speed = xbox.getShooterSetting(true);
            }
        }
    }
    public void shootMotorOff() {
        if (btr.isEnabled())
        {
            shoot1.setX(0);
            shoot2.setX(0);
        }
    }
    public void shootDisk()
    {
        if (btr.isEnabled())
        {
            trigger.setPistonState(true);
            sleep(1000);
            setMotorSpeed();
            sleep(1000);
            setMotorSpeed();
            sleep(1000);
            setMotorSpeed();
            trigger.setPistonState(false);
            sleep(500);
        }
    }
    public void sleep(double time){if(btr.isEnabled()){try{Thread.sleep((long)time);}catch(Exception e){}}}

}
