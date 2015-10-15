package ID3;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;


public class Solution {

	public static void main(String[] args) throws ParseException, IOException {
		if(args.length == 0){
			System.out.println("Please pass some arguments.");
			System.exit(0);
		}
		else{
			boolean boolPrint = Boolean.FALSE;
			int L = Integer.parseInt(args[0]); 
			int K = Integer.parseInt(args[1]); 
			String trainingData = args[2];
			String validationData = args[3];
			String testData = args[4];
			String print = null;
			try {
			print = args[5];
			} catch(Exception e)
			{}

			if(print != null)
			{
				if(print.equalsIgnoreCase("Yes")){
					boolPrint = Boolean.TRUE;
				}
				else if(print.equalsIgnoreCase("No")){
					boolPrint = Boolean.FALSE;
				}
				else
				{
					System.out.println("Please pass a Yes or No to denote if you want to print the tree!! Or ignore");
					System.exit(0);
				}
			}

			FileParser FileParser = new FileParser();
			CreateTree CreateTree = new CreateTree();
			PruneTree PruneTree = new PruneTree();
			
			ArrayList<ArrayList<String>> trainingSet = FileParser.read(trainingData);
			ArrayList<ArrayList<String>> validationSet = FileParser.read(validationData);
			ArrayList<ArrayList<String>> testSet = FileParser.read(testData);

			ArrayList<String> attributeList = trainingSet.get(0);

			Tree treeLearningEntropy = CreateTree.getTree(trainingSet, attributeList);
			System.out.println("Accuracy with information gain heuristic on test data: " + PruneTree.accuracyOfTree(treeLearningEntropy, testSet));
			System.out.println();
			Tree prunedTreeEntropy = PruneTree.prunedTree(treeLearningEntropy, L, K, validationSet);
			System.out.println("Accuracy of pruned tree on test data: " + PruneTree.accuracyOfTree(prunedTreeEntropy, testSet));
			System.out.println();
			if(boolPrint){
				treeLearningEntropy.print();
				System.out.println();
				System.out.println();
			}
				
		}
	}
}
