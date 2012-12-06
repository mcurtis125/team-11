package pacmanlogin;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Creates, edits and deletes users.
 * @author xchen82
 * @author ChanWoo
 * 
 */

public class ProfileManagement {

    /**
     * Creates a user with default max level 1.
     * @param username
     * @param password
     * @param displayname
     * @param securityquestion
     * @param securityanswer
     * @param filename
     * @return false if creation failed, true if creation succeeded
     */
	public static boolean create(String username, String password, String displayname, String securityquestion,String securityanswer,String filename){

		ArrayList<GetLoginInfo> UserList=GetUserInfo.getuserinfo(filename);
		int done=0;

		//check if the user already exists
		for (int i=0;i<UserList.size();i++){
			if (UserList.get(i).GetUserName().equals(username)) {
				System.out.println("This user already exsits");
				done=1;
				return false; //creation failed
			}            
		}

		if (done==0){

			String filein = "&"+username+'^'+password+'^'+displayname+'^'+'0'+'^'+'0'+'^'+'0'+'^'+'0'+'^'+'0'+'^'+'0'+'^'+'0'+'^'+'0'+'^'+'0'+'^'+'0'+'^'+securityquestion+'^'+securityanswer+'^'+'1';
			RandomAccessFile mm = null;
			try {
				mm = new RandomAccessFile(filename, "rw");

				File file = new File(filename);
				Scanner scanner = new Scanner(file);
				String WholeText=scanner.next();
				filein=WholeText+filein;
				mm.writeBytes(filein);

			} catch (IOException e1) {

				e1.printStackTrace();
			} finally {
				if (mm != null) {
					try {
						mm.close();
					} catch (IOException e2) {

						e2.printStackTrace();
					}
				}
			}
		}

		return true; //creation succeed
	}

	/**
	 *Removes a specific user
	 */
	public static void remove(String username, String password, String securityAnswer, String FilePath){

		ArrayList<GetLoginInfo> UserList=GetUserInfo.getuserinfo(FilePath);

		//check if the user already exists
		for (int i=0;i<UserList.size();i++){
			if (UserList.get(i).GetUserName().equals(username)&&UserList.get(i).GetPassWord().equals(password)&&UserList.get(i).GetSecurityAnswer().equals(securityAnswer)) {
				UserList.remove(i);
			}            
		}

		FileManagement.UpdateFile(UserList,FilePath);
	}

	/**
         * Changes the password of a specific user
         * @param username
         * @param password
         * @param NewPW
         * @param FilePath 
         */
	public static void ChangePW(String username,String password,String NewPW,String FilePath){

		ArrayList<GetLoginInfo> UserList=GetUserInfo.getuserinfo(FilePath);

		//check which user
		for (int i=0;i<UserList.size();i++){

			if (UserList.get(i).GetUserName().equals(username)&&UserList.get(i).GetPassWord().equals(password)) {
				UserList.get(i).ChangePassWord(NewPW);
			}

		}

		FileManagement.UpdateFile(UserList,FilePath);
	}

	/**
         * This class can change the display name of a specific user
         * @param username
         * @param password
         * @param NewDN
         * @param FilePath 
         */
	public static void ChangeDN(String username,String password,String NewDN,String FilePath){

		ArrayList<GetLoginInfo> UserList=GetUserInfo.getuserinfo(FilePath);

		//check which user
		for (int i=0;i<UserList.size();i++){

			if (UserList.get(i).GetUserName().equals(username)&&UserList.get(i).GetPassWord().equals(password)) {
				UserList.get(i).ChangeDisplayName(NewDN);
			}

		}

		FileManagement.UpdateFile(UserList,FilePath);
	}

}
