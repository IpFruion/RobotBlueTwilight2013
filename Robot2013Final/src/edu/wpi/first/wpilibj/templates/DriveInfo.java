/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

/**
 *
 * @author alec
 */
public class DriveInfo {
//These are examples for the int
    //+ or - int
    public int direction = 0;
    //how fast the motors for wheels run
    public double speed = 0;
    //how many cycles this runs
    public int cycles = 0;

    public boolean shifterSetting1;
    public boolean shifterSetting2;
    public String debug()
    {
        return "d = "+direction+" p = "+speed+" c = "+cycles+ " s = ";
    }
    
    
}
