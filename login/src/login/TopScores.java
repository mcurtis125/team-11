/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package login;


import java.io.*;
import java.util.*;


/**
 *
 * @author xchen82
 */
public class TopScores {
	String player;
	int score;


public TopScores(String player,int score){
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