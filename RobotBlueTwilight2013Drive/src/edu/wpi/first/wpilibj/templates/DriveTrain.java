/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Jaguar;

/**
 *
 * @author Tim
 */
public class DriveTrain implements Constants
{
    Jaguar left = new Jaguar(LEFT_JAG_PORT);
    Jaguar right = new Jaguar(RIGHT_JAG_PORT);
    Piston shift = new Piston(SHIFTER_EXTEND_PORT, SHIFTER_RETRACT_PORT);
    public void update(DriveController stick)
    {
        double rightValue = stick.getRMotorSpeed();
        double leftValue = stick.getLMotorSpeed();
        shift.set(stick.getShifterSetting());
        left.set(leftValue);
        right.set(rightValue * -1);
    }
}
