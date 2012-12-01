package pacmanprogram;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class will read the UserData.txt file and convert all the users' information into an array list
 * @author xchen82
 */

public class New_GetUserInfo {

	/**
	 *This class will read the UserData.txt file and convert all the users' information into an array list
	 */
	public static ArrayList<New_GetLoginInfoClass> getuserinfo(String FilePath)
	{
		ArrayList<New_GetLoginInfoClass> userinfo =new ArrayList<New_GetLoginInfoClass>();

		try
		{
			File file = new File(FilePath);
			Scanner scanner = new Scanner(file);

			scanner.useDelimiter("&"); //shift 7

			while (scanner.hasNext())
			{

				String oneuser=scanner.next();
				String TempUserName="";
				String TempPassWord="";
				String TempDisplayName="";

				String TempScore1="";
				String TempScore2="";
				String TempScore3="";
				String TempScore4="";
				String TempScore5="";
				String TempScore6="";
				String TempScore7="";
				String TempScore8="";
				String TempScore9="";
				String TempScore10="";

				String TempSecurityQuestion="";
				String TempSecurityAnswer="";
				String TempMaxLevel="";

				int status=0;

				for (int i=0;i<oneuser.length();i++){


					if((oneuser.charAt(i)!='^')&&(status==0)){
						TempUserName=TempUserName+oneuser.charAt(i);//username
					}
					else if ((status==0)&&(oneuser.charAt(i)=='^')){
						status=1;
					}
					else if((oneuser.charAt(i)!='^')&&(status==1)){
						TempPassWord=TempPassWord+oneuser.charAt(i);//password
					} 
					else if ((status==1)&&(oneuser.charAt(i)=='^')){
						status=2;
					}
					else if((oneuser.charAt(i)!='^')&&(status==2)){//score1
						TempDisplayName=TempDisplayName+oneuser.charAt(i);
					} 
					else if ((status==2)&&(oneuser.charAt(i)=='^')){
						status=3;
					}
					else if((oneuser.charAt(i)!='^')&&(status==3)){//score2
						TempScore1=TempScore1+oneuser.charAt(i);
					} 
					else if ((status==3)&&(oneuser.charAt(i)=='^')){
						status=4;
					}
					else if((oneuser.charAt(i)!='^')&&(status==4)){
						TempScore2=TempScore2+oneuser.charAt(i);
					} 
					else if ((status==4)&&(oneuser.charAt(i)=='^')){
						status=5;
					}
					else if((oneuser.charAt(i)!='^')&&(status==5)){
						TempScore3=TempScore3+oneuser.charAt(i);
					} 
					else if ((status==5)&&(oneuser.charAt(i)=='^')){
						status=6;
					}
					else if((oneuser.charAt(i)!='^')&&(status==6)){
						TempScore4=TempScore4+oneuser.charAt(i);
					} 
					else if ((status==6)&&(oneuser.charAt(i)=='^')){
						status=7;
					}
					else if((oneuser.charAt(i)!='^')&&(status==7)){
						TempScore5=TempScore5+oneuser.charAt(i);
					} 
					else if ((status==7)&&(oneuser.charAt(i)=='^')){
						status=8;
					}
					else if((oneuser.charAt(i)!='^')&&(status==8)){
						TempScore6=TempScore6+oneuser.charAt(i);
					} 
					else if ((status==8)&&(oneuser.charAt(i)=='^')){
						status=9;
					}
					else if((oneuser.charAt(i)!='^')&&(status==9)){
						TempScore7=TempScore7+oneuser.charAt(i);
					} 
					else if ((status==9)&&(oneuser.charAt(i)=='^')){
						status=10;
					}
					else if((oneuser.charAt(i)!='^')&&(status==10)){
						TempScore8=TempScore8+oneuser.charAt(i);
					} 
					else if ((status==10)&&(oneuser.charAt(i)=='^')){
						status=11;
					}
					else if((oneuser.charAt(i)!='^')&&(status==11)){
						TempScore9=TempScore9+oneuser.charAt(i);
					} 
					else if ((status==11)&&(oneuser.charAt(i)=='^')){
						status=12;
					}
					else if((oneuser.charAt(i)!='^')&&(status==12)){
						TempScore10=TempScore10+oneuser.charAt(i);
					}
					else if ((status==12)&&(oneuser.charAt(i)=='^')){
						status=13;
					}
					else if((oneuser.charAt(i)!='^')&&(status==13)){
						TempSecurityQuestion=TempSecurityQuestion+oneuser.charAt(i);
					}
					else if ((status==13)&&(oneuser.charAt(i)=='^')){
						status=14;
					}
					else if((oneuser.charAt(i)!='^')&&(status==14)){
						TempSecurityAnswer=TempSecurityAnswer+oneuser.charAt(i);
					}
					else if ((status==14)&&(oneuser.charAt(i)=='^')){
						status=15;
					}
					else if((oneuser.charAt(i)!='^')&&(status==15)){
						TempMaxLevel=TempMaxLevel+oneuser.charAt(i);
					}


					else if(oneuser.charAt(i)=='*')  
					{
						break;
					}

				};

				int[] UserScore={Integer.parseInt(TempScore1)
						,Integer.parseInt(TempScore2)
						,Integer.parseInt(TempScore3)
						,Integer.parseInt(TempScore4)
						,Integer.parseInt(TempScore5)
						,Integer.parseInt(TempScore6)
						,Integer.parseInt(TempScore7)
						,Integer.parseInt(TempScore8)
						,Integer.parseInt(TempScore9)
						,Integer.parseInt(TempScore10)};

				New_GetLoginInfoClass TempUser = new New_GetLoginInfoClass(

						TempUserName,
						TempPassWord,
						TempDisplayName,
						UserScore,
						TempSecurityQuestion,
						TempSecurityAnswer,
						Integer.parseInt(TempMaxLevel)
						);

				userinfo.add(TempUser);
			}

		}
		catch(Exception e)
		{
			System.out.println("create method error");
			System.exit(1);
		}

		return userinfo;
	}

}
