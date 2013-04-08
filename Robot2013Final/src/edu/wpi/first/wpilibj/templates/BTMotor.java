/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Jaguar;

/**
 *
 * @author Dlock
 */
public class BTMotor {
    
    private boolean isCANBus = false;
    private CANJaguar CANMotor;
    private Jaguar PWMMotor;
    private boolean successJag = false;
    private int portNum;
   
    
    public BTMotor(int port, boolean isCan, boolean isVoltage)
    {
        isCANBus = isCan;
        portNum = port;
        if (isCANBus) {
            try{
                CANMotor = new CANJaguar(port);
                if (isVoltage)
                {
                    setVoltageControlMode();
                }
                successJag = true;
            }
            catch(Exception CANTimeoutException){
                System.out.println("Error initialising CANJaguar at port: " +port);
            }
        }
        else
        {
            PWMMotor = new Jaguar(port);
        }
    }
    public void setX(double x)
    {
        
        if(isCANBus && successJag)
        {
            try{
                CANMotor.setX(x);
            }
            catch(Exception CANTimeoutException){
                System.out.println("Error setting CANJaguar speed at "+portNum);
            }
        }
        else
        {
            PWMMotor.set(x);
        }
       
    }
    public double getBTBusVoltage(){
        if(isCANBus && successJag)
        {
            try{
                return CANMotor.getBusVoltage();
            }
            catch(Exception e){
                System.out.println("Error in getting voltage at "+portNum);
            }
        }
    return 0;
    }
    public double getBTOutputCurrent(){
        if(isCANBus && successJag)
        {            
            try{
                return CANMotor.getOutputCurrent();
        }
            catch(Exception e){
                System.out.println("Error in getting current at "+portNum);
            }
        }
    return 0;
    }
    public double getBTTemperature(){
        if(isCANBus)
        {
            try{
                return CANMotor.getTemperature();
        }
            catch (Exception e){
                System.out.println("Error in getting temperature at "+portNum);
            }    
        }
    return 0;
    }
    private void setVoltageControlMode()
    {
        try {
            CANMotor.changeControlMode(CANJaguar.ControlMode.kVoltage);
            CANMotor.enableControl();
        }
        catch(Exception e){}
    }
}
