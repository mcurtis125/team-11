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
 */
public class Login {
    
   //this method open the userfile
   //and return the array of two array lists {username,password}
    public static ArrayList<ArrayList> getuserinfo(String getuserinfolol)
	{
                ArrayList<ArrayList> userinfo =new ArrayList<ArrayList>();
		ArrayList<String> username = new ArrayList<String>();
                ArrayList<String> password = new ArrayList<String>();
                
		try
		{
			File file = new File(getuserinfolol);
			Scanner scanner = new Scanner(file);
			
			scanner.useDelimiter("&"); //shift 12345
                        
			while (scanner.hasNext())
			{
                            
                            String oneuser=scanner.next();
                            String TempUserName="";
                            String TempPassWord="";
                            int status=0;
                            
                              for (int i=0;i<oneuser.length();i++){
                                                           
                                  
                                  if((oneuser.charAt(i)!='^')&&(status==0)){
                                      TempUserName=TempUserName+oneuser.charAt(i);
                                      }
                                  else if (oneuser.charAt(i)=='^'){
                                       status=1;
                                      }
                                  else if((oneuser.charAt(i)!='^')&&(status==1)){
                                      TempPassWord=TempPassWord+oneuser.charAt(i);
                                      } 
                              };
                             
                              username.add(TempUserName);
                              password.add(TempPassWord);
                        }
                        
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			System.exit(1);
		}
                
		userinfo.add(username);
                userinfo.add(password);
                
                return userinfo;
	}
     
   //this method will put the givin username and password behind the txt file 
    public static void create(String username, String password,String filename,int ifdefault){
        
        ArrayList<ArrayList> test=getuserinfo(filename);
        
        ArrayList<String> usernames = new ArrayList<String>();
        usernames=test.get(0);
        int size=usernames.size();
        int done=0;
        
        //check if the user already exists
        for (int i=0;i<size;i++){
            if ((usernames.get(i).equals(username))&&(ifdefault==0)) {
                System.out.println("This user already exsits");
                done=1;
                return;
            }            
        }
        
        if (done==0){
            
            String filein = "&"+username+'^'+password;
            RandomAccessFile mm = null;
            try {
                mm = new RandomAccessFile(filename, "rw");
                
                if (ifdefault==0){
                File file = new File(filename);
		Scanner scanner = new Scanner(file);
                String WholeText=scanner.next();
                filein=WholeText+filein;
                mm.writeBytes(filein);
                
                }else{
                File file = new File(filename);
		Scanner scanner = new Scanner(file);
                mm.writeBytes(filein);
                }
                
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
    
    public static void CheckNCreat(String FilePath) {
        
        String ThereIsAfile = FilePath;
        
        File HiFile = new File(ThereIsAfile);
        if (!HiFile.exists()){
            
        String fileName = "out.txt";
        
        try {
            PrintWriter outputStream = new PrintWriter(fileName);
            outputStream.println("");
            outputStream.flush();
            outputStream.close();
            
            //System.out.println("Done.");
        
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
    }
    
    public static void CreateDefaultUser(String FilePath){
    create("DefaultUser","DefaultPassword",FilePath,1);
    }
    
    public static void RemoveUser(String UserName,String FilePath){
        
        ArrayList<ArrayList> test=getuserinfo(FilePath);
        ArrayList<String> usernames = new ArrayList<String>();
        ArrayList<String> passwords = new ArrayList<String>();
        
        usernames=test.get(0);
        passwords=test.get(1);
        
        int size=usernames.size();
        
        //check if the user already exists
        for (int i=0;i<size;i++){
            if (usernames.get(i).equals(UserName)) {
                usernames.remove(i);
                passwords.remove(i);
            }            
        }
        
        try {
            PrintWriter outputStream = new PrintWriter(FilePath);
            
            String recreate="";
            
                for (int i=0;i<size-1;i++){
                    recreate=recreate+'&'+usernames.get(i)+'^'+passwords.get(i);
                }
            
            outputStream.println(recreate);
            outputStream.flush();
            outputStream.close();
            
            //System.out.println("Done.");
        
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
      }
    
    public static void ChangePW(String User,String NewPW,String FilePath){
        
        ArrayList<ArrayList> test=getuserinfo(FilePath);
        ArrayList<String> usernames = new ArrayList<String>();
        ArrayList<String> passwords = new ArrayList<String>();
        
        usernames=test.get(0);
        passwords=test.get(1);
        
        int size=usernames.size();
        
        //check if the user already exists
        for (int i=0;i<size;i++){
            if (usernames.get(i).equals(User)) {
                passwords.remove(i);
                passwords.add(i,NewPW);
            }            
        }
        
        try {
            PrintWriter outputStream = new PrintWriter(FilePath);
            
            String recreate="";
            
                for (int i=0;i<size;i++){
                    recreate=recreate+'&'+usernames.get(i)+'^'+passwords.get(i);
                }
            
            outputStream.println(recreate);
            outputStream.flush();
            outputStream.close();
            
            //System.out.println("Done.");
        
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
      }
    
    
    //testing
    public static void main(String[] args) {
        
        String ThereIsAfile="H:/321/login/out.txt";
        
        CheckNCreat(ThereIsAfile);
        CreateDefaultUser(ThereIsAfile);
                
        create("hello","hi",ThereIsAfile,0);
        create("hello","hi",ThereIsAfile,0);
        create("hello2","hi2",ThereIsAfile,0);
        create("hello3","hi3",ThereIsAfile,0);
        RemoveUser("hello3",ThereIsAfile);
        RemoveUser("hello2",ThereIsAfile);
        ChangePW("hello","hi2",ThereIsAfile);
        
        ArrayList<ArrayList> userinfolist=getuserinfo(ThereIsAfile);
        
        for (int i=0;i<userinfolist.get(0).size();i++){
        System.out.println(userinfolist.get(0).get(i));
        }
        
        for (int i=0;i<userinfolist.get(0).size();i++){
        System.out.println(userinfolist.get(1).get(i));
        }
        
    
    }
}

