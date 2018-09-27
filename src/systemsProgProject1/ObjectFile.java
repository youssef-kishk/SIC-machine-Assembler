package systemsProgProject1;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ObjectFile {

	static ArrayList<String> OPcode = new ArrayList<String>();
	static ArrayList<String> OBjectFile = new ArrayList<String>();
	private static String firstLine = "";
	private static String lastLine = "";
	
	private static void PassTwo(){
		
		//assign correct opcode
		String address = "";
		String inst = "";
		String operand ="";
		String opcode = "";
		String temp ="";
		String objectCode ="";
		String operValue = "";
		String firstDigit ="";
		int j;
		String hex = "";
		int size;
		int ascii =0;
		String opValue = "";
		String temp1 ="";
		
		int length = IntermidiateFile.interMidiate.size() - 2;
		OPcode.add(0, "Start");
		for(int i = 1; i<= length; i++){
		
			inst = IntermidiateFile.interMidiate.get(i);
//			System.out.println(inst);
			j = 0;
			temp = "";
			opcode = "";
			operand = "";
				
				//7ana5od address mn hena ne save
			address ="";
				while(inst.charAt(j)!= ' '){
					address += inst.charAt(j);
					j++;
				}

				j++;
				
				while(inst.charAt(j) != ' '){
					temp += inst.charAt(j);
					
					if(j!= inst.length()-1)
						j++;
					else
						break;
				
				}
//				System.out.println(temp);
				
//					System.out.println(temp);
				
				if( SymbolTable.symbolTab.containsKey(temp)){
//					System.out.println("d5l");
					//check RESW RESB
					j++;
					while(inst.charAt(j) != ' ')
						opcode += inst.charAt(j++);
					j++;
					while(j != inst.length())
						operand += inst.charAt(j++);
					
				}else{
					
					opcode = temp;
					j++;
					while(j != inst.length())
						operand += inst.charAt(j++);
				
				}
				
			// Making object code for each instruction
//				System.out.println(opcode);
//				System.out.println(operand);
			
				//Value of opcode
				opValue = "";
				opValue = OpTable.opTable.get(opcode);
				if(opValue == null){
		
					if(opcode.contains("RES")){
						//shelt 2 spaces
						opValue = "";
					}
					else if(opcode.contains("WORD")){
						opValue = "";
					}
					else if(opcode.contains("BYTE")){
						opValue = "";
					}
					else{
						System.out.println("ERROR! Invalid Opcode in line "+ (i+1));
						System.exit(-1);
					}
					
				}

				operValue = "";
				//value of operand
				if(opcode.contains("RES")){
					operValue = "";
				}
				else if(opcode.contains("WORD")){
					if(operand.contains("X'")){
						hex = "";
						for(int k=2; k<operand.length()-1; k++){
							hex += operand.charAt(k);
						}
					}
					else if(operand.contains("H")){
						hex = "";
						for(int k=0; k<operand.length()-1; k++){
							hex += operand.charAt(k);
						}
					}
					else{
						hex = Integer.toHexString(Integer.parseInt(operand));
					}
					//azawed zeros
					size = hex.length();
					if(length !=6){
						for(int l=0; l<6-size;l++)
							operValue += "0";
					}
					operValue += hex;
				}
				else if(opcode.contains("BYTE")){
					
					if(operand.contains("X'")){
//						System.out.println("da5al 1");
						hex = "";
						for(int k=2; k<operand.length()-1; k++){
							hex += operand.charAt(k);
						}
					}
					else{
//						System.out.println("da5al 2");
						hex = "";
//						System.out.println(operand);
						for(int f=2; f<operand.length()-1; f++){
							ascii = operand.charAt(f);
							hex += Integer.toHexString(ascii);
						}
						
					}
					
					operValue = hex;
					
				}
				
				else if( operand.length() == 0){
					
					operValue = "0000";
				}
				
				else if(operand.contains(",")){
				
					operand = operand.substring(0, operand.length()-2);
					operValue = SymbolTable.symbolTab.get(operand);
//					System.out.println(operValue);
					
//					System.out.println(operValue.substring(0, 1));
					try{
						size = operValue.length(); 
						if( size != 4){
							temp1 ="";
							for(int z=0;z<4-size;z++){
							temp1 += "0";
							}
							operValue = temp1 + operValue;
							
						}
					
						firstDigit = operValue.substring(0, 1);
//						 System.out.println(firstDigit);
						firstDigit = Integer.toHexString(Integer.valueOf(firstDigit,16)+ 8);
						operValue = firstDigit + operValue.substring(1, operValue.length());
					
					}catch(NullPointerException e){
						System.out.println("ERROR! Invalid operand in line "+ (i+1));
						System.exit(-1);
					}
//					System.out.println(operValue);
					
					
				}

				else{	
					operValue = SymbolTable.symbolTab.get(operand);
					if(operValue == null){
						System.out.println("ERROR! Invalid operand in line "+ (i+1));
						System.exit(-1);
					}
					else{
						
						size = operValue.length(); 
						if( size != 4){
							temp1 ="";
							for(int z=0;z<4-size;z++){
							temp1 += "0";
							}
							operValue = temp1 + operValue;
							
						}
					}
						
				}

				objectCode = opValue + operValue;
//				System.out.println(objectCode);
				OPcode.add(i,address +" "+objectCode);
				
		}
		OPcode.add(length+1, "End");
		
		for(String a:OPcode)
			System.out.println(a);
			
		}

	private static void firstAndLastLine(ArrayList interMidiate){
		String s;
		firstLine="H ";
		lastLine="E ";
		int i=0;
		String temp = "";
		s=""+interMidiate.get(0);
		while(s.charAt(i)!=' '){
			temp+=s.charAt(i);
			i++;
		}
		int j=i;
		while(j<6){
			temp=temp.codePointCount(0, 0)+temp;
			j++;
		}
		i++;
		while(s.charAt(i)!=' '){
			firstLine+=s.charAt(i);
			i++;
		}
		String startAddres=temp;
		firstLine+=" "+temp;
		s=""+interMidiate.get(interMidiate.size()-1);
		i=0;
		temp="";
		while(s.charAt(i)!=' '){
			temp+=s.charAt(i);
			i++;
		}
		while(i<6){
			temp=temp.codePointCount(0, 0)+temp;
			i++;
		}
		String endAddress=temp;
		temp = Integer.toHexString(Integer.valueOf(endAddress,16)-Integer.valueOf(startAddres,16));
		i=temp.length();
		while(i<6){
			temp=temp.codePointCount(0, 0)+temp;
			i++;
		}
		//First & last lines completed
		firstLine+=" "+temp;
		lastLine+=startAddres;
//		System.out.println(firstLine);
//		System.out.println(lastLine);
	}
	
	public static void prepareObjectFile(){
		
		PassTwo();
		
		String finalSize = "";
		String inst = "";
		String address = "";
		String objectCode = "";
		int j = 0;
		int size = 0;
		String record = "";
		String startingAddress = "";
		int k = 1;
		String zeros = "";
		int t=0;
		int l;
		
		// first-> firstLine Last -> lastLine
		firstAndLastLine(IntermidiateFile.interMidiate);
		
		//Filling first line
		OBjectFile.add(0, firstLine);
//		System.out.println(OBjectFile.get(0));
		
		//Filling text records
		int length = OPcode.size() -2;
//		System.out.println(length);
		for(int i = 1; i <= length; i++){

			//Getting instruction
			inst = OPcode.get(i);
//			System.out.println(inst);
		
			//Getting address
			address ="";
			j = 0;
			while(inst.charAt(j)!= ' '){
				address += inst.charAt(j);
				j++;
			}
			if(address.length() < 6){
				zeros = "";
				for(int z =0; z<6-address.length(); z++){
					zeros += "0";
				}
				address = zeros + address;
			}
//			System.out.println(address);
			
			//Getting objectCode;
			 if(j < inst.length()-1)
				 j++;
			objectCode ="";

				while(inst.charAt(j) != ' '){
					objectCode += inst.charAt(j);
				
				if(j!= inst.length()-1)
					j++;
					else
					break;
				}
			
//			if(objectCode == "")
//				System.out.println("fady");
			
			//begin writing record
			//initialize new record
			if(record.length() == 0){

				startingAddress = address;
				size = 0;
//				record = "T "+ startingAddress+" ";
				
			}
			
			//60 -> max bits allowed for 10 instructions in a record
			if(objectCode == ""){
				
				//add in record
				finalSize = Integer.toHexString(size/2);
				if(finalSize.length() == 1)
					finalSize = "0" + finalSize;
				
				OBjectFile.add(k, "T " + startingAddress +" "+ finalSize +" "+ record);
				k++;
				
				t = i+1;
				l = OPcode.get(t).length();
//				System.out.println(l);
				while(l == 5 && t!= length+1){					
//				while(!OPcode.get(t).contains(" ")&&!OPcode.get(t).contains("End")){
					t++;
					i++;
				}
				//zawedt dih
				if( i == length){
					break;
				}else{
					
					//gahez new record
					record = "";
					size = 0;
				
				}
			}
			else if(objectCode.length()+ size <= 60){
				
				record += objectCode + " ";
				size +=  objectCode.length();
//				System.out.println(record);
//				System.out.println(i);

			}
			else{

				//e2fel el record da 
				finalSize = Integer.toHexString(size/2);
				if(finalSize.length() == 1)
					finalSize = "0" + finalSize;
				
				OBjectFile.add(k, "T " + startingAddress +" "+ finalSize +" "+ record);
				
				//gahez new record
				record = "";
				size = 0;
				i--;
				k++;
				
				
			}
			//2afel a5er record 5alas
			if (i == length){
//				System.out.println("enter");
				
//				record = startingAddress +" "+ Integer.toHexString(size) +" "+ record;
//				System.out.println(record);
				finalSize = Integer.toHexString(size/2);
				if(finalSize.length() == 1)
					finalSize = "0" + finalSize;
				OBjectFile.add(k, "T " + startingAddress +" "+ finalSize +" "+ record);
				
			}
			
		}
		
		//Fill last line
		OBjectFile.add(lastLine);
		
		for(String e:OBjectFile)
			System.out.println(e);
		
	}

	public static void writeObjectFile() throws FileNotFoundException{
		
		prepareObjectFile();
		
		//write into new file hena
		PrintWriter p =new PrintWriter("finalObjectFile.txt");
		for(int i=0;i<OBjectFile.size();i++){
			p.println(OBjectFile.get(i));
		}
		p.close();
		
	
	}
	
}

	

