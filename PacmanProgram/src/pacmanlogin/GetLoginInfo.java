/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanlogin;

import java.util.Arrays;

/**
 * All the information of one user.
 * @author xchen82
 * @author ChanWoo
 */
public class GetLoginInfo{

	String username;
	String password;
	String displayname;
	String securityquestion;
	String securityanswer;
	int maxlevel;
	int[] score=new int[10];


	public GetLoginInfo(String usernamex,String passwordx,String displaynamex
			, int[] Scores ,String securityquestionx,String securityanswerx,int maxlevelx

			) {
		this.username=usernamex;
		this.password=passwordx;
		this.displayname=displaynamex;
		this.securityquestion=securityquestionx;
		this.securityanswer=securityanswerx;
		this.maxlevel=maxlevelx;


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

        /**
         * Adds a new score in a user's personal top 10 if it's high enough.
         * @param NewScore 
         */
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

        /**
         * Changes the user's max level reached.
         * @param NewMaxLevel 
         */
	public void AddNewMaxLevel(int NewMaxLevel){
		this.maxlevel=NewMaxLevel;
	}

        /**
         * Changes the user's password.
         * @param NewPassWord 
         */
	public void ChangePassWord(String NewPassWord){
		this.password=NewPassWord;
	}

        /**
         * Changes the user's display name.
         * @param NewDisplayName 
         */
	public void ChangeDisplayName(String NewDisplayName){
		this.displayname=NewDisplayName;
	}


        /**
         * Returns the user's username.
         * @return 
         */
	public String GetUserName(){
            return username;
        }
        /**
         * Returns the user's password.
         * @return 
         */
	public String GetPassWord(){
            return password;
        }
        /**
         * Returns the user's display name.
         * @return 
         */
	public String GetDisplayName(){
            return displayname;
        }

        /**
         * Returns the user's security question.
         * @return 
         */
	public String GetSecurityQuestion(){
            return securityquestion;
        }
        /**
         * Returns the user's security answer.
         * @return 
         */
	public String GetSecurityAnswer(){
            return securityanswer;
        }
        /**
         * Returns the user's max level reached.
         * @return 
         */
	public int GetMaxLevel(){
            return maxlevel;
        }

        /**
         * Returns the user's trop score.
         * @return 
         */
	public int GetScore(int i){
            return score[i];
        }
        /**
         * Returns the user's top score as a string.
         * @return 
         */
	public String GetScoreS(int i){
            return Integer.toString(score[i]);
        }
  
}