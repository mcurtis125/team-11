import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/* CSV file format "username,password,wins,total games" */
public class CSV {
	
	String filePath = "Database.csv"; 
	public CSV() {
		
	}
	public boolean verifyAccount(String username, String password) {
		
		String info = username + "," + password;
		
		BufferedReader reader = null;

		try {
		    File file = new File(filePath);
		    reader = new BufferedReader(new FileReader(file));

		    String line;
		    while ((line = reader.readLine()) != null) {
		        if (info.regionMatches(0, line, 0, info.length())) {
		        	return true;
		        }
		    }

		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    try {
		        reader.close();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}
		System.out.println("false");
		return false;
	}
	
	public void addAccount(String username, String password) {
	
		try{
    		String data = username + "," + password + "," + 0 + "," + 0 + "\n";
 
    		File file =new File(filePath);
 
    		//if file doesnt exists, then create it
    		if(!file.exists()){
    			file.createNewFile();
    		}
 
    		//true = append file
    		FileWriter fileWritter = new FileWriter(file.getName(),true);
    	        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
    	        bufferWritter.write(data);
    	        bufferWritter.close();
 
	        System.out.println("Done");
 
    	}catch(IOException e){
    		e.printStackTrace();
    	}
	}
	
	
}
