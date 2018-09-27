package systemsProgProject1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class IntermidiateFile {
	 
	public IntermidiateFile()throws FileNotFoundException{}
	
	File file1 = new File("prog2.txt");
	Scanner in = new Scanner(file1);

	static String initialAddress;
	static ArrayList<String> interMidiate = new ArrayList<String>();
	private static String s;
	String temp ="";
	int size;
	

	private void detemineInitialAddress() throws FileNotFoundException{
			
		int check = 0;
		s = in.nextLine();
		
		//fits line to get starting address
		for(int i=0;i<s.length();i++){
	
			if(s.charAt(i)==' ')
				check++;
			else if(check == 2)
				temp +=s .charAt(i);
		}
		
		if(!temp.isEmpty()){
			initialAddress = temp;
			//kamel el length 5 lel address
			size = initialAddress.length(); 
			if( size != 4){
				temp = "";
				for(int h=0; h<4-size;h++)
					temp += "0";
				initialAddress = temp + initialAddress;
			}
		}
		else 
			initialAddress = "0000";
		
		
		
		interMidiate.add(initialAddress+" "+s);
		
		s = in.nextLine();
//		if(s.contains("FIRST"))
			interMidiate.add(initialAddress+" "+s);
		
	
	}
		
	//first pass making intermediate file
	private void readProgram() throws FileNotFoundException{
		
		detemineInitialAddress();
		String address = initialAddress; //hex value
		
		while(in.hasNext()){
	
			int check = 0;
			String temp ="";
			int length = 0;
		
			//get last operand
			for(int i=0;i<s.length();i++){
				
				if(s.charAt(i)==' ')
					check++;
				else if(check == 2){
					int k = i;
					while( k < s.length()){
						temp +=s .charAt(k);
						k++;
					}
					break;
				}
			}
//			System.out.println(temp);
			
			//give correct addresses
			 if(s.contains("BYTE")){
				if(s.contains("X'")){
					length = temp.length()-3;
					address= Integer.toHexString((int) (Integer.valueOf(address,16)+Math.ceil(length/2.0)));
				}
				else{
					length = temp.length()-3;
					address= Integer.toHexString(Integer.valueOf(address,16)+length);
				}
				}
				
			else if(s.contains("RESB")){
				
				address = Integer.toHexString(Integer.valueOf(address,16)+Integer.parseInt(temp));
				
			}
			else if(s.contains("RESW"))
				address= Integer.toHexString(Integer.valueOf(address,16)+(Integer.parseInt(temp)*3));
			
			else
				address= Integer.toHexString(Integer.valueOf(address,16)+3);
			
			
			s = in.nextLine();
			size = address.length(); 
			if( size != 4){
				temp = "";
				for(int h=0; h<4-size;h++)
					temp += "0";
					address = temp + address;
			}

			interMidiate.add(address+" "+s);
			
		}
		
		//printing result
		for (String a:interMidiate){
			System.out.println(a);
		}
		
		SymbolTable.fillSymbolTable(interMidiate);
		
	}
	
	
	public void writeIntermidiateFile() throws FileNotFoundException{
		
		readProgram();
		
		//write into new file hena
		PrintWriter p =new PrintWriter("intermidiateFile.txt");
		for(int i=0;i<interMidiate.size();i++){
			p.println(interMidiate.get(i));
		}
		p.close();

	}

	
}
