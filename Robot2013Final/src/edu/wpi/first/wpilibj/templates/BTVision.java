package edu.wpi.first.wpilibj.templates;

import com.sun.squawk.debugger.Log;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.image.*;

/**
 * Sample program to use NIVision to find rectangles in the scene that are illuminated
 * by a LED ring light. The camera sensitivity is set very low so as to only show light
 * sources and remove any distracting parts of the image.
 * 
 * The CriteriaCollection is the set of criteria that is used to filter the set of
 * rectangles that are detected.
 * 
 * The algorithm first does a color threshold operation that only takes objects in the
 * scene that have a bright green color component. Then a convex hull operation fills 
 * all the rectangle outlines (even the partially occluded ones). Then a small object filter
 * removes small particles that might be caused by green reflection scattered from other 
 * parts of the scene. Finally all particles are scored on rectangularity, aspect ratio,
 * and hollowness to determine if they match the target.
 */

/**
 *
 * @author Abby, Tim, Austin
 */
public class BTVision {

    final int XMAXSIZE = 24;
    final int XMINSIZE = 24;
    final int YMAXSIZE = 24;
    final int YMINSIZE = 48;
    final double xMax[] = {1, 1, 1, 1, .5, .5, .5, .5, .5, .5, .5, .5, .5, .5, .5, .5, .5, .5, .5, .5, 1, 1, 1, 1};
    final double xMin[] = {.4, .6, .1, .1, .1, .1, .1, .1, .1, .1, .1, .1, .1, .1, .1, .1, .1, .1, .1, .1, .1, .1, 0.6, 0};
    final double yMax[] = {1, 1, 1, 1, .5, .5, .5, .5, .5, .5, .5, .5, .5, .5, .5, .5, .5, .5, .5, .5, 1, 1, 1, 1};
    final double yMin[] = {.4, .6, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05,
								.05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05,
								.05, .05, .6, 0};
    
    final int RECTANGULARITY_LIMIT = 60;
    final int ASPECT_RATIO_LIMIT = 75;
    final int X_EDGE_LIMIT = 40;
    final int Y_EDGE_LIMIT = 60;
    
    final int X_IMAGE_RES = 320;          //X Image resolution in pixels, should be 160, 320 or 640
    final double VIEW_ANGLE = 48;       //Axis M1011 camera
    
    AxisCamera camera;          // the axis camera object (connected to the switch)
    CriteriaCollection cc;      // the criteria for doing the particle filter operation
    double centerRange;
    int onlyone = 0; // controls image capture while in loop (only want one!)
    
    public class Scores {
        double rectangularity;
        double aspectRatioInner;
        double aspectRatioOuter;
        double xEdge;
        double yEdge;
    }
    
    public class Target {
        boolean isTarget;
        boolean isHigh;
        double centerMassX;
        double centerMassY;
        double distance;
    }
    
    public BTVision() {
        camera = AxisCamera.getInstance();  // get an instance of the camera
        cc = new CriteriaCollection();      // create the criteria for the particle filter
        cc.addCriteria(NIVision.MeasurementType.IMAQ_MT_AREA, 500, 65535, false);
    }

    public void update(ControlBoard cb) {
            try {
                /**
                 * Do the image capture with the camera and apply the algorithm described above. This
                 * sample will either get images from the camera or from an image file stored in the top
                 * level directory in the flash memory on the cRIO. The file name in this case is "testImage.jpg"
                 */
                ColorImage image = camera.getImage();     // comment if using stored images
                BinaryImage thresholdImage = image.thresholdHSV(60, 100, 90, 255, 20, 255);   // keep only red objects
                BinaryImage convexHullImage = thresholdImage.convexHull(false);          // fill in occluded rectangles
                BinaryImage filteredImage = convexHullImage.particleFilter(cc);           // filter out small particles
                
                // let's capture these images so we can see them ...
                if (onlyone == 0) {
                    onlyone++;
                    image.write("/colorimage.bmp");
                    thresholdImage.write("/thresholdimage.bmp");
                    convexHullImage.write("/convexhullimage.bmp");
                    filteredImage.write("/filteredimage.bmp");
                }
                
                //iterate through each particle and score to see if it is a target
                Scores scores[] = new Scores[filteredImage.getNumberParticles()];
                Target target[] = new Target[filteredImage.getNumberParticles()];
                
                for (int i = 0; i < scores.length; i++) {
                    ParticleAnalysisReport report = filteredImage.getParticleAnalysisReport(i);
                    scores[i] = new Scores();
                    scores[i].rectangularity = scoreRectangularity(report);
                    scores[i].aspectRatioOuter = scoreAspectRatio(filteredImage, report, i, true);
                    scores[i].aspectRatioInner = scoreAspectRatio(filteredImage, report, i, false);
                    scores[i].xEdge = scoreXEdge(thresholdImage, report);
                    scores[i].yEdge = scoreYEdge(thresholdImage, report);
                    
                    if(scoreCompare(scores[i], false))
                    {
                        target[i] = new Target();
                        target[i].isTarget = true;
                        target[i].isHigh = true;
                        target[i].centerMassX = report.center_mass_x_normalized;
                        target[i].centerMassY = report.center_mass_y_normalized;
                        target[i].distance = computeDistance(thresholdImage, report, i, false);
                        //target is high goal, sets Target variables
                    } else if (scoreCompare(scores[i], true)) {
                        target[i].isTarget = true;
                        target[i].isHigh = false;
                        target[i].centerMassX = report.center_mass_x_normalized;
                        target[i].centerMassY = report.center_mass_y_normalized;
                        target[i].distance = computeDistance(thresholdImage, report, i, false);
                        //target is middle goal, sets Target variables
                    } else {
                        target[i].isTarget = false;
                        target[i].centerMassX = -10000.;
                        //target is not a target
                    }
			System.out.println("rect: " + scores[i].rectangularity + "ARinner: " + scores[i].aspectRatioInner);
			System.out.println("ARouter: " + scores[i].aspectRatioOuter + "xEdge: " + scores[i].xEdge + "yEdge: " + scores[i].yEdge);	
                }
                
                centerRange = Math.abs(target[0].centerMassX - 160.);
                int bestTarget = 0;
                for (int i = 0; i < target.length; i++) {
                    if(Math.abs(160 - target[i].centerMassX) < centerRange) {
                        centerRange = Math.abs(160 - target[i].centerMassX);
                        bestTarget = i;
                    }
                }
                if (target[bestTarget].centerMassX > 0.) {
                    targetingAdjustments(target[bestTarget]);
                }
                else {
                    Log.log("No targets found");
                }
                /**
                 * all images in Java must be freed after they are used since they are allocated out
                 * of C data structures. Not calling free() will cause the memory to accumulate over
                 * each pass.
                 */
                filteredImage.free();
                convexHullImage.free();
                thresholdImage.free();
                image.free();
            } catch (AxisCameraException ex) {        // this is needed if the camera.getImage() is called
                Log.log("Axis Camera Exception, error targeting");
            } catch (NIVisionException ex) {
                Log.log("NIVision Exception, error targeting");
            }
        }
    
    /**
     * Tells the control board what the necessary adjustments are to hit the optimal target.
     * 
     * @param tg The target you want to hit
     */
    //TODO: add overrides in case adjustments are impractical/impossible
    public void targetingAdjustments(Target tg) {
        double lower = 140, upper = 180; //These should be fixed once we figure out how
        //Calculate upper, lower bounds for x center of mass
        if (tg.centerMassX < lower) {
            //calculate how much to rotate
            //tell controlboard to rotate robot by that much
        }
        else if (tg.centerMassY > upper) {
            //calculate how much to rotate
            //tell controlboard to rotate robot by that much
        }
        
        //calculate distance/speed of motors needed based on tg.centerMassY
        //tell control board to change whatever is necessary
        
        //TODO: Robot.shoot
    }
    
    /**
     * Computes the estimated distance to a target using the height of the particle in the image. For more information and graphics
     * showing the math behind this approach see the Vision Processing section of the ScreenStepsLive documentation.
     * 
     * @param image The image to use for measuring the particle estimated rectangle
     * @param report The Particle Analysis Report for the particle
     * @param outer True if the particle should be treated as an outer target, false to treat it as a center target
     * @return The estimated distance to the target in Inches.
     */
    public double computeDistance (BinaryImage image, ParticleAnalysisReport report, int particleNumber, boolean outer) throws NIVisionException {
            double rectShort, height;
            int targetHeight;

            rectShort = NIVision.MeasureParticle(image.image, particleNumber, false, NIVision.MeasurementType.IMAQ_MT_EQUIVALENT_RECT_SHORT_SIDE);
            //using the smaller of the estimated rectangle short side and the bounding rectangle height results in better performance
            //on skewed rectangles
            height = Math.min(report.boundingRectHeight, rectShort);
            targetHeight = outer ? 29 : 20;

            return X_IMAGE_RES * targetHeight / (height * 12 * 2 * Math.tan(VIEW_ANGLE*Math.PI/(180*2)));
    }
    
    /**
     * Computes a score (0-100) comparing the aspect ratio to the ideal aspect ratio for the target. This method uses
     * the equivalent rectangle sides to determine aspect ratio as it performs better as the target gets skewed by moving
     * to the left or right. The equivalent rectangle is the rectangle with sides x and y where particle area= x*y
     * and particle perimeter= 2x+2y
     * 
     * @param image The image containing the particle to score, needed to performa additional measurements
     * @param report The Particle Analysis Report for the particle, used for the width, height, and particle number
     * @param outer	Indicates whether the particle aspect ratio should be compared to the ratio for the inner target or the outer
     * @return The aspect ratio score (0-100)
     */
    public double scoreAspectRatio(BinaryImage image, ParticleAnalysisReport report, int particleNumber, boolean outer) throws NIVisionException
    {  
        double rectLong, rectShort, aspectRatio, idealAspectRatio;

        rectLong = NIVision.MeasureParticle(image.image, particleNumber, false, NIVision.MeasurementType.IMAQ_MT_EQUIVALENT_RECT_LONG_SIDE);
        rectShort = NIVision.MeasureParticle(image.image, particleNumber, false, NIVision.MeasurementType.IMAQ_MT_EQUIVALENT_RECT_SHORT_SIDE);
        
        idealAspectRatio = outer ? (62/29) : (62/20);	//Dimensions of goal opening + 4 inches on all 4 sides for reflective tape
	
        System.out.println("rectLong:  " + rectLong );
        System.out.println("rectShort:  " + rectShort);
        
        //Divide width by height to measure aspect ratio
        if(report.boundingRectWidth > report.boundingRectHeight){
            //particle is wider than it is tall, divide long by short
            aspectRatio = 100*(1-Math.abs((1-((rectLong/rectShort)/idealAspectRatio))));
        } else {
            //particle is taller than it is wide, divide short by long
                aspectRatio = 100*(1-Math.abs((1-((rectShort/rectLong)/idealAspectRatio))));
        }
	return (Math.max(0, Math.min(aspectRatio, 100.0))); //force to be in range 0-100
    }

    /**
     * Compares scores to defined limits and returns true if the particle appears to be a target
     * 
     * @param scores The structure containing the scores to compare
     * @param outer True if the particle should be treated as an outer target, false to treat it as a center target
     * 
     * @return True if the particle meets all limits, false otherwise
     */
    boolean scoreCompare(Scores scores, boolean outer){
            boolean isTarget = true;

            isTarget &= scores.rectangularity > RECTANGULARITY_LIMIT;
            if(outer){
                    isTarget &= scores.aspectRatioOuter > ASPECT_RATIO_LIMIT;
            } else {
                    isTarget &= scores.aspectRatioInner > ASPECT_RATIO_LIMIT;
            }
            isTarget &= scores.xEdge > X_EDGE_LIMIT;
            isTarget &= scores.yEdge > Y_EDGE_LIMIT;

            return isTarget;
    }
    
    /**
     * Computes a score (0-100) estimating how rectangular the particle is by comparing the area of the particle
     * to the area of the bounding box surrounding it. A perfect rectangle would cover the entire bounding box.
     * 
     * @param report The Particle Analysis Report for the particle to score
     * @return The rectangularity score (0-100)
     */
    double scoreRectangularity(ParticleAnalysisReport report){
            if(report.boundingRectWidth*report.boundingRectHeight !=0){
                    return 100*report.particleArea/(report.boundingRectWidth*report.boundingRectHeight);
            } else {
                    return 0;
            }	
    }
    
    /**
     * Computes a score based on the match between a template profile and the particle profile in the X direction. This method uses the
     * the column averages and the profile defined at the top of the sample to look for the solid vertical edges with
     * a hollow center.
     * 
     * @param image The image to use, should be the image before the convex hull is performed
     * @param report The Particle Analysis Report for the particle
     * 
     * @return The X Edge Score (0-100)
     */
    public double scoreXEdge(BinaryImage image, ParticleAnalysisReport report) throws NIVisionException
    {
        double total = 0;
        LinearAverages averages;
        
        NIVision.Rect rect = new NIVision.Rect(report.boundingRectTop, report.boundingRectLeft, report.boundingRectHeight, report.boundingRectWidth);
        averages = NIVision.getLinearAverages(image.image, LinearAverages.LinearAveragesMode.IMAQ_COLUMN_AVERAGES, rect);
        float columnAverages[] = averages.getColumnAverages();
        for(int i=0; i < (columnAverages.length); i++){
                if(xMin[(i*(XMINSIZE-1)/columnAverages.length)] < columnAverages[i] 
                   && columnAverages[i] < xMax[i*(XMAXSIZE-1)/columnAverages.length]){
                        total++;
                }
        }
        total = 100*total/(columnAverages.length);
        return total;
    }
    
    /**
	 * Computes a score based on the match between a template profile and the particle profile in the Y direction. This method uses the
	 * the row averages and the profile defined at the top of the sample to look for the solid horizontal edges with
	 * a hollow center
	 * 
	 * @param image The image to use, should be the image before the convex hull is performed
	 * @param report The Particle Analysis Report for the particle
	 * 
	 * @return The Y Edge score (0-100)
    */
    public double scoreYEdge(BinaryImage image, ParticleAnalysisReport report) throws NIVisionException
    {
        double total = 0;
        LinearAverages averages;
        
        NIVision.Rect rect = new NIVision.Rect(report.boundingRectTop, report.boundingRectLeft, report.boundingRectHeight, report.boundingRectWidth);
        averages = NIVision.getLinearAverages(image.image, LinearAverages.LinearAveragesMode.IMAQ_ROW_AVERAGES, rect);
        float rowAverages[] = averages.getRowAverages();
        for(int i=0; i < (rowAverages.length); i++){
                if(yMin[(i*(YMINSIZE-1)/rowAverages.length)] < rowAverages[i] 
                   && rowAverages[i] < yMax[i*(YMAXSIZE-1)/rowAverages.length]){
                        total++;
                }
        }
        total = 100*total/(rowAverages.length);
        return total;
    }
}