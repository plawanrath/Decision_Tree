package ID3;

import java.util.Set;

public class Tree {
	private Tree left;
	private Tree right;
	private String name;
	private boolean leafNode;
	private String leafValue;
	private int nodeNumber;
	private static int depth = -1;
	
	
	private Set<String> attributes;
	
	
	public Tree() {
		super();
	}
	
	public Tree(String leafValue){
		this.leafValue = leafValue;
		this.setLeafNode(Boolean.TRUE);
	}
	
	public Tree(String bestAttr, Tree left, Tree right){
		this.name = bestAttr;
		this.left = left;
		this.right = right;
		this.setLeafNode(Boolean.FALSE);
	}
	
	public Tree getLeft() {
		return left;
	}

	public void setLeft(Tree left) {
		this.left = left;
	}

	public Tree getRight() {
		return right;
	}

	public void setRight(Tree right) {
		this.right = right;
	}
	
	
	public boolean isLeafNode() {
		return leafNode;
	}

	public void setLeafNode(boolean leafNode) {
		this.leafNode = leafNode;
	}

	public String getLeafValue() {
		return leafValue;
	}

	public void setLeafValue(String leafValue) {
		this.leafValue = leafValue;
	}

	public Set<String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Set<String> attributes) {
		this.attributes = attributes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * Function to print the tree
	 * based on the depth of the tree.
	 */
	public void print(){
		depth++;
		if(this.name == null){
			System.out.print(" : " + leafValue);
		}
		else{
			System.out.println();
			for(int i=0; i<depth;i++){
				System.out.print(" | ");
			}
			System.out.print(name + " = 0");
		}

		if(left != null){
			left.print();
			if(this.name == null){
				System.out.print(" : " + leafValue);
			}
			else{
				System.out.println();
				for(int i=0; i<depth;i++){
					System.out.print(" | ");
				}
				System.out.print(name + " = 1" );
			}
			right.print();
		}
		depth--;
	}

	public int getNodeNumber() {
		return nodeNumber;
	}

	public void setNodeNumber(int nodeNumber) {
		this.nodeNumber = nodeNumber;
	}
	
	
}
