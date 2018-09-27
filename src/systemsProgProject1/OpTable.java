package systemsProgProject1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class OpTable {
	
	static File file1 = new File("OPTable.txt");
	static HashMap <String,String> opTable = new HashMap<String,String>();
	
	//function reads the opTable values to a HashMap
	public static void readOpTable() throws FileNotFoundException{
		Scanner in = new Scanner(file1);
		int check;
			String s;
			String temp1;
			String temp2;
			
		while(in.hasNext()){
			
			s = in.nextLine();
			check = 0;
			temp1 ="";
			temp2 ="";
			
			for(int i=0;i<s.length();i++){
				if(check == 1)
					temp2 += s.charAt(i);
				else if(s.charAt(i)!=' ')
					temp1 += s.charAt(i);
				else
					check=1;
			}
			
			opTable.put(temp1, temp2);
//			System.out.println(opTable.get(temp1));
		}
		
		in.close();
	}

}

