package systemsProgProject1;
import java.util.ArrayList;
import java.util.HashMap;

public class SymbolTable{
	
	static HashMap<String,String> symbolTab = new HashMap<String,String>();
	
	public static void fillSymbolTable(ArrayList interMidiate){
		//symbol table
		int symbol=0;
		String symbolLine;
		String symbolLineAddress="";
		for(int i=1;i<interMidiate.size()-1;i++){
			symbol=0;
			symbolLine=""+interMidiate.get(i);
			try{
			for(int j=0;j<2;j++){
				while(symbolLine.charAt(symbol)!=' ')
					symbol++;
				symbol++;
				}
			}
			catch(StringIndexOutOfBoundsException ex){}
			symbolLineAddress=symbolLine.substring(0,symbol-1);
			symbol=0;
			while(symbolLine.charAt(symbol)!=' ')
				symbol++;
			if(!OpTable.opTable.containsKey(symbolLineAddress.substring(symbol+1, symbolLineAddress.length()))){
				symbolTab.put(symbolLineAddress.substring(symbol+1, symbolLineAddress.length()),
						symbolLineAddress.substring(0, symbol));
			}
			
		}	
//		for (String a:symbolTab)
			System.out.println(symbolTab);
		
	}

}