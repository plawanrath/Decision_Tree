package ID3;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class InformationGain {

	HashMap<String, ArrayList<String>> dataMap;
	HashMap<String, Double> gainValues ;


	public static double logOfBase(double num, double base){
		return Math.log(num)/Math.log(base);
	}

	public static double entropy(double oneCounts, double zeroCounts){
		double total = oneCounts + zeroCounts;
		double probOnes = oneCounts/total;
		double probZeros = zeroCounts/total;

		if(oneCounts == zeroCounts){
			return 1;
		}
		else if(oneCounts == 0 || zeroCounts == 0){
			return 0;
		}
		else{
			double entropy = ((-probOnes) * (logOfBase (probOnes,2))) + ((-probZeros)*(logOfBase(probZeros, 2)));
			return entropy;
		}

	}

	public double infoGain(double rootOne, double rootZero, double oneLeft, double zeroLeft, double oneRight, double zeroRight){
		double totalRoot = rootOne + rootZero;
		double rootEntropy = entropy(rootOne, rootZero);
		double leftEntropy = entropy(oneLeft,zeroLeft);
		double rightEntropy = entropy(oneRight, zeroRight);
		double totalLeft = oneLeft + zeroLeft;
		double totalRight = oneRight + zeroRight;

		double gain = rootEntropy - (((totalLeft/totalRoot)* leftEntropy) + ((totalRight/totalRoot) * rightEntropy));

		return gain;
	}

	public String bestAttribute(ArrayList<ArrayList<String>> data, ArrayList<String> attributeList) throws FileNotFoundException{
		String bestAttribute = "";
		DataManipulation dm = new DataManipulation();
		dataMap = dm.attDensityInData(data);
		gainValues = new HashMap<String, Double>();
		double classOne = 0;
		double classZero = 0;
		for(String value : dataMap.get("Class")){
			if(value.equalsIgnoreCase("1")){
				classOne++;
			}
			else{
				classZero++;
			}
		}

		for(String key: attributeList.subList(0, attributeList.size()-1)){		
			ArrayList<String> temp = dataMap.get(key);
			double oneLeft = 0;
			double oneRight = 0;
			double zeroLeft = 0;
			double zeroRight = 0;
			for(int i=0; i<temp.size();i++){								
				if(temp.get(i).equalsIgnoreCase("0")){
					if(dataMap.get("Class").get(i).equalsIgnoreCase("1")){
						oneLeft++;
					}
					else{
						zeroLeft++;
					}
				}
				else{
					if(dataMap.get("Class").get(i).equalsIgnoreCase("1")){
						oneRight++;
					}
					else{
						zeroRight++;
					}
				}
			}

			Double gainForEachKey = infoGain(classOne, classZero, oneLeft, zeroLeft, oneRight, zeroRight);
			gainValues.put(key, gainForEachKey);
		}

		ArrayList<Double> valueList = new ArrayList<Double>(gainValues.values());
		Collections.sort(valueList);
		Collections.reverse(valueList);
		for(String key: gainValues.keySet()){
			if (valueList.get(0).equals(gainValues.get(key))){
				bestAttribute = key;
				break;
			}
		}
		return bestAttribute;		
	}
}
