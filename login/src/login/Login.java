/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package login;


import java.io.*;
import java.util.*;


/**
 *
 * @author xchen82
 * please delete "UserData.txt" before starting a new test
 *
 *	methods included:
 *
 *	Initialize: must run this at the begining, it will detect if the UserData.txt exists. If not, it will create a new one.
 *
 *	Getuserinfo: will load the UserData.txt and transfer it to an arraylist of "GetLoginInfoClass" Class
 *
 *	create:will create a new user
 *
 *	remove:will remove a user
 *
 *	ChangePW:will change the password of a specfic user
 *
 *	ChangeDN:will change the displayname of a specfic user
 *
 *	AddNewScore:will add a new score to a user. it will compare the new score with the 10 current scores and re-rank them
 *
 *	GetTop10Score:will return top 10 score and user in the form of an arraylist of the "TopScores" class
 *
 *	UpdateFile:will update the UserData.java File, this is called in most of other classes such as create,remove and etc.
 *
 *	main:tester
 *
 */
public class Login {
    
   
    public static ArrayList<GetLoginInfoClass> getuserinfo(String FilePath)
	{
                ArrayList<GetLoginInfoClass> userinfo =new ArrayList<GetLoginInfoClass>();
                             
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
                                  
                                  else if(oneuser.charAt(i)=='*')  //start of the current top 10 score
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
                    		  
                              GetLoginInfoClass TempUser = new GetLoginInfoClass(
                            		  
                            		  TempUserName,
                            		  TempPassWord,
                            		  TempDisplayName,
                            		  UserScore
                            		  
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

    

    public static void create(String username, String password, String displayname, String filename){
        
       ArrayList<GetLoginInfoClass> test=getuserinfo(filename);
       int done=0;
        
        //check if the user already exists
        for (int i=0;i<test.size();i++){
            if (test.get(i).GetUserName().equals(username)) {
                System.out.println("This user already exsits");
                done=1;
                return;
            }            
        }
        
        if (done==0){
            
            String filein = "&"+username+'^'+password+'^'+displayname+'^'+'0'+'^'+'0'+'^'+'0'+'^'+'0'+'^'+'0'+'^'+'0'+'^'+'0'+'^'+'0'+'^'+'0'+'^'+'0';
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
    }
    
    
    public static void Initialize(String FilePath) {
        
        String ThereIsAfile = FilePath;
        
        File HiFile = new File(ThereIsAfile);
        if (!HiFile.exists()){
            
        String fileName = "UserData.txt";
        
        try {
            PrintWriter outputStream = new PrintWriter(fileName);
            outputStream.write("&DefaultUser^DefaultPassword^DefaultDisplayName^100^90^80^70^60^50^40^30^20^10");
            outputStream.flush();
            outputStream.close();

            System.out.println("UserData File Created");
            
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        
    }
    }
    
    public static void remove(String username,String FilePath){
        
        ArrayList<GetLoginInfoClass> test=getuserinfo(FilePath);
               
        //check if the user already exists
        for (int i=0;i<test.size();i++){
            if (test.get(i).GetUserName().equals(username)) {
                test.remove(i);
                }            
        }
        
        UpdateFile(test,FilePath);
      }
    
    public static void ChangePW(String username,String NewPW,String FilePath){
        
    	ArrayList<GetLoginInfoClass> test=getuserinfo(FilePath);
    	
        //check which user
        for (int i=0;i<test.size();i++){
        	
            if (test.get(i).GetUserName().equals(username)) {
            	test.get(i).ChangePassWord(NewPW);
            }
            
        }
        
        UpdateFile(test,FilePath);
      }
    
    public static void ChangeDN(String username,String NewDN,String FilePath){
        
    	ArrayList<GetLoginInfoClass> test=getuserinfo(FilePath);
    	
        //check which user
        for (int i=0;i<test.size();i++){
        	
            if (test.get(i).GetUserName().equals(username)) {
            	test.get(i).ChangeDisplayName(NewDN);
            }
            
        }
        
        UpdateFile(test,FilePath);
      }
    
    public static void AddNewScore(String username, int NewScore,String FilePath){
    	
    	ArrayList<GetLoginInfoClass> test=getuserinfo(FilePath);
    	
        for (int i=0;i<test.size();i++){
        	
            if (test.get(i).GetUserName().equals(username)) {
            	test.get(i).AddNewScore(NewScore);
            }
            
        }    	
    	
        UpdateFile(test,FilePath);
    }
    
    public static ArrayList<TopScores> GetTop10Score(String FilePath)
    {
    	ArrayList<GetLoginInfoClass> test=getuserinfo(FilePath);
    	ArrayList<TopScores> test2= new ArrayList<TopScores>();
    	
    	int TotalScores=10*test.size();
    	int[] AllScores= new int[TotalScores];
    	
    	for (int i=0;i<test.size();i++){
    		for (int a=0;a<10;a++){
    		AllScores[10*i+a]=test.get(i).GetScore(a);
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
    		for (int i=0;i<test.size();i++){
    			for (int a=0;a<10;a++){
    					if (test.get(i).GetScore(a)==Top10Score[b]){
    						Top10Player[b]=test.get(i).GetDisplayName();
    					}
    			}
    		} 
    	}
    	
    	
    	for (int i=0;i<10;i++){
    		TopScores topplayer=new TopScores(Top10Player[i], Top10Score[i]);
    		test2.add(topplayer);
    	}
    	
    	return test2;
    }
    
    public static void UpdateFile(ArrayList<GetLoginInfoClass> NewInfoClass,String FilePath)
    {

    	try {

            PrintWriter outputStream = new PrintWriter(FilePath);
            
            String recreate="";
            
                for (int i=0;i<NewInfoClass.size();i++){
                    recreate=recreate+'&'+NewInfoClass.get(i).GetUserName()
                    		+'^'+NewInfoClass.get(i).GetPassWord()+'^'+NewInfoClass.get(i).GetDisplayName()+'^'+NewInfoClass.get(i).GetScoreS(0)
                    		+'^'+NewInfoClass.get(i).GetScoreS(1)+'^'+NewInfoClass.get(i).GetScoreS(2)+'^'+NewInfoClass.get(i).GetScoreS(3)+'^'+NewInfoClass.get(i).GetScoreS(4)
                    		+'^'+NewInfoClass.get(i).GetScoreS(5)+'^'+NewInfoClass.get(i).GetScoreS(6)+'^'+NewInfoClass.get(i).GetScoreS(7)+'^'+NewInfoClass.get(i).GetScoreS(8)
                    		+'^'+NewInfoClass.get(i).GetScoreS(9)
                    		;
                }
               
            outputStream.write(recreate);
            outputStream.flush();
            outputStream.close();
            
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        
    }
    
    
       
    //testing
    public static void main(String[] args) {
        
        String Path="UserData.txt";

        Initialize(Path);
        create("hello","hi","Display",Path);
        create("hello2","hi2","Display2",Path);
        create("test3","hi3","Display3",Path);
        remove("hello",Path);
        ChangePW("hello2","hinew2",Path);
        ChangeDN("test3","bye3",Path);
        
        AddNewScore("hello2",15,Path);
        AddNewScore("test3",150,Path);
        AddNewScore("test3",200,Path);
        AddNewScore("test3",300,Path);
        
        ArrayList<GetLoginInfoClass> userinfolist=getuserinfo(Path);
        
                
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
        }
 
 //Top Score Test
        System.out.println('\n');
        System.out.println("Here are top 10 scores:");
        for (int i=0;i<10;i++){
        	System.out.println(GetTop10Score(Path).get(i).GetPlayer()+":"+GetTop10Score(Path).get(i).GetScore());
        }
}
}
