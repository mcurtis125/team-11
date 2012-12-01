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
public class New_TopScores {
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

}