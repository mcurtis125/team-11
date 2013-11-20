import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

/* CSV file format "username,password,wins,total games" */
public class CSV {
	
	String filePath = "Database.csv"; 
	private final int USERNAME_INDEX = 0;
	private final int WINS_INDEX = 2;
	private final int GAMES_INDEX = 3;
	private String[][] statsArray;
	public CSV() {
		
	}
	public boolean verifyAccount(String username, String password) {
		
		String info = username + "," + password;
		
		BufferedReader reader = null;

		try {
		    File file = new File(filePath);
		    if (!file.exists()) {
		    	file.createNewFile();
		    }
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
		//System.out.println("false");
		return false;
	}
	/*
	 * username: ASCII alphanumeric characters only
	 * password: Minimum 8 characters, one lowercase, one uppercase, 
	 * one digit, and one nonalphanumeric character
	 * 
	 * returns 0 for bad username, 1 if username already exists,
	 * 2 for bad password, 3 for success
	 */
	public int addAccount(String username, String password) {
		
		if (!isAlphaNumeric(username)) {
			return 0;
		}
		
		if (verifyAccount(username, "")) {
			return 1;
		}
		
		if (password.length() < 8 || !passwordRequirements(password)) {
			return 2;
		}

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
		return 3;
	}
	
	private boolean isAlphaNumeric(String s){
	    String pattern= "^[a-zA-Z0-9]*$";
	        if(s.matches(pattern)){
	            return true;
	        }
	        return false;   
	}
	
	private boolean passwordRequirements(String pass) {
		int current;
		boolean hasNum = false;
		boolean hasUpper = false;
		boolean hasLower = false;
		boolean hasSymbol = false;
		for (int i = 0; i < pass.length(); i++) {
			current = pass.charAt(i);
			if (current < 58 && current > 47) {
				hasNum = true;
			} else if (current > 64 && current < 91)  {
				hasUpper = true;
			} else if (current > 96 && current < 123) {
				hasLower = true;
			} else if (current > 32 && current < 127) {
				hasSymbol = true;
			}
		}
		System.out.println(hasNum);
		System.out.println(hasUpper);
		System.out.println(hasLower);
		System.out.println(hasSymbol);
		System.out.println(hasNum && hasUpper && hasLower && hasSymbol);
		return (hasNum && hasUpper && hasLower && hasSymbol);
	}
	
	//method to return stats from Database.csv
	public String[][] generateStats() {
		BufferedReader reader = null;
		try {
		    File file = new File(filePath);
		    if (!file.exists()) {
		    	file.createNewFile();
		    }
		    reader = new BufferedReader(new FileReader(file));

		    String line;
			int numLines = 0; 
			String seperator = ",";
			ArrayList<String[]> statsList = new ArrayList<String[]>();
			//read each line and store as seperated string array in arraylist
		    while ( (line = reader.readLine() ) != null) {
				statsList.add(line.split(seperator));
				numLines++;
		    }
			//return that no data is present if numLines = 0
			if(numLines == 0) {
				String[][] tmp = {{" "," "," "}};
				return tmp;
			}
			//create and fill 2d array, length - 1 to exclude password information
			String[][] statsArray = new String[numLines][statsList.get(0).length - 1];
			//fill each row
			for(int i = 0; i < statsArray.length; i++) {
				//fill each column, exluding password information
				statsArray[i][0] = statsList.get(i)[USERNAME_INDEX];
				statsArray[i][1] = statsList.get(i)[WINS_INDEX];
				statsArray[i][2] = statsList.get(i)[GAMES_INDEX];
			}
			return statsArray;

		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    try {
		        reader.close();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}
		//tmp output
		String[][] tmp = {{" "," "," "}};
		return tmp;
	}
}
