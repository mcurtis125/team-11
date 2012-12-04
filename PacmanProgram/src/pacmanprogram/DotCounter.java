/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanprogram;

import java.awt.event.ActionEvent;

/**
 * Used to control when the ghosts can leave their pen. 
 * 
 * @author stavy92
 */
public class DotCounter {    
    public enum Type{personal, global, elroy};
    public enum Status{activated, deactivated, enabled, disabled};
    private Type type;
    private Status status;
    private int counter;
    private int limit;
    
    private Maze maze;
    
    /**
     * Elroy 
     * @param type
     * @param maze 
     */
    public DotCounter(Type type, Maze maze){
        this.type = type;
        this.maze = maze;
        if(type == Type.personal){
            this.status = Status.enabled;
        }
        else if(type == Type.global){
            this.status = Status.deactivated;
        }
        else{
            this.status = Status.enabled;
        }
        counter = 0;
    }
        
    /**
     * Returns the status of the counter.
     * @return Status{activated, deactivated, enabled, disabled}
     */
    public Status getStatus(){
        return status;
    }
      
    /**
     * Returns the current count.
     * @return current dot count
     */
    public int getCount(){
        return counter;
    }
    
    /**
     * Increments the counter.
     */
    public void increaseCounter() {
        counter++;
    }
    
    /**
     * Sets the dot count to 0.
     */
    public void resetCounter(){
        counter = 0;
    }
    
    /**
     * Resets to the status of the counter.
     * -Ghost counters: enabled
     * -Global counter: deactivated
     */
    public void resetStatus(){
        if(type == Type.personal){
            this.status = Status.enabled;
        }
        else if(type == Type.global){
            this.status = Status.deactivated;
        }
        else{
            this.status = Status.enabled;
        }
    }
    
    /**
     * Sets the limit of the dot counter.
     * @param limit number of dots
     */
    public void setLimit(int limit){
        this.limit = limit;
    }

    /**
     * Checks if the dot counter limit is reached. 
     * -Personal: checks if dotsEaten is >= limit
     * -Global: checks if dotsEaten is == limit
     * -Elroy: checks if dotsRemaining <= limit
     * @return true or false
     */
    public boolean checkIfLimitReached() {
        if(type == Type.personal){
            return (counter>=limit);
        }
        else if(type == Type.elroy){
            return ((244-counter)<=limit);
        }
        return (counter==limit);
    }
       
    /**
     * Enables the counter.
     */
    public void enableCounter(){
        status = Status.enabled;
    }
    
    /**
     * Disables the counter.
     */
    public void disableCounter(){
        status = Status.disabled;
    }
    
    /**
     * Activates the counter.
     */
    public void activateCounter(){
        status = Status.activated;
    }
    
    /**
     * Deactivates the counter.
     */
    public void deactivateCounter(){
        status = Status.deactivated;
    }
    

}



