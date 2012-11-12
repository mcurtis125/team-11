/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanprogram;

/**
 *
 * @author Kevin
 */
public class TargetTileFinder {
    
    ShortestDistanceFinder distance = new ShortestDistanceFinder();
    
    public TargetTileFinder(){
        
    }
    
        
    
    public double[] getBlinkyChaseTarget(double pacmanX, double pacmanY){
        double[] position = new double[2];
        
        position[0]=pacmanX;
        position[1]=pacmanY;
        
        return position;
        
    }
    
    
    public double[] getPinkyChaseTarget(double pacmanX, double pacmanY, double pacVelX, double pacVelY){
            double x=pacmanX+64;
            double y=pacmanY+64;
            double[] position = new double[2];
            
            if(pacVelX>0){
                x=pacmanX+64;
                y=pacmanY;
            }
            if(pacVelX<0){
                x=pacmanX-64;
                y=pacmanY;
            }
            if(pacVelY>0){
                x=pacmanX;
                y=pacmanY+64;
            }
            if(pacVelY<0){
                x=pacmanX-64;
                y=pacmanY-64;
            }
            
            position[0]=x;
            position[1]=y;
            
            return position;
        }


    public double[] getInkyChaseTarget(double pacmanX, double pacmanY, double pacVelX, double pacVelY, double blinkyX, double blinkyY){
        
        double offsetX;
        double offsetY;
        double xLine;
        double yLine;
        double x=pacmanX+32;
        double y=pacmanY+32;
        double[] position = new double[2];
        
        if(pacVelX>0){
            offsetX=pacmanX+32;
            offsetY=pacmanY;
            xLine=offsetX-blinkyX;
            yLine=offsetY-blinkyY;
            x=offsetX+xLine;
            y=offsetY+yLine;
        }
 
        if(pacVelX<0){
            offsetX=pacmanX-32;
            offsetY=pacmanY;
            xLine=offsetX-blinkyX;
            yLine=offsetY-blinkyY;
            x=offsetX+xLine;
            y=offsetY+yLine;
        }

        if(pacVelY>0){
            offsetX=pacmanX;
            offsetY=pacmanY+32;
            xLine=offsetX-blinkyX;
            yLine=offsetY-blinkyY;
            x=offsetX+xLine;
            y=offsetY+yLine;
        } 
        
        if(pacVelY<0){
            offsetX=pacmanX-32;
            offsetY=pacmanY-32;
            xLine=offsetX-blinkyX;
            yLine=offsetY-blinkyY;
            x=offsetX+xLine;
            y=offsetY+yLine;
        }
        
        if(pacVelY==0&&pacVelX==0){
            xLine=x-blinkyX;
            yLine=y-blinkyY;
            x=x+xLine;
            y=y+yLine;
        }
        
        position[0]=x;
        position[1]=y;
        
        return position;
        
    }
    
    
    
    public double[] getClydeChaseTarget(double pacmanX, double pacmanY, double clydeX, double clydeY){
        
        double[] position = new double[2];
        
        if(distance.calculateDistance(clydeX, clydeY, pacmanX, pacmanY)<128.0){
            position=getClydeScatterTarget();
        }
        
        else{
            position[0]=pacmanX;
            position[1]=pacmanY;
        }
        
        return position;
        
        
    }
    
    
    public double[] getBlinkyScatterTarget(){
        double[] position = new double[2];
        position[0]=400.0;
        position[1]=0.0;
        return position;
    }
    
    
    
    public double[] getPinkyScatterTarget(){
        double[] position = new double[2];
        position[0]=48.0;
        position[1]=0.0;
        return position;
    }
    
   
    public double[] getInkyScatterTarget(){
        double[] position = new double[2];
        position[0]=432.0;
        position[1]=544.0;
        return position;
    }
    
    public double[] getClydeScatterTarget(){
        double[] position = new double[2];
        position[0]=0.0;
        position[1]=544.0;
        return position;
    }
    
}
