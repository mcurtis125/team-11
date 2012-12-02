/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanprogram;


import java.io.*;
import java.util.*;


/**
 * @author xchen82
 * @author ChanWoo
 * please delete "UserData.txt" before starting a new test
 */
public class New_Login {

	//testing
	public static void main(String[] args) {

		String Path="UserData.txt";

		New_FileManagement.Initialize(Path);
		New_ProfileManagement.create("hello","hi","Display","Security1","Answer1",Path);
		New_ProfileManagement.create("hello2","hi2","Display2","Security2","Answer2",Path);
		New_ProfileManagement.create("test3","hi3","Display3","Security3","Answer3",Path);
		New_ProfileManagement.remove("hello",Path);
		New_ProfileManagement.ChangePW("hello2","hinew2",Path);
		New_ProfileManagement.ChangeDN("test3","bye3",Path);

		New_DataManagement.AddNewScore("hello2",15,Path);
		New_DataManagement.AddNewScore("test3",150,Path);
		New_DataManagement.AddNewScore("test3",200,Path);
		New_DataManagement.AddNewScore("test3",300,Path);

		ArrayList<New_GetLoginInfoClass> userinfolist=New_GetUserInfo.getuserinfo(Path);


		//All User List Test
		for (int i=0;i<userinfolist.size();i++){
			int k=i+1;
			System.out.println('\n');
			System.out.println("Here is the "+k+"th user:");
			System.out.println("Username:"+userinfolist.get(i).GetUserName());
			System.out.println("Password:"+userinfolist.get(i).GetPassWord());
			System.out.println("DisplayName:"+userinfolist.get(i).GetDisplayName());
			System.out.println("Here is the score of the "+k+"th user:");
			System.out.println(userinfolist.get(i).GetScore(0));
			System.out.println(userinfolist.get(i).GetScore(1));
			System.out.println(userinfolist.get(i).GetScore(2));
			System.out.println(userinfolist.get(i).GetScore(3));
			System.out.println(userinfolist.get(i).GetScore(4));
			System.out.println(userinfolist.get(i).GetScore(5));
			System.out.println(userinfolist.get(i).GetScore(6));
			System.out.println(userinfolist.get(i).GetScore(7));
			System.out.println(userinfolist.get(i).GetScore(8));
			System.out.println(userinfolist.get(i).GetScore(9));
			System.out.println(userinfolist.get(i).GetSecurityQuestion());
			System.out.println(userinfolist.get(i).GetSecurityAnswer());
			System.out.println(userinfolist.get(i).GetMaxLevel());

		}

		//Top Score Test
		System.out.println('\n');
		System.out.println("Here are top 10 scores:");
		for (int i=0;i<10;i++){
			System.out.println(New_DataManagement.GetTop10Score(Path).get(i).GetPlayer()+":"+New_DataManagement.GetTop10Score(Path).get(i).GetScore());
		}
	}
}
