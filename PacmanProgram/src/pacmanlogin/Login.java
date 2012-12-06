/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanlogin;


import java.io.*;
import java.util.*;


/**
 * Logs a user into his profile. 
 * @author xchen82
 * @author ChanWoo
 */
public class Login {
    /**
     * Matches username and password of a user to log in. 
     * @param username
     * @param password
     * @param filename
     * @return All of the user's information if login info is correct, guest user if login fails
     */

       public static GetLoginInfo login(String username, String password,String filename){
       
       ArrayList<GetLoginInfo> UserList = GetUserInfo.getuserinfo(filename);
           
       if(username.equals("")){
           return UserList.get(0);
       }    
           

       int loopCounter;
       
        for (loopCounter=0;loopCounter<UserList.size();loopCounter++){
            if (UserList.get(loopCounter).GetUserName().equals(username)&&UserList.get(loopCounter).GetPassWord().equals(password)) {
                return UserList.get(loopCounter);
            }            
        }
        
        return UserList.get(0);
        }

	
}
