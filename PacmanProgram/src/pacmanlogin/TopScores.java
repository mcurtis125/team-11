/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanlogin;


import java.io.*;
import java.util.*;


/**
 * Top score of one user.
 * @author xchen82
 */
public class TopScores implements Comparable{
	String player;
	int score;


public TopScores(String player,int score){
	this.player=player;
	this.score=score;
}

/**
 * Returns player's username.
 * @return 
 */
public String GetPlayer(){
	return player;
}

/**
 * Returns player's high score.
 * @return 
 */
public int GetScore(){
	return score;
}


 /**
  * Compares a user's top score to this top score.
  * @param user
  * @return -1 if other user's score is smaller, 1 if other user's score is bigger
  */      
public int compareTo(Object user){
    TopScores otherUser = (TopScores)user;
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