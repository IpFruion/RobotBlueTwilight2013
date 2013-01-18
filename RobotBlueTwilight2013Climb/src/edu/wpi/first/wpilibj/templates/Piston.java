/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Solenoid;

/*@author Robotics*/
public class Piston {
    private Solenoid extend, retract;
    public Piston(int extendPort, int retractPort){
        extend = new Solenoid(extendPort);
        retract = new Solenoid(retractPort);
    }
    public void set(boolean state)
    {
        extend.set(state);
        retract.set(!state);
    }
    public boolean get()
    {
        return extend.get();
    }
}
