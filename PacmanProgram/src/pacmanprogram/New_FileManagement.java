package pacmanprogram;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author xchen82
 * @author ChanWoo
 */

public class New_FileManagement {
	
	/**
	 *This method will initialize the UserFile
         *If UserData.txt does not exists, it will create a new UserData.txt with a sample player
	 *If UserData.txt already exists, it won't do anything.
         * 
         * @param FilePath Name of the .txt file where profile information is saved
	 **/
	public static void Initialize(String FilePath) {

		String ThereIsAfile = FilePath;

		File HiFile = new File(ThereIsAfile);
		if (!HiFile.exists()){

			String fileName = "UserData.txt";

			try {
				PrintWriter outputStream = new PrintWriter(fileName);
				outputStream.write("&DefaultUser^DefaultPassword^DefaultDisplayName^100^90^80^70^60^50^40^30^20^10^DefaultSecurityQuestion^DefaultSecurityAnswer^1");
				outputStream.flush();
				outputStream.close();

				System.out.println("UserData File Created");

			} catch (FileNotFoundException e){
				e.printStackTrace();
			}
		}
	}


	/**
	 *This method will rewrite the UserData.txt based on its input variables 
         *(an array list consists of all user information)
	 *It is widely called in all other user management methods.
         * 
         * @param NewInfoClass Parameter of array list that collects all user informations
         * @param FilePath Name of the .txt file where profile information is saved
	 **/
	public static void UpdateFile(ArrayList<New_GetLoginInfoClass> NewInfoClass,String FilePath)
	{

		try {

			PrintWriter outputStream = new PrintWriter(FilePath);

			String recreate="";

			for (int i=0;i<NewInfoClass.size();i++){
				recreate=recreate+'&'+NewInfoClass.get(i).GetUserName()
						+'^'+NewInfoClass.get(i).GetPassWord()+'^'+NewInfoClass.get(i).GetDisplayName()+'^'+NewInfoClass.get(i).GetScoreS(0)
						+'^'+NewInfoClass.get(i).GetScoreS(1)+'^'+NewInfoClass.get(i).GetScoreS(2)+'^'+NewInfoClass.get(i).GetScoreS(3)+'^'+NewInfoClass.get(i).GetScoreS(4)
						+'^'+NewInfoClass.get(i).GetScoreS(5)+'^'+NewInfoClass.get(i).GetScoreS(6)+'^'+NewInfoClass.get(i).GetScoreS(7)+'^'+NewInfoClass.get(i).GetScoreS(8)
						+'^'+NewInfoClass.get(i).GetScoreS(9)+'^'+NewInfoClass.get(i).GetSecurityQuestion()+'^'+NewInfoClass.get(i).GetSecurityAnswer()
						+'^'+Integer.toString(NewInfoClass.get(i).GetMaxLevel())
						;
			}

			outputStream.write(recreate);
			outputStream.flush();
			outputStream.close();

		} catch (FileNotFoundException e){
			e.printStackTrace();
		}

	}
}
