/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanprogram;


import java.io.*;
import java.util.*;


/**
 *
 * @author xchen82
 */
public class New_TopScores implements Comparable{
	String player;
	int score;


public New_TopScores(String player,int score){
	this.player=player;
	this.score=score;
}

public String GetPlayer(){
	return player;
}

public int GetScore(){
	return score;
}


        
public int compareTo(Object user){
    New_TopScores otherUser = (New_TopScores)user;
    if(this.GetScore()==otherUser.GetScore()){
        return 0;
    }

    else if(this.GetScore()>otherUser.GetScore()){
        return -1;
    }

    else{
        return 1;
    }
}



}