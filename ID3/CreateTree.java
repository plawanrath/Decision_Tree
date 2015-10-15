package ID3;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class CreateTree {

	public Tree getTree(ArrayList<ArrayList<String>> dataSample, ArrayList<String> attributeList) throws FileNotFoundException{
		int countZero = 0;
		int countOne = 0;
		for(int i=1; i < dataSample.size();i++){
			if(dataSample.get(i).get(dataSample.get(i).size()-1).equals("1")){
				countOne++;
			}
			else{
				countZero++;
			}
		}
		if (attributeList.isEmpty() || countZero == dataSample.size()-1){
			return new Tree("0");

		}
		else if(attributeList.isEmpty() || countOne == dataSample.size()-1){
			return new Tree("1");
		}
		else{
			InformationGain gc = new InformationGain();
			DataManipulation dm = new DataManipulation();
			String bestAttribute = gc.bestAttribute(dataSample,attributeList);
			ArrayList<String> attributes2 = new ArrayList<String>();
			HashMap<String, ArrayList<ArrayList<String>>> gainMap = dm.getBestAttClassification(dataSample, bestAttribute);
			for(int i=0;i<attributeList.size();i++)
			{
				String att = attributeList.get(i);
				if(!att.equals(bestAttribute)){
					attributes2.add(att);
				}
			}
			if (gainMap.size() < 2){
				String value = "0";
				if(countOne > countZero){
					value = "1";
				}

				return new Tree(value);
			}
			return new Tree(bestAttribute, getTree(gainMap.get("0"),attributes2),getTree(gainMap.get("1"),attributes2));
		}
	}

}
