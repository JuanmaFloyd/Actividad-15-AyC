package disjointSets;

public class DisjointSets {
		
	private Node[] nodes;
		
	public DisjointSets(int numElems) {
		nodes = new Node[numElems];
		for (int i = 0; i < numElems; i++) {
			Node node = new Node();
			node.parent = node;
			node.rank = 0;
			nodes[i] = node;
		}
	}
			private Node find(int elemIndex) {
		if (elemIndex < 0 || elemIndex >= nodes.length)
			throw new IndexOutOfBoundsException();
		return find(nodes[elemIndex]);
	}
	
	
	private static Node find(Node node) {
		if (node.parent != node)
			node.parent = find(node.parent);
		return node.parent;
	}
	
	
	public boolean inSameSet(int elemIndex0, int elemIndex1) {
		return find(elemIndex0) == find(elemIndex1);
	}
	
	
	public boolean union(int elemIndex0, int elemIndex1) {
		Node repr0 = find(elemIndex0);
		Node repr1 = find(elemIndex1);
		if (repr0 == repr1)
			return false;
		
		if (repr0.rank > repr1.rank)
			repr1.parent = repr0;
		else if (repr1.rank > repr0.rank)
			repr0.parent = repr1;
		else {
			repr1.parent = repr0;
			repr0.rank++;
		}
		return true;
	}
}