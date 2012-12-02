package pacmanprogram;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author xchen82
 * @author ChanWoo
 * 
 */

public class New_ProfileManagement {

	/**
	 *This class will create a user, its default maxlevel is 0
	 *this is a boolean, it will return false if the creation failed,true if the created succeed
	 */
	public static boolean create(String username, String password, String displayname, String securityquestion,String securityanswer,String filename){

		ArrayList<New_GetLoginInfoClass> UserList=New_GetUserInfo.getuserinfo(filename);
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
	 *This class will remove a specific user
	 */
	public static void remove(String username, String password, String securityAnswer, String FilePath){

		ArrayList<New_GetLoginInfoClass> UserList=New_GetUserInfo.getuserinfo(FilePath);

		//check if the user already exists
		for (int i=0;i<UserList.size();i++){
			if (UserList.get(i).GetUserName().equals(username)&&UserList.get(i).GetPassWord().equals(password)&&UserList.get(i).GetSecurityAnswer().equals(securityAnswer)) {
				UserList.remove(i);
			}            
		}

		New_FileManagement.UpdateFile(UserList,FilePath);
	}

	/**
	 *This class can change the password of a specific user
	 */
	public static void ChangePW(String username,String password,String NewPW,String FilePath){

		ArrayList<New_GetLoginInfoClass> UserList=New_GetUserInfo.getuserinfo(FilePath);

		//check which user
		for (int i=0;i<UserList.size();i++){

			if (UserList.get(i).GetUserName().equals(username)&&UserList.get(i).GetPassWord().equals(password)) {
				UserList.get(i).ChangePassWord(NewPW);
			}

		}

		New_FileManagement.UpdateFile(UserList,FilePath);
	}

	/**
	 *This class can change the display name of a specific user
	 */
	public static void ChangeDN(String username,String password,String NewDN,String FilePath){

		ArrayList<New_GetLoginInfoClass> UserList=New_GetUserInfo.getuserinfo(FilePath);

		//check which user
		for (int i=0;i<UserList.size();i++){

			if (UserList.get(i).GetUserName().equals(username)&&UserList.get(i).GetPassWord().equals(password)) {
				UserList.get(i).ChangeDisplayName(NewDN);
			}

		}

		New_FileManagement.UpdateFile(UserList,FilePath);
	}

}
