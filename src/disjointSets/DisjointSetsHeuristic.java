package disjointSets;

public class DisjointSetsHeuristic {
	private NodeH[] nodes;
	private int sets;
		
	public DisjointSetsHeuristic(int numElems) {
		nodes = new NodeH[numElems];
		for (int i = 0; i < numElems; i++) {
			NodeH node = new NodeH();
			node.parent = node;
			node.rank = 0;
			nodes[i] = node;
		}
		sets = numElems;
	}
		
	private static NodeH find(NodeH node) {
		if (node.parent != node)
			node.parent = find(node.parent);
		return node.parent;
	}
	
	public boolean inSameSet(int elemIndex0, int elemIndex1) {
		return find(elemIndex0) == find(elemIndex1);
	}
	
	private NodeH find(int elemIndex) {
		return find(nodes[elemIndex]);
	}
	
	public boolean union(int elemIndex0, int elemIndex1) {
		NodeH repr0 = find(elemIndex0);
		NodeH repr1 = find(elemIndex1);		
		if (repr0 == repr1)
			return false;		

		if (repr0.rank > repr1.rank)
			repr1.parent = repr0;
		else{
			repr0.parent = repr1;
			if (repr0.rank == repr1.rank)
				repr1.rank++;
		}
		
		sets -= 1;
		return true;
	}
	
	public int getNumberOfSets(){
		return sets;
	}
}
