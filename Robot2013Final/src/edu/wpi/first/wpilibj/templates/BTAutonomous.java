/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;
/**
 *
 * @author Luke
 */
public class BTAutonomous{
    int currentStep;
    public void update(ControlBoard cb){
    //drive forward for duration 10
    DriveInfo di = new DriveInfo();
    di.direction = 1;
    di.percent = 50;
    di.duration = 10;
    
    currentStep = 1;
    cb.setDrive(di, di);
    }

    public void drive(int duration){

    }
    public void turn(int degrees){
    
    
    }
    public void shoot(){
    
    
    }
    public void isStepDone(){
    
    
    }
    public void stop(){
    
    }
}