package pacmanprogram;

import java.util.ArrayList;
import java.util.Arrays;

/**
* This method can update player's score, max reached level
* @author xchen82
*/

public class New_DataManagement {
	
	/**
	* This method can update player's max reached level,
	* if the user's current max level is lower than the input int MaxLevel,
	* it will update the user's max level
	*/
	public static void AddNewMaxLevel(String username, int MaxLevel, String FilePath){
    	ArrayList<New_GetLoginInfoClass> UserList=New_GetUserInfo.getuserinfo(FilePath);
    	
    	for (int i=0;i<UserList.size();i++){
        	
            if (UserList.get(i).GetUserName().equals(username)) {
            	if(UserList.get(i).GetMaxLevel()<MaxLevel){
            	UserList.get(i).AddNewMaxLevel(MaxLevel);}
            }
            
        }
    	New_FileManagement.UpdateFile(UserList,FilePath);
    }
    
	/**
	* This method can update player's max 10 score,
	* it will compare the new score with the current 10 scores, and place it in the right place(if needed)
	*/
    public static void AddNewScore(String username, int NewScore,String FilePath){
    	
    	ArrayList<New_GetLoginInfoClass> UserList=New_GetUserInfo.getuserinfo(FilePath);
    	
        for (int i=0;i<UserList.size();i++){
        	
            if (UserList.get(i).GetUserName().equals(username)) {
            	UserList.get(i).AddNewScore(NewScore);
            }
            
        }    	
    	
        New_FileManagement.UpdateFile(UserList,FilePath);
    }
    
    /**
	* This will return the Top10 score in the form of class TopScores(Player,TopScore)
	*/
    public static ArrayList<New_TopScores> GetTop10Score(String FilePath)
    {
    	ArrayList<New_GetLoginInfoClass> UserList=New_GetUserInfo.getuserinfo(FilePath);
    	ArrayList<New_TopScores> Top10ScoreList= new ArrayList<New_TopScores>();
    	
    	int TotalScores=10*UserList.size();
    	int[] AllScores= new int[TotalScores];
    	
    	for (int i=0;i<UserList.size();i++){
    		for (int a=0;a<10;a++){
    		AllScores[10*i+a]=UserList.get(i).GetScore(a);
    		}
    	}
    	
    	Arrays.sort(AllScores);
    	int[] Top10Score =  new int[10];
    	
    	for(int c=1;c<=10;c++){
    		Top10Score[c-1]=AllScores[TotalScores-c];
    	}
    	//Top10Score[0] is the Global Highest Score 
    	    	
    	String[] Top10Player=new String[10];
    	
    	for (int b=0;b<10;b++){
    		for (int i=0;i<UserList.size();i++){
    			for (int a=0;a<10;a++){
    					if (UserList.get(i).GetScore(a)==Top10Score[b]){
    						Top10Player[b]=UserList.get(i).GetDisplayName();
    					}
    			}
    		} 
    	}
    	
    	
    	for (int i=0;i<10;i++){
    		New_TopScores topplayer=new New_TopScores(Top10Player[i], Top10Score[i]);
    		Top10ScoreList.add(topplayer);
    	}
    	
    	return Top10ScoreList;
    }
}
