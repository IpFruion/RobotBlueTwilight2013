/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Compressor;

/**
 *
 * @author Dlock
 */

public class CompressorInit implements Constants {
    
    Compressor comp;
    public CompressorInit()
    {
        comp = new Compressor(COMP_SENSOR_PORT, COMP_RELAY_PORT);
        
    }
    public void run()
    {
        comp.start();
    }
    public void stop()
    {
        comp.stop();
    }
}
