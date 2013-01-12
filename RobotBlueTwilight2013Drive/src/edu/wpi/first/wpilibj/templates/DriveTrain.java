/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;



/**
 *
 * @author Tim
 */
public class DriveTrain implements Constants
{
    Jaguar left = new Jaguar(LEFT_JAG_PORT);
    Jaguar right = new Jaguar(RIGHT_JAG_PORT);
    
    public void update(DriveController stick)
    {
        double rightValue = stick.getRMotorSpeed();
        double leftValue = stick.getLMotorSpeed();
        left.set(leftValue);
        right.set(rightValue * -1);
    }
}
