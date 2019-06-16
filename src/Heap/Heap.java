package Heap;

import Graph.Arco;

public class Heap {
    private Arco[] heap;
    private int size;

    public Heap(Arco[] arcos) {
        heap = new Arco[arcos.length + 1];
        heap[0] = null;
        size = arcos.length;
        int i = 1;
        for (Arco arco : arcos){
            heap[i++]= arco;
        }

        minHeap();
    }
    
    private void minHeap() {
        for (int pos = Math.floorDiv(size, 2); pos >= 1; pos--) {
            minHeapify(pos);
        }
    }

    private int leftChild(int pos) {
        return (2 * pos);
    }

    private int rightChild(int pos) {
        return (2 * pos) + 1;
    }

    private boolean hasBothChildren(int pos) {
        return leftChild(pos)<=size && rightChild(pos)<=size ? true : false;
    }

    private void swap(int fpos, int spos) {
        Arco tmp;
        tmp = heap[fpos];
        heap[fpos] = heap[spos];
        heap[spos] = tmp;
    }

    private void minHeapify(int pos) {
        // If the node is a non-leaf node and greater
        // than any of its child
        if (hasBothChildren(pos)) {
            if (heap[pos].weight > heap[leftChild(pos)].weight
                    || heap[pos].weight > heap[rightChild(pos)].weight) {

                // Swap with the left child and heapify
                // the left child
                if (heap[leftChild(pos)].weight < heap[rightChild(pos)].weight) {
                    swap(pos, leftChild(pos));
                    minHeapify(leftChild(pos));
                }

                // Swap with the right child and heapify
                // the right child
                else {
                    swap(pos, rightChild(pos));
                    minHeapify(rightChild(pos));
                }
            }
        } else if (leftChild(pos)<=size && (heap[pos].weight > heap[leftChild(pos)].weight)) {
            swap(pos, leftChild(pos));
        }
    }

    public Arco removeMin() {
        Arco popped = heap[1];
        heap[1] = heap[size--];
        minHeapify(1);
        return popped;
    }
}
