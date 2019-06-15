package disjointSets;

public class DisjointSets {
		
	private Node[] nodes;
	private int sets;
		
	public DisjointSets(int numElems) {
		nodes = new Node[numElems];
		for (int i = 0; i < numElems; i++) {
			Node node = new Node();
			node.parent = node;
			nodes[i] = node;
		}
		sets = numElems;
	}
		
	private static Node find(Node node) {
		if (node.parent != node)
			return find(node.parent);
		return node.parent;
	}
	
	public boolean inSameSet(int elemIndex0, int elemIndex1) {
		return find(elemIndex0) == find(elemIndex1);
	}
	
	private Node find(int elemIndex) {
		return find(nodes[elemIndex]);
	}
	
	public boolean union(int elemIndex0, int elemIndex1) {
		Node repr0 = find(elemIndex0);
		Node repr1 = find(elemIndex1);		
		if (repr0 == repr1)
			return false;		
		repr1.parent = repr0;
		sets -= 1;
		return true;
	}
	
	public int getNumberOfSets(){
		return sets;
	}
}