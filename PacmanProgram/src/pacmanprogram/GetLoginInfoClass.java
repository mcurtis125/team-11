/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanprogram;

import java.util.Arrays;

/**
 *
 * @author xchen82
 * Justin provided ideas on sorting
 */
public class GetLoginInfoClass {
    
    String username;
    String password;
    String displayname;
    int[] score=new int[10];
    

public GetLoginInfoClass(String usernamex,String passwordx,String displaynamex
		, int[] Scores
        ) {
    this.username=usernamex;
    this.password=passwordx;
    this.displayname=displaynamex;
    
    
    //sorting
    int[] UserScore= Scores;
	Arrays.sort(UserScore);
	
	this.score[0]=UserScore[9];
	this.score[1]=UserScore[8];
	this.score[2]=UserScore[7];
	this.score[3]=UserScore[6];
	this.score[4]=UserScore[5];
	this.score[5]=UserScore[4];
	this.score[6]=UserScore[3];
	this.score[7]=UserScore[2];
	this.score[8]=UserScore[1];
	this.score[9]=UserScore[0];
    }

public void AddNewScore(int NewScore){
	int[] CurrentScore={this.score[0],this.score[1],this.score[2],this.score[3],this.score[4],this.score[5],this.score[6],this.score[7],this.score[8],this.score[9]};
	for (int i=0;i<10;i++){
		if (NewScore>CurrentScore[i]){
			
			for (int ii=9;ii>i;ii--){
			CurrentScore[ii]=CurrentScore[ii-1];
			}
			CurrentScore[i]=NewScore;
			
			this.score[0]=CurrentScore[9];
			this.score[1]=CurrentScore[8];
			this.score[2]=CurrentScore[7];
			this.score[3]=CurrentScore[6];
			this.score[4]=CurrentScore[5];
			this.score[5]=CurrentScore[4];
			this.score[6]=CurrentScore[3];
			this.score[7]=CurrentScore[2];
			this.score[8]=CurrentScore[1];
			this.score[9]=CurrentScore[0];
			
			break;
		}
	}
}

public void ChangePassWord(String NewPassWord){
	this.password=NewPassWord;
}

public void ChangeDisplayName(String NewDisplayName){
	this.displayname=NewDisplayName;
}


public String GetUserName(){return username;}
public String GetPassWord(){return password;}
public String GetDisplayName(){return displayname;}

public int GetScore(int i){return score[i];}

public String GetScoreS(int i){return Integer.toString(score[i]);}
      
        
}