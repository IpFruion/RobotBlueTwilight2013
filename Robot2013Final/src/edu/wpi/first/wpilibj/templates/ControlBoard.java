/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;
/**
 *
 * @author Alec Pierce
 */ 
public class ControlBoard extends BTController implements DriveInfo{
    
    private DriveInfo left;
    private DriveInfo right;
    
    public ControlBoard()
    {
        
    }
    public double getShootMotorSpeed()
    {
        //add slider for adjust speed
        return .9;
    }
    public DriveInfo getDriveLeft(){
        //get ints for direction, percent, and duration for left 
        return left;
    }
    public DriveInfo getDriveRight(){
        //gets ints for direction, percent, and duration for right
        return right;
    }
    public void setDrive(DriveInfo left, DriveInfo right){
        //sets ints to what we want them to be
    } 
}
