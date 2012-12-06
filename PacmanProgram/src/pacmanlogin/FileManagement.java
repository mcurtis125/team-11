package pacmanlogin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Creates, reads from and writes to the file that stores all user info.
 * @author xchen82
 * @author ChanWoo
 */

public class FileManagement {
	
	/**
	 *This method will delete the UserFile
     *this is for testing purpose
     * @param FilePath Name of the .txt file where profile information is saved
	 **/
	public static void deletefile(String FilePath) {
		
			    String fileName = "UserData.txt";
			    // A File object to represent the filename
			    File f = new File(fileName);

			    // Make sure the file or directory exists and isn't write protected
			    if (!f.exists())
			      throw new IllegalArgumentException(
			          "Delete: no such file or directory: " + fileName);

			    if (!f.canWrite())
			      throw new IllegalArgumentException("Delete: write protected: "
			          + fileName);

			    // If it is a directory, make sure it is empty
			    if (f.isDirectory()) {
			      String[] files = f.list();
			      if (files.length > 0)
			        throw new IllegalArgumentException(
			            "Delete: directory not empty: " + fileName);
			    }

			    // Attempt to delete it
			    boolean success = f.delete();

			    if (!success)
			      throw new IllegalArgumentException("Delete: deletion failed");
			  }
	
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
				outputStream.write("&Guest^DefaultPassword^Guest^100^90^80^70^60^50^40^30^20^10^DefaultSecurityQuestion^DefaultSecurityAnswer^1");
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
	public static void UpdateFile(ArrayList<GetLoginInfo> NewInfoClass,String FilePath)
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
