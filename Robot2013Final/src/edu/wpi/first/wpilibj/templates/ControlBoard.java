/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

/**
 *
 * @author Alec Pierce
 */
public class ControlBoard extends BTController {
    
    private DriveInfo left;
    private DriveInfo right;
    
    public ControlBoard() {
        
    }
    
    
    
    public double getShootMotorSpeed() {
        //add slider for adjust speed
        return -1.0;
    }
    
    public DriveInfo getDriveLeft(){
        return left;
    }
    
    public DriveInfo getDriveRight(){
        return right;
    }
    public void setDrive(DriveInfo leftTemp, DriveInfo rightTemp){
        left = leftTemp;
        right = rightTemp;
    }
}
