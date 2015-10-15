package ID3;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class FileParser {


	public ArrayList<ArrayList<String>> read(String fileName) throws IOException{

		String getDir = System.getProperty("user.dir");
		StringBuilder sb = new StringBuilder(getDir);
		sb.append(System.getProperty("file.separator"));
		sb.append(fileName);

		String fileNameFinal = sb.toString();
		ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
		File file = new File(fileNameFinal);
		Scanner input;
		input = new Scanner(file);
		while(input.hasNext()){
			String[] dataForEachRow = input.next().split(",");
			data.add(new ArrayList<String>(Arrays.asList(dataForEachRow)));

		}
		input.close();
		return data;
	}

}
