package systemsProgProject1;

import java.io.FileNotFoundException;

public class TestAssembler {

	public static void main(String[] args) throws FileNotFoundException {
		
		//Load opCode Table
		OpTable.readOpTable();
		
		long start = System.currentTimeMillis();
		
		//Create Intermediate File
		IntermidiateFile t = new IntermidiateFile();
		t.writeIntermidiateFile();
		
		//Create ObjectCode File
		ObjectFile.writeObjectFile();
		
		long finish = System.currentTimeMillis();
		
		//show time taken
		System.out.println("Time Taken: " + (finish - start) + " mSec");
		
		
	}

}
