package ID3;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class DataManipulation {

	public HashMap<String,ArrayList<ArrayList<String>>> getBestAttClassification(ArrayList<ArrayList<String>> data, String bestAttribute){
		HashMap<String, ArrayList<ArrayList<String>>> bestAttClassificationMap = new HashMap<String, ArrayList<ArrayList<String>>>();
		int index = data.get(0).indexOf(bestAttribute);
		for(int i=1;i<data.size();i++){
			if(data.get(i).get(index).equalsIgnoreCase("0")){
				if(bestAttClassificationMap.containsKey("0")){
					bestAttClassificationMap.get("0").add(data.get(i));
				}
				else{
					ArrayList<ArrayList<String>> dataAdd = new ArrayList<ArrayList<String>>();
					dataAdd.add(data.get(0));
					dataAdd.add(data.get(i));
					bestAttClassificationMap.put("0",dataAdd);
				}

			}
			else{
				if(bestAttClassificationMap.containsKey("1")){
					bestAttClassificationMap.get("1").add(data.get(i));
				}
				else{
					ArrayList<ArrayList<String>> dataAdd = new ArrayList<ArrayList<String>>();
					dataAdd.add(data.get(0));
					dataAdd.add(data.get(i));
					bestAttClassificationMap.put("1",dataAdd);
				}
			}
		}

		return bestAttClassificationMap;
	}

	public HashMap<String, ArrayList<String>> attDensityInData(ArrayList<ArrayList<String>> data) throws FileNotFoundException{
		HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();

		ArrayList<String> keys = data.get(0);	

		for(int i=0;i<keys.size();i++){
			for(int j=1;j<data.size();j++){
				if (map.containsKey(keys.get(i))){
					map.get(keys.get(i)).add(data.get(j).get(i));
				}
				else{
					ArrayList<String> values = new ArrayList<String>();
					values.add(data.get(j).get(i));
					map.put(keys.get(i), values);
				}
			}
		}
		return map;
	}


}
