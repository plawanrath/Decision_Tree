package ID3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PruneTree {

	private int nonLeaf = 0;
	
	public int getnonLeaf() {
		int number = this.nonLeaf;
		setnonLeaf(0);
		return number;
	}


	public void setnonLeaf(int nonLeaf) {
		this.nonLeaf = nonLeaf;
	}

	public double accuracyOfTree(Tree node, ArrayList<ArrayList<String>> dataToBeChecked){
		double accuracy = 0;
		int goodSamples = 0;

		ArrayList<String> attributes = dataToBeChecked.get(0);
		for(ArrayList<String> row : dataToBeChecked.subList(1, dataToBeChecked.size())){	
			boolean checkBool = treeVerify(node, row, attributes);					
			if(checkBool){
				goodSamples++;
			}
		}
		accuracy = (((double) goodSamples / (double) (dataToBeChecked.size()-1)) * 100.00);
		return accuracy;
	}

	public boolean treeVerify(Tree root, ArrayList<String> row, ArrayList<String> attributeList){
		Tree nodeCopy = root;
		while(true){
			if(nodeCopy.isLeafNode()){
				if(nodeCopy.getLeafValue().equals(row.get(row.size()-1))){
					return true;
				}
				else{
					return false;
				}
			}
			int index = attributeList.indexOf(nodeCopy.getName());
			String value = row.get(index);
			if(value.equals("0")){
				nodeCopy = nodeCopy.getLeft();
			}
			else{
				nodeCopy = nodeCopy.getRight();
			}
		}
	}

	public void getNoOfLeafNodes(Tree root){
		if(!root.isLeafNode()){
			nonLeaf++;
			root.setNodeNumber(nonLeaf);
			getNoOfLeafNodes(root.getLeft());
			getNoOfLeafNodes(root.getRight());
		}
	}

	public void replaceNode(Tree root, int P){
		if(!root.isLeafNode()){
			if(root.getNodeNumber() == P){
				String leafValueToBeChanged = findMaxClass(root);
				root.setLeafNode(Boolean.TRUE);
				root.setLeft(null);
				root.setRight(null);
				root.setLeafValue(leafValueToBeChanged);
			}
			else{
				replaceNode(root.getLeft(), P);
				replaceNode(root.getRight(), P);
			}

		}
	}

	public void treeCopy(Tree first, Tree second){
		second.setLeafNode(first.isLeafNode());
		second.setName(first.getName());
		second.setLeafValue(first.getLeafValue());
		if(!first.isLeafNode()){
			second.setLeft(new Tree());
			second.setRight(new Tree());
			treeCopy(first.getLeft(), second.getLeft());
			treeCopy(first.getRight(), second.getRight());
		}
	}

	public List<Tree> getLeafNodeList(Tree root){
		List<Tree> leafNodeList = new ArrayList<>();
		if(root.isLeafNode()){ 
			leafNodeList.add(root);
		}
		else{
			if(!root.getLeft().isLeafNode()){
				getLeafNodeList(root.getLeft());
			}
			if(!root.getRight().isLeafNode()){
				getLeafNodeList(root.getRight());
			}
		}
		return leafNodeList;
	}

	public String findMaxClass(Tree root){
		int countZero = 0;
		int countOne = 0;
		String majority = "0";
		List<Tree> leafNodes = getLeafNodeList(root);
		for(Tree node : leafNodes){
			if(node.getLeafValue().equals("1")){
				countOne++;
			}
			else{
				countZero++;
			}
		}
		if(countOne>countZero){
			majority = "1";
		}

		return majority;
	}

	public Tree prunedTree(Tree root, int l, int k, ArrayList<ArrayList<String>> validationData){
		Tree bestTree = new Tree();
		treeCopy(root, bestTree);
		double bestAccuracyOfTree = accuracyOfTree(bestTree, validationData);
		Tree tree2 = new Tree();
		for(int i=1; i<=l;i++){
			treeCopy(root, tree2);
			Random random = new Random();
			int M = 1 + random.nextInt(k);
			for(int j=0; j<=M; j++){
				getNoOfLeafNodes(tree2);			
				int N = getnonLeaf();
				if(N>1){
					int P = random.nextInt(N) + 1;
					replaceNode(tree2, P);
				}
				else{
					break;
				}
			}
			double accuracyOfTree2 = accuracyOfTree(tree2, validationData);
			if (accuracyOfTree2 > bestAccuracyOfTree){
				bestAccuracyOfTree = accuracyOfTree2;
				treeCopy(tree2, bestTree);
			}
		}
		return bestTree;
	}
	
}
