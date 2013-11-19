import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Stats {
	
	private String[][] rowData;
	private CSV csv;
	
	private final int WINS_COLUMN = 1;
	
	public Stats(CSV csv) {
		this.csv = csv;
		rowData = generateRowData();
	}
	
	//getter for row data to be placed in jTable
	public String[][] getRowData() {
		return rowData;
	}
	
	//method to generate and sort stats by most games won
	public String[][] generateRowData() {
		return bubbleSort( csv.generateStats() );
	}
	
	//helpers to sort 2d data
	public String[][] bubbleSort(String data[][]) {  
		for(int i = data.length; i >= 2; i--) {
			for(int j = 0; j < i - 1; j++) {
				if( Integer.parseInt( data[j][WINS_COLUMN].trim() ) < Integer.parseInt( data[j+1][WINS_COLUMN].trim() ) ) { 
					swap(data, j, j+1);
				}
			}
		}
		return data;
	}  
   
	public void swap(String data[][], int index1, int index2) {  
		String[] temp;  
		temp = data[index1];  
		data[index1] = data[index2];  
		data[index2] = temp;  
	}  
}
