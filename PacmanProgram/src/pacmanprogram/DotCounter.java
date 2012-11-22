/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanprogram;

import java.awt.event.ActionEvent;

/**
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
            this.status = Status.activated;
        }
        counter = 0;
    }
    
    public void increaseCounter() {
        counter++;
    }

    public boolean checkIfLimitReached() {
        if(type == Type.personal){
            return (counter>=limit);
        }
        else if(type == Type.elroy){
            if(getStatus() == DotCounter.Status.activated){
                return ((244-counter)==limit);
            }
        }
        return (counter==limit);
    }
    
    
    
    public void enableCounter(){
        status = Status.enabled;
    }
    
    public void disableCounter(){
        status = Status.disabled;
    }
    
    public void activateCounter(){
        status = Status.activated;
    }
    
    public void deactivateCounter(){
        status = Status.deactivated;
    }
    
    public void resetCounter(){
        counter = 0;
    }
    
    public void resetStatus(){
        if(type == Type.personal){
            this.status = Status.enabled;
        }
        else if(type == Type.global){
            this.status = Status.deactivated;
        }
        else{
            this.status = Status.activated;
        }
    }
    
    public void setLimit(int limit){
        this.limit = limit;
    }
    
    public Status getStatus(){
        return status;
    }
    
    public int getCount(){
        return counter;
    }
}



