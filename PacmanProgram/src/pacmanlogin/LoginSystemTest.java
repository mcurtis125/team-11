/**This JUnit Test is under Eclipse IDE for Java Developers
 * Version: Juno Release
 * Build id: 20120614-1722
 * JavaSE - 1.6
 * @author xchen82
 */

package pacmanlogin;

import static org.junit.Assert.*;

import org.junit.Test;
import java.io.*;
import java.util.*;

public class LoginSystemTest {
	
	private final static String Path="UserData.txt";
	
	private final static String[] createTest_username ={"Guest","player1","player2","player3","player4"};
	private final static String[] createTest_password ={"DefaultPassword","password1","password2","password3","password4"};
	private final static String[] createTest_displayname={"Guest","Kevin","Voula","Shinchi","Justin"};
	private final static String[] createTest_SecurityQ={"DefaultSecurityQuestion","SecurityQ1","SecurityQ2","SecurityQ3","SecurityQ4"};
	private final static String[] createTest_SecurityA={"DefaultSecurityAnswer","SecurityA1","SecurityA2","SecurityA3","SecurityA4"};
	private final static int createTest_NewMaxLevel=1;
	
	private final static String[] removeTest_username ={"Guest","player3","player4"};
	private final static String[] removeTest_password ={"DefaultPassword","password3","password4"};
	private final static String[] removeTest_displayname={"Guest","Shinchi","Justin"};
	private final static String[] removeTest_SecurityQ={"DefaultSecurityQuestion","SecurityQ3","SecurityQ4"};
	private final static String[] removeTest_SecurityA={"DefaultSecurityAnswer","SecurityA3","SecurityA4"};
	private final static int removeTest_NewMaxLevel=1;
	
	private final static String[] changePWTest_username ={"Guest","player3","player4"};
	private final static String[] changePWTest_OldPassword ={"DefaultPassword","password3","password4"};
	private final static String[] changePWTest_password ={"DefaultPassword","password3new","password4"};
	
	private final static String[] changeDNTest_username ={"Guest","player3","player4"};
	private final static String[] changeDNTest_password ={"DefaultPassword","password3new","password4new"};
	private final static String[] changeDNTest_displayname ={"Guest","Shinchi_New","Justin_New"};
	
	private final static String[] addNewMaxLvTest_username ={"Guest","player3","player4"};
	private final static int[] addNewMaxLvTest_newLV={1,3,20};
	
	private final static String[] addNewScoreTest_username ={"Guest","player3","player4"};
	private final static int[] addNewScoreTest_newScore={300,200,100,100,90,80,70,60,50,40};
	
	public static void createTest_Init(){
		
		FileManagement.deletefile(Path);
		FileManagement.Initialize(Path);
		
		ProfileManagement.create(createTest_username[1],createTest_password[1],createTest_displayname[1],createTest_SecurityQ[1],createTest_SecurityA[1],Path);
		ProfileManagement.create(createTest_username[2],createTest_password[2],createTest_displayname[2],createTest_SecurityQ[2],createTest_SecurityA[2],Path);
		ProfileManagement.create(createTest_username[3],createTest_password[3],createTest_displayname[3],createTest_SecurityQ[3],createTest_SecurityA[3],Path);
		ProfileManagement.create(createTest_username[4],createTest_password[4],createTest_displayname[4],createTest_SecurityQ[4],createTest_SecurityA[4],Path);
}
	
	public static void removeTest_Init(){
		ProfileManagement.remove(createTest_username[1],createTest_password[1],createTest_SecurityA[1],Path);
		ProfileManagement.remove(createTest_username[2],createTest_password[2],createTest_SecurityA[2],Path);
	}
	
	public static void changePWTest_Init(){
		ProfileManagement.ChangePW(changePWTest_username[1],changePWTest_OldPassword[1],changePWTest_password[1],Path);
		ProfileManagement.ChangePW(changePWTest_username[2],changePWTest_OldPassword[2],changePWTest_password[2],Path);
	}
	
	public static void changeDNTest_Init(){
		ProfileManagement.ChangeDN(changeDNTest_username[1],changeDNTest_password[1],changeDNTest_displayname[1],Path);
		ProfileManagement.ChangeDN(changeDNTest_username[2],changeDNTest_password[2],changeDNTest_displayname[2],Path);
	}
	
	public static void addNewMaxLvTest_Init(){
		DataManagement.AddNewMaxLevel(addNewMaxLvTest_username[1],addNewMaxLvTest_newLV[1],Path);
		DataManagement.AddNewMaxLevel(addNewMaxLvTest_username[2],addNewMaxLvTest_newLV[2],Path);
	}
	
	public static void AddNewScoreTest_Init(){
		DataManagement.AddNewScore(addNewScoreTest_username[0],addNewScoreTest_newScore[0],Path);
		DataManagement.AddNewScore(addNewScoreTest_username[1],addNewScoreTest_newScore[1],Path);
		DataManagement.AddNewScore(addNewScoreTest_username[2],addNewScoreTest_newScore[2],Path);
	}
	

	@Test
	public void createTest() {
		createTest_Init();
		ArrayList<GetLoginInfo> userinfolist=GetUserInfo.getuserinfo(Path);
		for (int i=0;i<4;i++){
		assertEquals(userinfolist.get(i).GetUserName(),createTest_username[i]);
		assertEquals(userinfolist.get(i).GetPassWord(),createTest_password[i]);
		assertEquals(userinfolist.get(i).GetDisplayName(),createTest_displayname[i]);
		assertEquals(userinfolist.get(i).GetSecurityQuestion(),createTest_SecurityQ[i]);
		assertEquals(userinfolist.get(i).GetSecurityAnswer(),createTest_SecurityA[i]);
		assertEquals(userinfolist.get(i).GetMaxLevel(),createTest_NewMaxLevel);
		}
	}
	
	@Test
	public void removeTest() {
		removeTest_Init();
		ArrayList<GetLoginInfo> userinfolist=GetUserInfo.getuserinfo(Path);
		for (int i=0;i<2;i++){
		assertEquals(userinfolist.get(i).GetUserName(),removeTest_username[i]);
		assertEquals(userinfolist.get(i).GetPassWord(),removeTest_password[i]);
		assertEquals(userinfolist.get(i).GetDisplayName(),removeTest_displayname[i]);
		assertEquals(userinfolist.get(i).GetSecurityQuestion(),removeTest_SecurityQ[i]);
		assertEquals(userinfolist.get(i).GetSecurityAnswer(),removeTest_SecurityA[i]);
		assertEquals(userinfolist.get(i).GetMaxLevel(),removeTest_NewMaxLevel);
		}
	}
	
	@Test
	public void changePWTest() {
		changePWTest_Init();
		ArrayList<GetLoginInfo> userinfolist=GetUserInfo.getuserinfo(Path);
		for (int i=0;i<2;i++){
		assertEquals(userinfolist.get(i).GetPassWord(),changePWTest_password[i]);
		}
	}
	
	@Test
	public void changeDNTest() {
		changeDNTest_Init();
		ArrayList<GetLoginInfo> userinfolist=GetUserInfo.getuserinfo(Path);
		for (int i=0;i<2;i++){
		assertEquals(userinfolist.get(i).GetDisplayName(),changeDNTest_displayname[i]);
		}
	}
	
	@Test
	public void addNewMaxLvTest() {
		addNewMaxLvTest_Init();
		ArrayList<GetLoginInfo> userinfolist=GetUserInfo.getuserinfo(Path);
		for (int i=0;i<2;i++){
		assertEquals(userinfolist.get(i).GetMaxLevel(),addNewMaxLvTest_newLV[i]);
		}
	}
	
	@Test
	public void addNewScoreTest() {
		AddNewScoreTest_Init();
		ArrayList<GetLoginInfo> userinfolist=GetUserInfo.getuserinfo(Path);
		for (int i=0;i<2;i++){
		assertEquals(userinfolist.get(i).GetScore(0),addNewScoreTest_newScore[i]);
		}
	}
	
	@Test
	public void StatisticsTest() {
		ArrayList<TopScores> top_score=DataManagement.GetTop10Score(Path);
		for (int i=0;i<9;i++){
		assertEquals(top_score.get(i).GetScore(),addNewScoreTest_newScore[i]);
		}
	}
	
}
